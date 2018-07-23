package com.example.daro.carritocompras

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class DatabaseMateria{
    companion object {

        fun insertarMateria(materia:Materia){
            "http://172.29.64.65:1337/Materia".httpPost(listOf("numeroHoras" to materia.numeroPokemon, "nombre" to materia.nombre, "codigo" to materia.codigo, "descripcion" to materia.descripcion, "fechaInicio" to materia.fechaInicio,"imagenProfesor" to materia.imagenProfesor,"estudianteId" to materia.idEstudiante ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarMateria(id: Int) {
            "http://172.29.64.65:1337/Materia/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarMateria(materia: Materia) {
            "http://172.29.64.65:1337/Materia/${materia.id}".httpPut(listOf("numeroHoras" to materia.numeroPokemon, "nombre" to materia.nombre, "codigo" to materia.codigo, "descripcion" to materia.descripcion, "fechaInicio" to materia.fechaInicio,  "imagenProfesor" to materia.imagenProfesor))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getMateriaList(entrenadorId: Int): ArrayList<Materia> {
            val materia: ArrayList<Materia> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.65:1337/Materia?estudianteId=$entrenadorId".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val numeroHoras = it["numeroHoras"] as Int
                val nombre = it["nombre"] as String
                val codigo = it["codigo"] as String
                val descripcion = it["descripcion"] as String
                val fechaInicio = it["fechaInicio"] as String
                //val nivel = it["nivel"] as Int
                val imagenProfesor = it["imagenProfesor"] as String
                //val latitud = it["latitud"] as Double
               // val longitud = it["longitud"] as Double
                val materiaa = Materia(id,numeroHoras,nombre,codigo,descripcion,fechaInicio,imagenProfesor,entrenadorId,0,0)
                materia.add(materiaa)
            }
            return materia
        }




    }
}