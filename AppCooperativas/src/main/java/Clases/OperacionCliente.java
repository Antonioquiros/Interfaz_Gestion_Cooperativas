/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;

/**
 *
 * @author anton
 */
public class OperacionCliente {
    
    private List<Cliente> clientes;
    private Cliente cliente;

    private Result result;
    public OperacionCliente(int id, String nombre, String apellido, String dni, int telefono) {
        this.cliente = new Cliente(id, nombre, apellido, dni, telefono);
    }
    
    public OperacionCliente(String nombre, String apellido, String dni, int telefono) {
        this.cliente = new Cliente(nombre, apellido, dni, telefono);
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.cliente = Cliente;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


}
