package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_detalles_entrenador.*

class DetallesEstudianteActivity : AppCompatActivity() {

    var estudiante: Estudiante? = null
    lateinit var materia: ArrayList<Materia>
    lateinit var adaptador: MateriaAdapter
    //lateinit var codigoBotonoActivar:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_entrenador)



        estudiante = intent.getParcelableExtra("detallesEntrenador")
        //codigoBotonoActivar = intent.getStringExtra("codigoBotonoActivar")

        //Toast.makeText(this,codigoBotonoActivar,Toast.LENGTH_SHORT).show()

        txtShowIdEntrenador.text = estudiante?.id.toString()
        txtShowNombreEntrenador.text = estudiante?.nombre
        txtShowApellidoEntrenador.text = estudiante?.apellido
        txtShowFechaNEntrenador.text = estudiante?.fechaNacimiento
        txtShowNMedallasEntrenador.text = estudiante?.semestre.toString()
        txtShowCampeonEntrenador.text = if(estudiante?.graduado == 1) "Si" else "No"



        materia = DatabaseMateria.getMateriaList(estudiante?.id!!)
       Log.d("resultado",materia.toString())

       val layoutManager = LinearLayoutManager(this)
        adaptador = MateriaAdapter(materia)
        recycler_view_pokemon.layoutManager = layoutManager
        recycler_view_pokemon.itemAnimator = DefaultItemAnimator()
        recycler_view_pokemon.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_pokemon)

        btnNuevoPokemon.setOnClickListener { v: View? ->
            irActividdadCrearMateria()
        }
    }

    fun irActividdadCrearMateria(){
        val intent = Intent(this, MateriaActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("idEstudiante", estudiante?.id!!)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var materian = materia[position]

        when (item.itemId) {
        /*R.id.item_menu_compartir -> {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.autor)} - ${getString(R.string.app_name)}")
            intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.name)} ${autor.nombre} ${autor.apellido}\n${getString(R.string.numero_libros)} ${autor.semestre}\n${getString(R.string.fecha_nacimiento)} ${autor.fechaNacimiento}")
            startActivity(intent)
            return true
        }*/
            R.id.item_menu_editar -> {
                val intent = Intent(this, MateriaActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("materia", materian)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseMateria.eliminarMateria(materian.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
