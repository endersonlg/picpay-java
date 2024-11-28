package br.com.endersonlg.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.endersonlg.picpay.controller.dto.TransferDTO;
import br.com.endersonlg.picpay.entity.Transfer;
import br.com.endersonlg.picpay.service.TransferService;
import jakarta.validation.Valid;

@RestController
public class TransferController {
  private final TransferService transferService;

  public TransferController(TransferService transferService) {
    this.transferService = transferService;
  }

  @PostMapping("/transfer")
  public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO transferDTO) {
    var resp = transferService.transfer(transferDTO);

    return ResponseEntity.ok(resp);
  }

}
