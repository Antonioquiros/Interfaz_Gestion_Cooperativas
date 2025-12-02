/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Objects;

/**
 *
 * @author anton
 */
public class Cliente {
    private final int idCliente;
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final int telefono;

    public Cliente(int id, String nombre, String apellido, String dni, int telefono){
        this.idCliente = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
    }
    public Cliente(String Nombre, String Apellido, String DNI, int Telefono){
        this.idCliente = 0;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.dni = DNI;
        this.telefono = Telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDNI() {
        return dni;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idCliente == cliente.idCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }
}

