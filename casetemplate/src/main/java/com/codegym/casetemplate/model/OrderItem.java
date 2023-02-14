package com.codegym.casetemplate.model;

public class OrderItem {
    private Long id;
    private Long idProduct;
    private int quantiy;

    public OrderItem(Long id, Long idProduct, int quantiy) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantiy = quantiy;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }
}
