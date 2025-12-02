/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author anton
 */
public class ClienteConsulta {
    private final int idCliente;
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final int telefono;
    private final double kg;

    public ClienteConsulta(int idCliente, String nombre, String apellido, String dni, int telefono, double kg) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.kg = kg;
}

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public double getKg() {
        return kg;
    }
    
    
}
