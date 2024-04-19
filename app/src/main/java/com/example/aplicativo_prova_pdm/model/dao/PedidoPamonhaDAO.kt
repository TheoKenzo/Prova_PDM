package com.example.aplicativo_prova_pdm.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PedidoPamonhaDAO(context: Context) : SQLiteOpenHelper(context, "fabricaPamonhas", null, 1){
    private val nomeTabela = "PedidoPamonha"
    override fun onCreate(db: SQLiteDatabase?){
        val SQL_criacao =
            """
                CREATE TABLE IF NOT EXISTS $nomeTabela(
                    idPamonha INT PRIMARY KEY,
                    tipoDeRecheio TEXT NOT NULL,
                    pesoDeQueijo FLOAT NOT NULL,
                    fk_cpf TEXT NOT NULL,
                    FOREIGN KEY(fk_cpf) REFERENCES Cliente(cpf)
                )
            """.trimIndent()

        db?.execSQL(SQL_criacao)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS $nomeTabela"
        db?.execSQL(SQL_exclusao)
        onCreate(db)
    }
}