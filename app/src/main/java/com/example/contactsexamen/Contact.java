package com.example.contactsexamen;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "contacts_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)

    private long id;
    private String name;
    private String adress;
    private String number;
    private String image;

    public Contact() {
    }

    public Contact( String name, String adress, String number, String image) {
        this.name = name;
        this.adress = adress;
        this.number = number;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
