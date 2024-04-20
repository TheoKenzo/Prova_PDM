package com.example.aplicativo_prova_pdm.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativo_prova_pdm.R

class MainActivity2 : AppCompatActivity() {

    lateinit var cpf: TextView
    lateinit var nome: EditText
    lateinit var telefone: EditText
    lateinit var email: EditText
    lateinit var atualizar: Button
    lateinit var deletarConta: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cpf = findViewById(R.id.TV_CPF_Tela02)
        nome = findViewById(R.id.ET_Nome_Tela02)
        telefone = findViewById(R.id.ET_Telefone_Tela02)
        email = findViewById(R.id.ET_Email_Tela02)
        atualizar = findViewById(R.id.BT_Atualizar_Tela02)
        deletarConta = findViewById(R.id.BT_Deletar_Conta_Tela02)
        voltar = findViewById(R.id.BT_Voltar_Tela02)
    }
}