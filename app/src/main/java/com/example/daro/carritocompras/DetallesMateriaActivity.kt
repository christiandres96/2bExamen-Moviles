package com.example.daro.carritocompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_pokemon.*


class DetallesMateriaActivity : AppCompatActivity() {

    var materia: Materia? = null
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pokemon)

        materia = intent.getParcelableExtra("detallesPokemon")


        txtShowNumeroPokemon.text = materia?.numeroPokemon.toString()
        txtShowNombrePokemon.text = materia?.nombre
        txtShowPoderUno.text = materia?.codigo
        txtShowPoderDos.text = materia?.descripcion
        txtShowFechaC.text = materia?.fechaInicio
       // txtShowNivel.text = materia?.nivel.toString()

        myBitmapAgain = decodeBase64(materia?.imagenProfesor.toString()!!)
        showImageViewPokemon.setImageBitmap(myBitmapAgain)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
