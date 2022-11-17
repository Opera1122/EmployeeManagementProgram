package src.jdbc.dto;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Employee {
    private int number;
    private String name;
    private int birthDate;
    private String address;
    private String email;
    private String tel;
    private String position;


    public Employee(int number, String name, int birthDate, String address, String email, String tel, String position) {
        this.number = number;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
