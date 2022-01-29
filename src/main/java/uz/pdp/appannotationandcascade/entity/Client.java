package uz.pdp.appannotationandcascade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String phoneNumber;

    @OneToOne(mappedBy = "client", cascade = CascadeType.PERSIST) //mappedBy ni qo'yish orqali Client jadvalida qo'shimcha yana bir ustun qo'shilishini oldini olamz
    private BankAccount bankAccount;

    public void setBankAccount(BankAccount bankAccount) {
        bankAccount.setClient(this);
        this.bankAccount = bankAccount;
    }
}
