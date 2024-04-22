package com.example.aplicativo_prova_pdm.controller

import android.content.ContentValues
import android.content.Context
import com.example.aplicativo_prova_pdm.model.PedidoPamonha
import com.example.aplicativo_prova_pdm.model.dao.BancoDAO

class ControllerPedidoPamonha(context: Context) {
    private val DAO: BancoDAO = BancoDAO(context)

    fun selectAll(): ArrayList<PedidoPamonha>? {
        val pedidosPamonha: ArrayList<PedidoPamonha> = ArrayList()
        val funLeitura = DAO.readableDatabase

        val cursor = funLeitura.rawQuery("SELECT * FROM PedidoPamonha", null)

        if (cursor.moveToFirst()) {
            do {
                val idPamonha = cursor.getInt(cursor.getColumnIndexOrThrow("idPamonha"))
                val tipoDeRecheio = cursor.getString(cursor.getColumnIndexOrThrow("tipoDeRecheio"))
                val pesoDeQueijo = cursor.getFloat(cursor.getColumnIndexOrThrow("pesoDeQueijo"))
                val fk_cpf = cursor.getString(cursor.getColumnIndexOrThrow("fk_cpf"))

                pedidosPamonha.add(PedidoPamonha(idPamonha, tipoDeRecheio, pesoDeQueijo, fk_cpf))
            } while (cursor.moveToNext())
        }

        cursor.close()
        funLeitura.close()

        return if(pedidosPamonha.isEmpty())
            null
        else
            pedidosPamonha
    }

    fun insert(pedidoPamonha: PedidoPamonha): Boolean {
        val funEscrita = DAO.writableDatabase

        val cvNovoPedidoPamonha = ContentValues().apply {
            put("idPamonha", pedidoPamonha.idPamonha)
            put("tipoDeRecheio", pedidoPamonha.tipoDeRecheio)
            put("pesoDeQueijo", pedidoPamonha.pesoDeQueijo)
            put("fk_cpf", pedidoPamonha.fk_cpf)
        }

        val confirmacaoInsercao = funEscrita.insert("PedidoPamonha", null, cvNovoPedidoPamonha)
        funEscrita.close()

        return confirmacaoInsercao != -1L
    }

    fun update(pedidoPamonha: PedidoPamonha): Boolean {
        val funEscrita = DAO.writableDatabase

        val cvAtualizacaoPedidoPamonha = ContentValues().apply {
            put("idPamonha", pedidoPamonha.idPamonha)
            put("tipoDeRecheio", pedidoPamonha.tipoDeRecheio)
            put("pesoDeQueijo", pedidoPamonha.pesoDeQueijo)
            put("fk_cpf", pedidoPamonha.fk_cpf)
        }

        val confirmaAtualizacao = funEscrita.update("PedidoPamonha", cvAtualizacaoPedidoPamonha, "idPamonha = ?", arrayOf(pedidoPamonha.idPamonha.toString()))

        funEscrita.close()
        return confirmaAtualizacao > 0
    }

    fun deleteByCPF(fk_cpf: String): Boolean{
        val funEscrita = DAO.writableDatabase

        val confirmaExclusao = funEscrita.delete("PedidoPamonha", "fk_cpf = ?", arrayOf(fk_cpf))

        funEscrita.close()

        return confirmaExclusao > 0
    }

    fun existePedidoPamonha(fk_cpf: String): Boolean{
        val listaPedidosPamonha = selectAll()

        return listaPedidosPamonha?.filter { it.fk_cpf == fk_cpf }.orEmpty().isEmpty()
    }
}