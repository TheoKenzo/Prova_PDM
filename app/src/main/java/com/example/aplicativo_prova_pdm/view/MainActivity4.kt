package com.example.aplicativo_prova_pdm.view

import Cliente
import android.content.Context
import android.os.Bundle
import android.widget.AdapterView
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
import com.example.aplicativo_prova_pdm.controller.ControllerCliente
import com.example.aplicativo_prova_pdm.controller.ControllerPedidoPamonha
import com.example.aplicativo_prova_pdm.model.PedidoPamonha
import java.io.FileInputStream
import java.io.FileOutputStream


class MainActivity4 : AppCompatActivity() {
    fun createFileInInternalStorage(context: Context, fileName: String, content: String) {
        var outputStream: FileOutputStream? = null
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            outputStream.write(content.toByteArray())
            outputStream.close()
            Toast.makeText(applicationContext,"Arquivo Salvo Com Sucesso!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext,"Não Foi Possível Salvar o Arquivo!", Toast.LENGTH_SHORT).show()
        } finally {
            outputStream?.close()
        }
    }

    fun readFileFromInternalStorage(context: Context, fileName: String) {
        val fileInputStream: FileInputStream? = context.openFileInput(fileName)
        val fileContent = fileInputStream?.bufferedReader().use { it?.readText() }
        println("Conteúdo do arquivo: $fileContent")
    }

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



        val ControllerCliente = ControllerCliente(this)
        val ControllerPedidoPamonha = ControllerPedidoPamonha(this)

        var listaClientes: ArrayList<Cliente>? = ControllerCliente.selectAll()
        var listaPedidosPamonha: ArrayList<PedidoPamonha>? = ControllerPedidoPamonha.selectAll()

        var proxID = (listaPedidosPamonha?.maxByOrNull { it.idPamonha }?.idPamonha?: 0) + 1

        if (listaPedidosPamonha != null) {
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPedidosPamonha)

            pedidos.adapter = adaptador
        }

        val cpfCliente = intent.extras?.getString("cpf")!!

        cpf.setText("CPF: $cpfCliente")

        fazerPedido.setOnClickListener{
            try{
                if(ControllerPedidoPamonha.existePedidoPamonha(cpfCliente)){
                    if(
                        ControllerPedidoPamonha.insert(
                            PedidoPamonha(proxID, tipoDeRecheio.text.toString(), pesoDeQueijo.text.toString().toFloat(), cpfCliente)
                        )
                    ){
                        Toast.makeText(applicationContext,"Pedido Enviado Com Sucesso!", Toast.LENGTH_SHORT).show()
                        tipoDeRecheio.text.clear()
                        pesoDeQueijo.text.clear()

                        listaPedidosPamonha = ControllerPedidoPamonha.selectAll()
                        pedidos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPedidosPamonha!!)
                    }else{
                        Toast.makeText(applicationContext,"Não Foi Possível Realizar o Pedido!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext,"Você Já Possuí Um Pedido Em Andamento!", Toast.LENGTH_SHORT).show()
                }
            }catch(e: Exception){
                Toast.makeText(applicationContext,"Não Foi Possível Realizar o Pedido!", Toast.LENGTH_SHORT).show()
            }
        }

        pedidos.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            val selectedItem = listaPedidosPamonha?.get(position)

            if (selectedItem != null) {
                tipoDeRecheio.setText(selectedItem.tipoDeRecheio)
                pesoDeQueijo.setText(selectedItem.pesoDeQueijo.toString())
            }
        }

        atualizar.setOnClickListener{
            try{
                val pedidosPamonha = listaPedidosPamonha?.filter { it.fk_cpf.equals(cpfCliente) }.orEmpty()

                if(
                    ControllerPedidoPamonha.update(
                        PedidoPamonha(pedidosPamonha[0].idPamonha, tipoDeRecheio.text.toString(), pesoDeQueijo.text.toString().toFloat(), pedidosPamonha[0].fk_cpf)
                    )
                ){
                    Toast.makeText(applicationContext,"Dados Atualizados Com Sucesso!", Toast.LENGTH_SHORT).show()

                    listaPedidosPamonha = ControllerPedidoPamonha.selectAll()
                    pedidos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPedidosPamonha!!)
                }else{
                    Toast.makeText(applicationContext,"Os Dados Não Foram Atualizados!", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception){
                Toast.makeText(applicationContext,"Os Dados Não Foram Atualizados!", Toast.LENGTH_SHORT).show()
            }
        }

        deletar.setOnClickListener{
            try{
                if(
                    ControllerPedidoPamonha.deleteByCPF(
                        cpfCliente
                    )
                ){
                    Toast.makeText(applicationContext,"Pedido Excluído Com Sucesso!", Toast.LENGTH_SHORT).show()

                    this.finish()
                }else{
                    Toast.makeText(applicationContext,"Não Foi Possível Excluir o Pedido!", Toast.LENGTH_SHORT).show()
                }
            }catch(e: Exception){
                Toast.makeText(applicationContext,"Não Foi Possível Excluir o Pedido!", Toast.LENGTH_SHORT).show()
            }
        }

        backup.setOnClickListener{
            var textoBackup: String = ""

            textoBackup += "idPamonha tipoDeRecheio pesoDeQueijo fk_cpf\n"

            listaPedidosPamonha?.forEach {
                textoBackup += "${it.idPamonha} ${it.tipoDeRecheio} ${it.pesoDeQueijo} ${it.fk_cpf}\n"
            }

            textoBackup += "\ncpf nome telefone email\n"

            listaClientes?.forEach{
                textoBackup += "${it.cpf} ${it.nome} ${it.telefone} ${it.email}\n"
            }

            createFileInInternalStorage(this, "backup.txt", textoBackup)
            readFileFromInternalStorage(this, "backup.txt")
        }

        voltar.setOnClickListener{
            this.finish()
        }
    }
}