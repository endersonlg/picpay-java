package br.com.endersonlg.picpay.service;

import org.springframework.stereotype.Service;

import br.com.endersonlg.picpay.client.AuthorizationClient;
import br.com.endersonlg.picpay.controller.dto.TransferDTO;
import br.com.endersonlg.picpay.exception.PicPayException;

@Service
public class AuthorizationService {
  private final AuthorizationClient authorizationClient;

  public AuthorizationService(AuthorizationClient authorizationClient) {
    this.authorizationClient = authorizationClient;
  }

  public boolean isAuthorized(TransferDTO Transfer) {
    var resp = authorizationClient.isAuthorized();

    if (resp.getStatusCode().isError()) {
      throw new PicPayException();
    }

    return resp.getBody().authorized();
  }
}
