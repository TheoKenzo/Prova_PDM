package com.example.aplicativo_prova_pdm.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoDAO(context: Context) : SQLiteOpenHelper(context, "fabricaPamonhas", null, 1) {
    private val nomeTabela1 = "Cliente"
    private val nomeTabela2 = "PedidoPamonha"

    override fun onCreate(db: SQLiteDatabase?){
        var SQL_criacao1 =
            """
                CREATE TABLE IF NOT EXISTS $nomeTabela1(
                    cpf TEXT PRIMARY KEY,
                    nome TEXT NOT NULL,
                    telefone TEXT NOT NULL,
                    email TEXT NOT NULL
                )
            """.trimIndent()

        db?.execSQL(SQL_criacao1)

        var SQL_criacao2 =
            """
                CREATE TABLE IF NOT EXISTS $nomeTabela2(
                    idPamonha INT PRIMARY KEY,
                    tipoDeRecheio TEXT NOT NULL,
                    pesoDeQueijo FLOAT NOT NULL,
                    fk_cpf TEXT NOT NULL
                )
            """.trimIndent()

        db?.execSQL(SQL_criacao2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val SQL_exclusao1 = "DROP TABLE IF EXISTS $nomeTabela1"
        db?.execSQL(SQL_exclusao1)
        onCreate(db)

        val SQL_exclusao2 = "DROP TABLE IF EXISTS $nomeTabela2"
        db?.execSQL(SQL_exclusao2)
        onCreate(db)
    }

}