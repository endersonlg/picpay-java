package br.com.endersonlg.picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.endersonlg.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {

}
