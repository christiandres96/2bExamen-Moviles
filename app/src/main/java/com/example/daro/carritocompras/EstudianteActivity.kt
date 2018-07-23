package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_entrenador.*

class EstudianteActivity : AppCompatActivity() {

    var estudiante: Estudiante? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewEntreador.text = "Editar Entrenador"
            estudiante = intent.getParcelableExtra("entrenador")
            fillFields()
            tipo = true
        }

        btnGuardarEntrenador.setOnClickListener { v: View? ->
            crearEstudiante()
        }
    }

    fun fillFields() {
        txtNombreEntrenador.setText(estudiante?.nombre)
        txtApellidoEntrenador.setText(estudiante?.apellido)
        txtFechaNacimientoEntrenador.setText(estudiante?.fechaNacimiento)
        txtNumeroMedallasEntrenador.setText(estudiante?.semestre.toString())
        if (estudiante?.graduado == 1) {
            switchCampeonEntrenador.toggle()
        }
    }

    fun crearEstudiante(){
        var nombre = txtNombreEntrenador.text.toString()
        var apellido = txtApellidoEntrenador.text.toString()
        var fecha = txtFechaNacimientoEntrenador.text.toString()
        var numeroMedallas = txtNumeroMedallasEntrenador.text.toString().toInt()
        var campeon = if (switchCampeonEntrenador.isChecked) 1 else 0


        if (!tipo){

            var estudiante = Estudiante(0, nombre, apellido, fecha, numeroMedallas, campeon,0,0)
            DatabaseEstudiante.insertarEstudiante(estudiante)

        }else{
            var estudiante = Estudiante(estudiante?.id!!, nombre, apellido, fecha, numeroMedallas, campeon,0,0)
            DatabaseEstudiante.actualizarEstudiante(estudiante)
        }

        //Log.d("print",DatabaseEntrenador.getList().toString())
        //print(DatabaseEntrenador.getList())
        irListarEstudianteActivity()

    }

    fun irListarEstudianteActivity(){
        val intent = Intent(this, ListarEstudiantesActivity::class.java)
        startActivity(intent)
    }
}
