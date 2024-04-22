package com.example.aplicativo_prova_pdm.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicativo_prova_pdm.R
import com.example.aplicativo_prova_pdm.controller.ControllerPedidoPamonha
import com.example.aplicativo_prova_pdm.model.PedidoPamonha

class MainActivity4 : AppCompatActivity() {

    lateinit var cpf: TextView
    lateinit var tipoDeRecheio: EditText
    lateinit var pesoDeQueijo: EditText
    lateinit var fazerPedido: Button
    lateinit var pedidos: ListView
    lateinit var atualizar: Button
    lateinit var deletar: Button
    lateinit var voltar: Button
    lateinit var backup: Button

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
        backup = findViewById(R.id.BT_Backup_Tela04)

        val ControllerPedidoPamonha = ControllerPedidoPamonha(this)

        var listaPedidosPamonha = ControllerPedidoPamonha.selectAll()

        var proxID = (listaPedidosPamonha?.maxByOrNull { it.idPamonha }?.idPamonha?: 0) + 1

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPedidosPamonha!!.toMutableList())

        pedidos.adapter = adaptador

        val cpfCliente = intent.extras?.getString("cpf")!!

        cpf.setText("CPF: $cpfCliente")

        fazerPedido.setOnClickListener{
            try{
                if(
                    ControllerPedidoPamonha.insert(
                        PedidoPamonha(proxID, tipoDeRecheio.text.toString(), pesoDeQueijo.text.toString().toFloat(), cpfCliente)
                    )
                ){
                    Toast.makeText(applicationContext,"Pedido Enviado Com Sucesso!", Toast.LENGTH_SHORT).show()
                    tipoDeRecheio.text.clear()
                    pesoDeQueijo.text.clear()

                    listaPedidosPamonha = ControllerPedidoPamonha.selectAll()
                    pedidos.adapter = adaptador

                    proxID += 1
                }else{
                    Toast.makeText(applicationContext,"Não Foi Possível Realizar o Pedido!", Toast.LENGTH_SHORT).show()
                }
            }catch(e: Exception){
                Toast.makeText(applicationContext,"Não Foi Possível Realizar o Pedido!", Toast.LENGTH_SHORT).show()
            }
        }

        voltar.setOnClickListener{
            this.finish()
        }
    }
}