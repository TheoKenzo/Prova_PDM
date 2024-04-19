package com.example.aplicativo_prova_pdm.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PedidoPamonha(private var idPamonha: Int, private var tipoDeRecheio: String, private var pesoDeQueijo: Float, private var fk_cpf: String): Parcelable {
    override fun toString(): String {
        return ("ID: $idPamonha | Recheio: $tipoDeRecheio | PesoQueijo: $pesoDeQueijo")
    }
}