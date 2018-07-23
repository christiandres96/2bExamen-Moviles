package com.example.daro.carritocompras

import android.os.Parcel
import android.os.Parcelable

class Materia(var id: Int, var numeroPokemon: Int, var nombre: String, var codigo: String, var descripcion: String, var fechaInicio: String, var imagenProfesor:String, var idEstudiante:Int, var createdAt: Long,
              var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(numeroPokemon)
        parcel.writeString(nombre)
        parcel.writeString(codigo)
        parcel.writeString(descripcion)
        parcel.writeString(fechaInicio)
        parcel.writeString(imagenProfesor)
        parcel.writeInt(idEstudiante)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Materia> {
        override fun createFromParcel(parcel: Parcel): Materia {
            return Materia(parcel)
        }

        override fun newArray(size: Int): Array<Materia?> {
            return arrayOfNulls(size)
        }
    }
}
