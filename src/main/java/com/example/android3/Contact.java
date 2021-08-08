package com.example.android3;

public class Contact {

    String name , number ;
    String image ;

    public Contact( String image ,String name, String number) {
        this.name = name;
        this.number = number;
        this.image =image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
