package me.skhull.compountinterestwebapplication.model;

import java.io.Serializable;
import java.util.Date;

// Java Bean

public class Requisitioner implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String pfNumber;
    private String description;
    private String unit;
    private Date formFilledDate;

    public Requisitioner() {
    }

    public Requisitioner(String name, String email, String phone, String description, String pfNumber, String unit) {
        this.pfNumber = pfNumber.trim().toUpperCase();
        this.unit = unit.trim();
        this.unit = this.unit.substring(0, 1).toUpperCase() + this.unit.substring(1);
        this.name = name.trim();
        this.name = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
        this.email = email.replace("@iitk.ac.in", "").trim()+"@iitk.ac.in";
        this.phone = phone.trim();
        this.description = description.trim();
        this.formFilledDate = new Date();
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

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void printRequisitioner() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("PF Number: " + pfNumber);
        System.out.println("Description: " + description);
        System.out.println("Unit: " + unit);
        System.out.println("Date Form Filled: " + formFilledDate);
    }

    public boolean validatePhone(String phone) {
        String phoneNum = phone
                .replace("+91", "")
                .replace("-", "")
                .trim();

        return phoneNum.matches("^[0-9]{10}$");
    }

    public boolean validateEmail(String email) {
        // Write Checks here for all the validation of the email
        // and the name and the unit/dept. matches

        return true;
    }

    public boolean validatePfNumber(String pfNumber) {
        if (pfNumber.length() != 22){
            return false;
        }

        return pfNumber.matches("^[a-zA-Z0-9]*$");

    }

    public boolean validateRequisitioner() {
        if (name == null || email == null || phone == null || pfNumber == null || description == null || unit == null ||
                name.isBlank() || email.isBlank() || phone.isBlank() || pfNumber.isBlank() || description.isBlank() ||
                unit.isBlank()) {
            return false;
        }

        return validateEmail(email) && validatePhone(phone) && validatePfNumber(pfNumber);
    }
}
