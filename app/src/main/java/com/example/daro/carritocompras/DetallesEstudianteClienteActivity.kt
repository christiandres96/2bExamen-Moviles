package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_detalles_entrenador.*

class DetallesEstudianteClienteActivity : AppCompatActivity() {

    var estudiante: Estudiante? = null
    lateinit var pokemon: ArrayList<Materia>
    lateinit var adaptador: MateriaClienteAdapter
    //lateinit var codigoBotonoActivar:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_entrenador_cliente)

        estudiante = intent.getParcelableExtra("detallesEntrenadorCliente")
        //codigoBotonoActivar = intent.getStringExtra("codigoBotonoActivar")

        //Toast.makeText(this,codigoBotonoActivar,Toast.LENGTH_SHORT).show()

        txtShowIdEntrenador.text = estudiante?.id.toString()
        txtShowNombreEntrenador.text = estudiante?.nombre
        txtShowApellidoEntrenador.text = estudiante?.apellido
        txtShowFechaNEntrenador.text = estudiante?.fechaNacimiento
        txtShowNMedallasEntrenador.text = estudiante?.semestre.toString()
        txtShowCampeonEntrenador.text = if(estudiante?.graduado == 1) "Si" else "No"



        pokemon = DatabaseMateria.getMateriaList(estudiante?.id!!)
        Log.d("resultado",pokemon.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = MateriaClienteAdapter(pokemon)
        recycler_view_pokemon.layoutManager = layoutManager
        recycler_view_pokemon.itemAnimator = DefaultItemAnimator()
        recycler_view_pokemon.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_pokemon)
    }
}
