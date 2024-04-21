package com.example.aplicativo_prova_pdm.controller

import Cliente
import ClienteDAO
import android.content.ContentValues
import android.content.Context

class ControllerCliente(context: Context) {
    private val DAO: ClienteDAO = ClienteDAO(context)

    fun selectAll(): ArrayList<Cliente>? {
        val clientes: ArrayList<Cliente> = ArrayList()
        val funLeitura = DAO.readableDatabase

        val cursor = funLeitura.rawQuery("SELECT * FROM Cliente", null)

        if (cursor.moveToFirst()) {
            do {
                val cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"))
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
                val telefone = cursor.getString(cursor.getColumnIndexOrThrow("telefone"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))

                clientes.add(Cliente(cpf, nome, telefone, email))
            } while (cursor.moveToNext())
        }

        cursor.close()
        funLeitura.close()

        return if(clientes.isEmpty())
            null
        else
            clientes
    }

    fun insert(cliente: Cliente): Boolean {
        val funEscrita = DAO.writableDatabase

        val cvNovoCliente = ContentValues().apply {
            put("cpf", cliente.cpf)
            put("nome", cliente.nome)
            put("telefone", cliente.telefone)
            put("email", cliente.email)
        }

        val confirmacaoInsercao = funEscrita.insert("Cliente", null, cvNovoCliente)
        funEscrita.close()

        return confirmacaoInsercao != -1L
    }

    fun update(cliente: Cliente): Boolean {
        val funEscrita = DAO.writableDatabase

        val cvAtualizacaoCliente = ContentValues().apply {
            put("cpf", cliente.cpf)
            put("nome", cliente.nome)
            put("telefone", cliente.telefone)
            put("email", cliente.email)
        }

        val confirmaAtualizacao = funEscrita.update("Cliente", cvAtualizacaoCliente, "cpf = ?", arrayOf(cliente.cpf))

        funEscrita.close()
        return confirmaAtualizacao > 0
    }

    fun existeCliente(cpf: String): Boolean{
        val listaClientes = selectAll()

        return listaClientes?.filter { it.cpf == cpf }.orEmpty().isEmpty()
    }
}