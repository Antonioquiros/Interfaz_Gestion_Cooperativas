/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apirest_ejemplo.apirest;

import Clases.Clientes;
import Clases.OperacionCliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 *
 * @author Antonio
 * 
 * Habrá que configurar la interface según hayamos construido la API
 * En este caso hay que pasar las cabeceras que indica la API, construir los datos JSON y 
 * utilizar el método indicado en la API: POST
 */
public interface MiAPIServicioBorrar {

    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    @POST("borrar.php")
     Call<OperacionCliente> borrarDato(@Body OperacionCliente a);
}
