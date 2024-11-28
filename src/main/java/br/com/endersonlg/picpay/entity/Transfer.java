package br.com.endersonlg.picpay.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transfer")
@Data
@NoArgsConstructor
public class Transfer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "wallet_sender_id")
  private Wallet sender;

  @ManyToOne
  @JoinColumn(name = "wallet_receiver_id")
  private Wallet receiver;

  private BigDecimal value;

  public Transfer(Wallet sender, Wallet receiver, BigDecimal value) {
    this.sender = sender;
    this.receiver = receiver;
    this.value = value;
  }
}
