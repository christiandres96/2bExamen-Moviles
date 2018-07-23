package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buscar_entrenador.*

class BuscarEstudianteActivity : AppCompatActivity() {

    lateinit var adaptador: EstudianteClienteAdapter
    lateinit var estudiante: ArrayList<Estudiante>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_entrenador)

        estudiante = DatabaseEstudiante.getList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = EstudianteClienteAdapter(estudiante)
        recyclerView_Resultados_Estudiante.layoutManager = layoutManager
        recyclerView_Resultados_Estudiante.itemAnimator = DefaultItemAnimator()
        recyclerView_Resultados_Estudiante.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_Resultados_Estudiante)


        btnBuscarEntrenadorServer.setOnClickListener { v: View? ->
            consultarDatos()
        }

        btnListarclienteXD.setOnClickListener { v: View? ->
            listarXD()
        }


    }

    fun listarXD(){
        estudiante = DatabaseEstudiante.getList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = EstudianteClienteAdapter(estudiante)
        recyclerView_Resultados_Estudiante.layoutManager = layoutManager
        recyclerView_Resultados_Estudiante.itemAnimator = DefaultItemAnimator()
        recyclerView_Resultados_Estudiante.adapter = adaptador
    }

    fun consultarDatos(){
        if (txtBuscarEntrenador.equals("")){


            Toast.makeText(this,"Ingrese parametro de busqueda",Toast.LENGTH_SHORT).show()

        }else{
            var datoBusqueda:String = txtBuscarEntrenador.text.toString()
            

            estudiante = DatabaseEstudiante.buscarEstudiante(datoBusqueda)

            //Toast.makeText(this,datoBusqueda,Toast.LENGTH_SHORT).show()

            val layoutManager = LinearLayoutManager(this)
            adaptador = EstudianteClienteAdapter(estudiante)
            recyclerView_Resultados_Estudiante.layoutManager = layoutManager
            recyclerView_Resultados_Estudiante.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Estudiante.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Estudiante)

        }
    }




}
