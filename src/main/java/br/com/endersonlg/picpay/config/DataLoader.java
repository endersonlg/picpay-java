package br.com.endersonlg.picpay.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.endersonlg.picpay.entity.WalletType;
import br.com.endersonlg.picpay.repositories.WalletTypeRepository;

@Configuration
public class DataLoader implements CommandLineRunner {

  private final WalletTypeRepository walletTypeRepository;

  public DataLoader(WalletTypeRepository walletTypeRepository) {
    this.walletTypeRepository = walletTypeRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Arrays.stream(WalletType.Enum.values()).forEach(walletType -> walletTypeRepository.save(walletType.get()));
  }
}
