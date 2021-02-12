package com.example.restaurant;


import java.util.ArrayList;

public class Pedido {

    String numer_pedido;
    String mesa;
    String fecha;
    String estado;
    String descripcion;
    ArrayList<ArayProductos> ListadeProductos = new ArrayList<>();



    public ArrayList<ArayProductos> getListadeProductos() {
        return ListadeProductos;
    }

    public void setListadeProductos(ArrayList<ArayProductos> listadeProductos) {
        ListadeProductos = listadeProductos;


    }

    public Pedido(String mesa, ArrayList<ArayProductos> listadeProductos) {
        this.mesa = mesa;
        ListadeProductos = listadeProductos;
    }

    public Pedido() {
    }

    public Pedido(String numer_pedido, String mesa, String fecha, String estado, String descripcion) {
        this.numer_pedido = numer_pedido;
        this.mesa = mesa;
        this.fecha = fecha;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Pedido(String mesa, String fecha, String descripcion) {
        this.mesa = mesa;
        this.fecha = fecha;

        this.descripcion = descripcion;
    }

    public String getNumer_pedido() {
        return numer_pedido;
    }

    public void setNumer_pedido(String numer_pedido) {
        this.numer_pedido = numer_pedido;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}