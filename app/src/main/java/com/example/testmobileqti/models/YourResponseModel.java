package com.example.testmobileqti.models;

import com.google.gson.annotations.SerializedName;

public class YourResponseModel {@SerializedName("id")
private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    // Constructor
    public YourResponseModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getter dan setter (atau dapat menggunakan plugin generate dalam Android Studio)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}