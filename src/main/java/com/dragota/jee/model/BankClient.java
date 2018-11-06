package com.dragota.jee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class BankClient  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private BigDecimal balance;
    private boolean premierCursomer;

    public BankClient() {}

    public BankClient(String name, BigDecimal balance, boolean premierCursomer) {
        this.name = name;
        this.balance = balance;
        this.premierCursomer = premierCursomer;
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
                ", balance=" + balance +
                ", premierCursomer=" + premierCursomer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankClient that = (BankClient) o;
        return getId() == that.getId() &&
                isPremierCursomer() == that.isPremierCursomer() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getBalance(), that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBalance(), isPremierCursomer());
    }
}

