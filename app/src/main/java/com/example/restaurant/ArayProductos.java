package com.example.restaurant;

public class ArayProductos {
    Producto p = new Producto();
    String cantidad;
    int totalProducts;

    public ArayProductos() {
        cantidad = "";
        p = new Producto();

    }

    public ArayProductos(Producto p, String cantidad, int totalProducts) {
        this.p = p;
        this.cantidad = cantidad;
        this.totalProducts = totalProducts;
    }




    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public ArayProductos(Producto p, String cantidad) {

        this.p = p;
        this.cantidad = cantidad;
    }

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}