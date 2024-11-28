package br.com.endersonlg.picpay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_wallet_type")
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @AllArgsConstructor
    public enum Enum {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        private Long id;
        private String description;

        public WalletType get() {
            return new WalletType(id, description);
        }

    }
}
