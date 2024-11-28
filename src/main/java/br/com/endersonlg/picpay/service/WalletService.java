package br.com.endersonlg.picpay.service;

import org.springframework.stereotype.Service;

import br.com.endersonlg.picpay.controller.dto.CreateWalletDTO;
import br.com.endersonlg.picpay.entity.Wallet;
import br.com.endersonlg.picpay.exception.WalletDataAlreadyExistsException;
import br.com.endersonlg.picpay.repositories.WalletRepository;

@Service
public class WalletService {

  private final WalletRepository walletRepository;

  public WalletService(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  public Wallet createWallet(CreateWalletDTO dto) {
    var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

    if (walletDb.isPresent()) {
      throw new WalletDataAlreadyExistsException("CpjCnpj or Email already exists");
    }

    return walletRepository.save(dto.toWallet());
  }

}
