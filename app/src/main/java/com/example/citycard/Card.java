package com.example.citycard;

import java.io.Serializable;

public class Card implements Serializable {
    private String id;
    private Integer balance;

    // Boş parametreli constructor
    public Card() {
    }

    // Parametreli constructor
    public Card(String id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }

    // Getter ve setter metotları
    public String getId() {
        return id;
    }

    public Integer getBalance() {
        return balance;
    }

}
