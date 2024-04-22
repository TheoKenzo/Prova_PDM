package com.example.aplicativo_prova_pdm.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PedidoPamonha(var idPamonha: Int, var tipoDeRecheio: String, var pesoDeQueijo: Float, var fk_cpf: String): Parcelable {
    override fun toString(): String {
        return ("ID: $idPamonha | Recheio: $tipoDeRecheio | PesoQueijo: $pesoDeQueijo | fk_cpf: $fk_cpf")
    }
}