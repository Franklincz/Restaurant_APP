package com.example.restaurant;

public class Empleado {

    String nombres;
    String apellidos;
    String dni;
    String gmail;
    String img;
    String tipoEmpleado;
    String direccion;
    String estado;
    String password;


    public Empleado(String nombres, String apellidos, String dni, String direccion, String tipoEmpleado, String estado, String gmail, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.tipoEmpleado = tipoEmpleado;
        this.direccion = direccion;
        this.estado = estado;
        this.password = password;
        this.gmail = gmail;
    }

    public Empleado() {

    }


    public Empleado(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Empleado(String nombres, String apellidos, String gmail, String password, String estado) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.gmail = gmail;
        this.apellidos = password;
        this.estado = estado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}


