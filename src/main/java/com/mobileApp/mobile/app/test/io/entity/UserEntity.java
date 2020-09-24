package com.mobileApp.mobile.app.test.io.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserEntity implements Serializable{
    private static final long serialVersionUID = 7968883935095306731L;

    @Id
    @GeneratedValue
    private long id;

}
