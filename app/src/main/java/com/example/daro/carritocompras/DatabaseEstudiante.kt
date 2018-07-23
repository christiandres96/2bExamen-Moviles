package com.example.daro.carritocompras

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.*

class DatabaseEstudiante{

    companion object {

        fun insertarEstudiante(estudiante:Estudiante){
            "http://172.29.64.65:1337/Estudiante".httpPost(listOf("nombre" to estudiante.nombre, "apellido" to estudiante.apellido, "fechaNacimiento" to estudiante.fechaNacimiento, "semestre" to estudiante.semestre, "graduado" to estudiante.graduado))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarEstudiante(id: Int) {
            "http://172.29.64.65:1337/Estudiante/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarEstudiante(estudiante: Estudiante) {
            "http://172.29.64.65:1337/Estudiante/${estudiante.id}".httpPut(listOf("nombre" to estudiante.nombre, "apellido" to estudiante.apellido, "fechaNacimiento" to estudiante.fechaNacimiento, "semestre" to estudiante.semestre, "graduado" to estudiante.graduado))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Estudiante> {
            val estudiantes: ArrayList<Estudiante> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.65:1337/Estudiante".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val semestre = it["semestre"] as Int
                val graduado = it["graduado"] as Int
                val estudiante = Estudiante(id, nombre, apellido, fechaNacimiento, semestre, graduado, 0, 0)
                estudiantes.add(estudiante)
            }
            return estudiantes
        }

        fun buscarEstudiante(nombre:String): ArrayList<Estudiante> {
            val estudiantes: ArrayList<Estudiante> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.65:1337/Estudiante?nombre=${nombre}".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val semestre = it["semestre"] as Int
                val graduado = it["graduado"] as Int
                val estudiante = Estudiante(id, nombre, apellido, fechaNacimiento, semestre, graduado, 0, 0)
                estudiantes.add(estudiante)
            }
            return estudiantes
        }

    }
}