<<<<<<< HEAD
=======
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
>>>>>>> 9d312f1e4afeb6f84403b08102a04cfc95cda8a7
