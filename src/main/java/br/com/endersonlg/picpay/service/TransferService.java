package br.com.endersonlg.picpay.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import br.com.endersonlg.picpay.controller.dto.TransferDTO;
import br.com.endersonlg.picpay.entity.Transfer;
import br.com.endersonlg.picpay.entity.Wallet;
import br.com.endersonlg.picpay.exception.InsufficientBalanceException;
import br.com.endersonlg.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.endersonlg.picpay.exception.TransferNotAuthorizedException;
import br.com.endersonlg.picpay.exception.WalletNotFoundException;
import br.com.endersonlg.picpay.repositories.TransferRepository;
import br.com.endersonlg.picpay.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class TransferService {

  private final TransferRepository transferRepository;
  private final AuthorizationService authorizationService;
  private final NotificationService notificationService;
  private final WalletRepository walletRepository;

  public TransferService(TransferRepository transferRepository, AuthorizationService authorizationService,
      NotificationService notificationService, WalletRepository walletRepository) {
    this.transferRepository = transferRepository;
    this.authorizationService = authorizationService;
    this.notificationService = notificationService;
    this.walletRepository = walletRepository;
  }

  @Transactional
  public Transfer transfer(@Valid TransferDTO transferDTO) {
    var sender = walletRepository.findById(transferDTO.payer())
        .orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

    var receiver = walletRepository.findById(transferDTO.payee())
        .orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

    validateTransfer(transferDTO, sender);

    sender.debit(transferDTO.value());
    receiver.credit(transferDTO.value());

    var transfer = new Transfer(sender, receiver, transferDTO.value());

    walletRepository.save(sender);
    walletRepository.save(receiver);
    var transferResult = transferRepository.save(transfer);

    CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

    return transferResult;
  }

  private void validateTransfer(@Valid TransferDTO transferDTO, Wallet sender) {
    if (!sender.isTransferAllowedForWalletType()) {
      throw new TransferNotAllowedForWalletTypeException();
    }

    if (!sender.isBalancerEqualOrGreatherThan(transferDTO.value())) {
      throw new InsufficientBalanceException();
    }

    if (!authorizationService.isAuthorized(transferDTO)) {
      throw new TransferNotAuthorizedException();
    }

  }

}
