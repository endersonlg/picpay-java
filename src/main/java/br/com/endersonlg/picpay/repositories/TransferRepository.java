package br.com.endersonlg.picpay.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.endersonlg.picpay.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {

}
