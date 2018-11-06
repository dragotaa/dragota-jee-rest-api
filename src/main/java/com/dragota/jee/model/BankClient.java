package com.dragota.jee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class BankClient  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private boolean premierCursomer;
    private BigDecimal balance;

    public BankClient() {}

    public BankClient(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String bankClientName) {
        this.name = bankClientName;
    }

    public boolean isPremierCursomer() {
        return premierCursomer;
    }

    public void setPremierCursomer(boolean premierCursomer) {
        this.premierCursomer = premierCursomer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", premierCursomer=" + (premierCursomer?"Premier Customer":"Standard Customer") +
                ", balance=" + balance +
                '}';
    }
}

