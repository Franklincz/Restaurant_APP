package com.example.restaurant;

import java.io.Serializable;

public class Producto implements Serializable {

    String id;
    String nombre;
    Double precio;
    String descripcion;
    int stock;
    String imgProduct;

    public Producto() {
        nombre = "";
        precio = null;
        stock = 0;
        descripcion = "";
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public Producto(int stock) {

        this.stock = stock;

    }

    public Producto(String id, String nombre, Double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String id, String nombre, Double precio, String descripcion, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public Producto(String nombre, Double precio, String id) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}