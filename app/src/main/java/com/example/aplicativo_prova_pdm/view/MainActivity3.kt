package com.example.aplicativo_prova_pdm.view

import Cliente
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativo_prova_pdm.R
import com.example.aplicativo_prova_pdm.controller.ControllerCliente

class MainActivity3 : AppCompatActivity() {

    lateinit var cpf: EditText
    lateinit var nome: EditText
    lateinit var telefone: EditText
    lateinit var email: EditText
    lateinit var cadastrese: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cpf = findViewById(R.id.ET_CPF_Tela03)
        nome = findViewById(R.id.ET_Nome_Tela03)
        telefone = findViewById(R.id.ET_Telefone_Tela03)
        email = findViewById(R.id.ET_Email_Tela03)
        cadastrese = findViewById(R.id.BT_Cadastrarse_Tela03)
        voltar = findViewById(R.id.BT_Voltar_Tela03)

        val ControllerCliente = ControllerCliente(this)

        cadastrese.setOnClickListener{
            try{
                if(ControllerCliente.existeCliente(cpf.text.toString()))
                    if(
                        ControllerCliente.insert(
                            Cliente(cpf.text.toString(), nome.text.toString(), telefone.text.toString(), email.text.toString())
                        )
                    ){
                        Toast.makeText(applicationContext,"Você Foi Cadastrado Com Sucesso!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext,"Não Foi Possível Lhe Cadastrar!",Toast.LENGTH_SHORT).show()
                    }
                else
                    Toast.makeText(applicationContext,"Você Já Está Cadastrado!",Toast.LENGTH_SHORT).show()
            }catch(e: Exception){
                Toast.makeText(applicationContext,"Não Foi Possível Lhe Cadastrar!",Toast.LENGTH_SHORT).show()
            }
        }

        voltar.setOnClickListener{
            this.finish()
        }
    }
}