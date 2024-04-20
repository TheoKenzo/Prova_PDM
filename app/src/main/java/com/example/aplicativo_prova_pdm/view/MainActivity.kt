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

class MainActivity : AppCompatActivity() {

    lateinit var cpf: EditText
    lateinit var verConta: Button
    lateinit var fazerPedido: Button
    lateinit var criarConta:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cpf = findViewById(R.id.ET_CPF_Tela01)
        verConta = findViewById(R.id.BT_Ver_Conta_Tela01)
        fazerPedido = findViewById(R.id.BT_Fazer_Pedido_Tela01)
        criarConta = findViewById(R.id.BT_Criar_Conta_Tela01)
    }
}