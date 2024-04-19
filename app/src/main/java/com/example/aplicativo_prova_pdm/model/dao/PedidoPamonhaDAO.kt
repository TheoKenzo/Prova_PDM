package com.example.aplicativo_prova_pdm.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PedidoPamonhaDAO(context: Context) : SQLiteOpenHelper(context, "MeubancoIFTM", null, 1){
    private val nomeTabela = "PedidoPamonha"
    override fun onCreate(db: SQLiteDatabase?){
        val col1 = "idPamonha"
        val col2 = "tipoDeRecheio"
        val col3 = "pesoDeQueijo"
        val col4 = "fk_cpf"

        val SQL_criacao =
            "CREATE TABLE IF NOT EXISTS $nomeTabela (" +
                    "$col1 INT PRIMARY KEY," +
                    "$col2 TEXT NOT NULL," +
                    "$col3 FLOAT NOT NULL," +
                    "$col4 INT NOT NULL," +
                    "FOREIGN KEY(fk_cpf) REFERENCES Cliente(cpf))"

        db?.execSQL(SQL_criacao)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS $nomeTabela"
        db?.execSQL(SQL_exclusao)
        onCreate(db)
    }
}