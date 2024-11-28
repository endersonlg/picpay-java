package br.com.endersonlg.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.endersonlg.picpay.controller.dto.CreateWalletDTO;
import br.com.endersonlg.picpay.entity.Wallet;
import br.com.endersonlg.picpay.service.WalletService;
import jakarta.validation.Valid;

@RestController
public class WalletController {
  private final WalletService walletService;

  public WalletController(WalletService walletService) {
    this.walletService = walletService;
  }

  @PostMapping("/wallets")
  public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO dto) {
    var wallet = walletService.createWallet(dto);

    return ResponseEntity.ok(wallet);

  }

}
