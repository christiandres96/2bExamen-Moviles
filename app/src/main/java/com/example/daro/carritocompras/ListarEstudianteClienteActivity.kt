package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_listar_entrenador_cliente.*

class ListarEstudianteClienteActivity : AppCompatActivity() {

    lateinit var adaptador: EstudianteClienteAdapter
    lateinit var estudiantes: ArrayList<Estudiante>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_entrenador_cliente)

        estudiantes = DatabaseEstudiante.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = EstudianteClienteAdapter(estudiantes)
        recyclerView_listarEntrenadores_Clientes.layoutManager = layoutManager
        recyclerView_listarEntrenadores_Clientes.itemAnimator = DefaultItemAnimator()
        recyclerView_listarEntrenadores_Clientes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_listarEntrenadores_Clientes)
    }
}
