package com.ruba.borrowbox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

@Entity
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String location;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy ="owner")
    private List<Item> item=new ArrayList<>();

    public User(){
    }
    public User(String name, String email, String phone, String location){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.location=location;
    }

    public Integer getId(){
        return id;
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}