/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author anton
 */
public class Clientes {
    private final int idCliente;
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final int telefono;

    public Clientes(int id, String Nombre, String Apellido, String DNI, int Telefono){
        this.idCliente = id;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.dni = DNI;
        this.telefono = Telefono;
    }
    public Clientes(String Nombre, String Apellido, String DNI, int Telefono){
        this.idCliente = 0;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.dni = DNI;
        this.telefono = Telefono;
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
}
