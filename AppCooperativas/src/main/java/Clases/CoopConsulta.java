/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author anton
 */
public class CoopConsulta {
    
    private final int idCooperativa;
    private final String ciudad;
    private final String nombre;
    private final String horario;
    private final double Kgtotales;
    private final double kg;

    public CoopConsulta(int idCooperativa, String ciudad, String nombre, String horario, double Kgtotales, double kg) {
        this.idCooperativa = idCooperativa;
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.horario = horario;
        this.Kgtotales = Kgtotales;
        this.kg = kg;
    }

    public int getIdCooperativa() {
        return idCooperativa;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public double getKgtotales() {
        return Kgtotales;
    }

    public double getKg() {
        return kg;
    }
    
    
    
}
