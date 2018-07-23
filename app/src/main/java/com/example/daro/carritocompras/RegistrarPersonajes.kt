package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_registrar_personajes.*


class RegistrarPersonajes : AppCompatActivity() {

    lateinit var rol: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_personajes)


        rol = intent.getStringExtra("valorRol")

        //Toast.makeText(this,"Rol es $rol", Toast.LENGTH_SHORT).show()

        if (rol.equals("VENDEDOR", true)) {
            //Toast.makeText(this,"Deshabilito boton", Toast.LENGTH_SHORT).show()
            btnBuscarEntrenador.visibility = View.INVISIBLE
            btnPedidos.visibility = View.INVISIBLE


        } else if (rol.equals("DELIVERY", true)) {
            btnBuscarEntrenador.visibility = View.INVISIBLE
            btnCrearEntrenador.visibility = View.INVISIBLE
            btnListarEntrenador.visibility = View.INVISIBLE
            btnPedidos.visibility = View.INVISIBLE
            irActividadDelivery()

        } else {
            //btnCrearEntrenador.visibility = View.INVISIBLE
            btnBuscarEntrenador.visibility = View.VISIBLE
            btnCrearEntrenador.visibility = View.INVISIBLE
            btnListarEntrenador.visibility = View.INVISIBLE
            btnPedidos.visibility = View.VISIBLE
        }


        btnCrearEntrenador.setOnClickListener { v: View? ->
            irEntrenadorActivity()
        }

        btnListarEntrenador.setOnClickListener { v: View? ->
            irListarEntrenadorActivity()
        }

        btnBuscarEntrenador.setOnClickListener { v: View? ->
            irBuscarEntrenadorActivity()
        }
        btnPedidos.setOnClickListener { v: View? ->
            irActividadDelivery()


        }}

        fun irEntrenadorActivity() {
            val intent = Intent(this, EstudianteActivity::class.java)
            intent.putExtra("tipo", "Create")
            startActivity(intent)
        }

        fun irListarEntrenadorActivity() {
            val intent = Intent(this, ListarEstudiantesActivity::class.java)
            startActivity(intent)
        }

        fun irActividadDelivery() {
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)

        }

        fun irBuscarEntrenadorActivity() {
            val intent = Intent(this, BuscarEstudianteActivity::class.java)
            startActivity(intent)

        }


    }
