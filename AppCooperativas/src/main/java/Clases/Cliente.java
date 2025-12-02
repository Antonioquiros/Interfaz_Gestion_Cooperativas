/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author anton
 */
public class Cliente {
    private final int idCliente;
    private final String Nombre;
    private final String Apellido;
    private final String DNI;
    private final int Telefono;
    
    public Cliente(int id, String Nombre, String Apellido, String DNI, int Telefono){
    
        this.idCliente = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.DNI = DNI;
        this.Telefono = Telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public int getTelefono() {
        return Telefono;
    }
    
}

