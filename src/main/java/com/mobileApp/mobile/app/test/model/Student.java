package com.mobileApp.mobile.app.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
   private int id;
   private String first;
   private String last;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first;
    }

    public void setFirstName(String firstName) {
        this.first = firstName;
    }

    public String getLastName() {
        return last;
    }

    public void setLastName(String lastName) {
        this.last = lastName;
    }
}
