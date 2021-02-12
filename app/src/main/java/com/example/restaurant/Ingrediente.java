package com.example.restaurant;

public class Ingrediente {
    private String ingrediente_id;
    private String ingrediente_nombre;
    private int ingrediente_cantidad;
    private String ingrediente_unidad;

    public String getIngrediente_id() {
        return ingrediente_id;
    }

    public String getIngrediente_nombre() {
        return ingrediente_nombre;
    }

    public int getIngrediente_cantidad() {
        return ingrediente_cantidad;
    }

    public Ingrediente() {
    }

    public String getIngrediente_unidad() {
        return ingrediente_unidad;
    }

    public void setIngrediente_unidad(String ingrediente_unidad) {
        this.ingrediente_unidad = ingrediente_unidad;
    }

    public Ingrediente(String ingrediente_id, String ingrediente_nombre, int ingrediente_cantidad) {
        this.ingrediente_id = ingrediente_id;
        this.ingrediente_nombre = ingrediente_nombre;
        this.ingrediente_cantidad = ingrediente_cantidad;
    }

}
