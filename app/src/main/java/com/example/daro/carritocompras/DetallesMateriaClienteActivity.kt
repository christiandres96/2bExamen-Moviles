package com.example.daro.carritocompras

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_pokemon_cliente.*

class DetallesMateriaClienteActivity : AppCompatActivity() {

    var materia: Materia? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idPokemon:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pokemon_cliente)

        materia = intent.getParcelableExtra("detallesPokemonCliente")



        txtShowNumeroPokemonn.text = materia?.numeroPokemon.toString()
        txtShowNombrePokemonn.text = materia?.nombre
        txtShowPoderUnoo.text = materia?.codigo
        txtShowPoderDoss.text = materia?.descripcion
        txtShowFechaCC.text = materia?.fechaInicio
        //txtShowNivell.text = materia?.nivel.toString()

        //Toast.makeText(this,materia?.id.toString(),Toast.LENGTH_SHORT).show()


        myBitmapAgain = decodeBase64(materia?.imagenProfesor.toString()!!)
        showImageViewPokemonn.setImageBitmap(myBitmapAgain)

        btnAdquirirPokemon.setOnClickListener { v ->
            irActividadDatosComprador()
        }


    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun irActividadDatosComprador(){
        val intent = Intent(this, DatosCompradorActivity::class.java)
        idPokemon = materia?.id.toString()
        intent.putExtra("idMateria",idPokemon)
        startActivity(intent)
    }


}
