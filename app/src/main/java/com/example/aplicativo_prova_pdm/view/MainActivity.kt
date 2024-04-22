package com.example.aplicativo_prova_pdm.view

import Cliente
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativo_prova_pdm.R
import com.example.aplicativo_prova_pdm.controller.ControllerCliente
import com.example.aplicativo_prova_pdm.model.PedidoPamonha

class MainActivity : AppCompatActivity() {

    lateinit var cpf: EditText
    lateinit var verConta: Button
    lateinit var fazerPedido: Button
    lateinit var criarConta: Button
    lateinit var clientes: ListView

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
        clientes = findViewById(R.id.LV_Contas_Tela01)

        val ControllerCliente = ControllerCliente(this)

        var listaClientes: ArrayList<Cliente>? = ControllerCliente.selectAll()

        if (listaClientes != null) {
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaClientes)

            clientes.adapter = adaptador
        }

        verConta.setOnClickListener{
            if(ControllerCliente.existeCliente(cpf.text.toString())){
                Toast.makeText(applicationContext,"Não Foi Possível Lhe Encontrar!",Toast.LENGTH_SHORT).show()
                cpf.text.clear()
            }else{
                Intent(this, MainActivity2::class.java).let{
                    it.putExtra("cpf", cpf.text.toString())
                    startActivity(it)
                }
            }
        }

        fazerPedido.setOnClickListener{
            if(ControllerCliente.existeCliente(cpf.text.toString())){
                Toast.makeText(applicationContext,"Não Foi Possível Lhe Encontrar!",Toast.LENGTH_SHORT).show()
                cpf.text.clear()
            }else{
                Intent(this, MainActivity4::class.java).let{
                    it.putExtra("cpf", cpf.text.toString())
                    startActivity(it)
                }
            }
        }

        criarConta.setOnClickListener{
            Intent(this, MainActivity3::class.java).let{
                startActivity(it)
            }
        }
    }
}