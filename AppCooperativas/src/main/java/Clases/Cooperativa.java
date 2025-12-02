/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Time;
import java.util.List;

/**
 *
 * @author anton
 */
public class Cooperativa {
    
    private final int idCooperativa;
    private final String Ciudad;
    private final String Nombre;
    private final String Horario;
    private final double Kgtotales;
    private List<Consultas> consultas;

    
    public Cooperativa(int id,String Ciudad, String Nombre, String Horario, double kgTotales){
        this.idCooperativa = id;
        this.Ciudad = Ciudad;
        this.Nombre = Nombre;
        this.Horario = Horario;
        this.Kgtotales = kgTotales;
    }
    
    public List<Consultas> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consultas> consultas) {
        this.consultas = consultas;
    }

    public int getIdCooperativa() {
        return idCooperativa;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getHorario() {
        return Horario;
    }

    public double getKgtotales() {
        return Kgtotales;
    }

    public void setKgTotales(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}
