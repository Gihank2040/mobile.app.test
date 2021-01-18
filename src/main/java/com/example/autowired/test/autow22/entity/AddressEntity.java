package com.example.autowired.test.autow22.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1048411314640320550L;

    @Id
    @GeneratedValue
    private long id;

    //It is using security purpose, if we use above id any one can guss user address id
    //@Column(nullable = false)
    private String addressId;

   // @Column(nullable = false)
    private String city;

   // @Column(nullable = false)
    private String country;

  //  @Column(nullable = false)
    private String streetName;

  //  @Column(nullable = false)
    private String postalCode;

  //  @Column(nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userEntity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
