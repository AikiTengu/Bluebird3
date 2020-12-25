package com.firebase.Bluebird3.model;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class BBUser {
    @NotNull
    private String org_id;
    @NotNull
    private String email;
    private String address;
    private String note;
    private Integer numberfield;
    ArrayList<String> references;


    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumberfield() {
        return numberfield;
    }

    public void setNumberfield(Integer numberfield) {
        this.numberfield = numberfield;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
}
