package com.codegym.casetemplate.model;

import java.util.List;

public class OrderDTO {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private double total;

    private List<OrderItemDTO> oderItemDTOS;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String name, String phone, String address,double total, List<OrderItemDTO> oderItemDTOS) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.oderItemDTOS = oderItemDTOS;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItemDTO> getOderItemDTOS() {
        return oderItemDTOS;
    }

    public void setOderItemDTOS(List<OrderItemDTO> oderItemDTOS) {
        this.oderItemDTOS = oderItemDTOS;
    }
}
