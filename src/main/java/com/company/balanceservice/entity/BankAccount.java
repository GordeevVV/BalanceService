package com.company.balanceservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Account")
public class BankAccount {
    public BankAccount(long balance) {
        this.balance = balance;
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idSeq")
    @SequenceGenerator(name = "idSeq", initialValue = 1, allocationSize = 1, sequenceName = "ID_SEQUENCE")
    private long id;


    private long balance;
}
