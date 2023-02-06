package model;

import java.util.Date;

public class Customer {
    private Long id;
    private String name;
    private Date createdAt;
    private String address;
    private String image;

    public Customer(String name, Date createdAt, String address, String image) {
        this.name = name;
        this.createdAt = createdAt;
        this.address = address;
        this.image = image;
    }

    public Customer(Long id, String name, Date createdAt, String address, String image) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.address = address;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
