package com.example.aplicativo_prova_pdm.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativo_prova_pdm.R

class MainActivity4 : AppCompatActivity() {

    lateinit var cpf: TextView
    lateinit var tipoDeRecheio: EditText
    lateinit var pesoDeQueijo: EditText
    lateinit var fazerPedido: Button
    lateinit var pedidos: ListView
    lateinit var atualizar: Button
    lateinit var deletar: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cpf = findViewById(R.id.TV_CPF_Tela04)
        tipoDeRecheio = findViewById(R.id.ET_TipoDeQueijo_Texto04)
        pesoDeQueijo = findViewById(R.id.ET_PesoDeQueijo_Tela04)
        fazerPedido = findViewById(R.id.BT_FazerPedido_Tela04)
        pedidos = findViewById(R.id.LV_Pedidos_Tela04)
        atualizar = findViewById(R.id.BT_Atualizar_Tela04)
        deletar = findViewById(R.id.BT_Deletar_Tela04)
        voltar = findViewById(R.id.BT_Voltar_Tela04)

        voltar.setOnClickListener{
            this.finish()
        }
    }
}