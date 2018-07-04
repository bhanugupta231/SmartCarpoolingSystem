package com.example.abc.smartcarpoolingsystem;

import java.io.Serializable;

/**
 * Created by abc on 3/29/2018.
 */

public class ContactListBean implements Serializable {
    String id,number,name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
