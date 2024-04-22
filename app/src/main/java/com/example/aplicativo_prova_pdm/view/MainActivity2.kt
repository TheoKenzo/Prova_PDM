package com.example.aplicativo_prova_pdm.view

import Cliente
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativo_prova_pdm.R
import com.example.aplicativo_prova_pdm.controller.ControllerCliente
import com.example.aplicativo_prova_pdm.controller.ControllerPedidoPamonha

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

        val ControllerCliente = ControllerCliente(this)
        val ControllerPedidoPamonha = ControllerPedidoPamonha(this)

        val listaClientes = ControllerCliente.selectAll()

        val cpfCliente = intent.extras?.getString("cpf")!!

        cpf.setText("CPF: $cpfCliente")

        val cliente = listaClientes?.filter { it.cpf.equals(cpfCliente) }.orEmpty()

        nome.setText(cliente[0].nome)
        telefone.setText(cliente[0].telefone)
        email.setText(cliente[0].email)

        atualizar.setOnClickListener{
            try{
                if(
                    ControllerCliente.update(
                        Cliente(cpfCliente, nome.text.toString(), telefone.text.toString(), email.text.toString())
                    )
                ){
                    Toast.makeText(applicationContext,"Dados Atualizados Com Sucesso!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"Os Dados Não Foram Atualizados!", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception){
                Toast.makeText(applicationContext,"Os Dados Não Foram Atualizados!", Toast.LENGTH_SHORT).show()
            }
        }

        deletarConta.setOnClickListener{
            try {
                if (ControllerPedidoPamonha.selectAll()?.filter { it.fk_cpf.equals(cpfCliente) }.orEmpty().isEmpty()){
                    if (
                        ControllerCliente.delete(
                            cpfCliente
                        )
                    ){
                        Toast.makeText(applicationContext,"Conta Excluída Com Sucesso!", Toast.LENGTH_SHORT).show()
                        this.finish()
                    }else{
                        Toast.makeText(applicationContext,"Não Foi Possível Excluir Sua Conta!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if(
                        ControllerCliente.delete(
                            cpfCliente
                        )
                        &&
                        ControllerPedidoPamonha.deleteByCPF(
                            cpfCliente
                        )
                    ){
                        Toast.makeText(applicationContext,"Conta Excluída Com Sucesso!", Toast.LENGTH_SHORT).show()
                        this.finish()
                    }else{
                        Toast.makeText(applicationContext,"Não Foi Possível Excluir Sua Conta!", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e: Exception){
                Toast.makeText(applicationContext,"Não Foi Possível Excluir Sua Conta!", Toast.LENGTH_SHORT).show()
            }
        }

        voltar.setOnClickListener{
            this.finish()
        }
    }
}