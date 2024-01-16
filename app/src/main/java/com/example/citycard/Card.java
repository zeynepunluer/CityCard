package com.example.citycard;

import java.io.Serializable;

public class Card implements Serializable {
    private String id;
    private Integer balance;
    public Card() {
    }

    public Card(String id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }
    public String getId() {
        return id;
    }
    public Integer getBalance() {
        return balance;
    }

}
