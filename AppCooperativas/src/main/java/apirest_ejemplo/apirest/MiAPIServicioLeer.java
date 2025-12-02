/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apirest_ejemplo.apirest;

import Clases.OperacionCliente;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 *
 * @author Antonio
 *
 * Habrá que configurar la interface según hayamos construido la API
 * En este caso hay que pasar las cabeceras que indica la API y utilizar el método indicado en la API: GET
 */
public interface MiAPIServicioLeer {
    @GET("leer.php") //Usa el método GET
    Call<OperacionCliente> getCliente(@Query("idCliente") int id);

    @Headers({
            "Accept: application/json"
    })
    @GET("leerJson.php") //Usa el método GET
    Call<OperacionCliente> getClientes();
}