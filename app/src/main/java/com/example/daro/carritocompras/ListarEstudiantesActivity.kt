package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_listar_entrenadores.*

class ListarEstudiantesActivity : AppCompatActivity() {

    lateinit var adaptador: EstudianteAdapter
    lateinit var estudiantes: ArrayList<Estudiante>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_entrenadores)

        estudiantes = DatabaseEstudiante.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = EstudianteAdapter(estudiantes)
        recyclerViewEntrenador.layoutManager = layoutManager
        recyclerViewEntrenador.itemAnimator = DefaultItemAnimator()
        recyclerViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerViewEntrenador)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var estudiante = estudiantes[position]

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
                val intent = Intent(this, EstudianteActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("entrenador", estudiante)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseEstudiante.eliminarEstudiante(estudiante.id)
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
