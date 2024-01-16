package com.example.citycard;

import java.io.Serializable;

<<<<<<< HEAD
public class Card implements Serializable { //this class represent city card objects
    private String id;    //unique identifier for the card
    private Integer balance;   //current balance on the card
                                //these variables is proper for how they represent in Firebase
=======
public class Card implements Serializable {
    private String id;
    private Integer balance;


>>>>>>> 8982bfceda05d081601215c010d7e476194a222a
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
