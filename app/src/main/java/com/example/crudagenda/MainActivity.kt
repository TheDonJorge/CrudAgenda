package com.example.crudagenda

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudagenda.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //Botoes de acao
        binding.btnSalvar.setOnClickListener {
            Toast.makeText(this, "botão Salvar Clique aceito", Toast.LENGTH_SHORT).show()
        }
        
        binding.btnBuscar.setOnClickListener {
            Toast.makeText(this, "botão Buscar clique aceito", Toast.LENGTH_SHORT).show()
        }
        
        binding.btnAtualizar.setOnClickListener {
            Toast.makeText(this, "botão Atualizar clique aceito", Toast.LENGTH_SHORT).show()
        }
        
        binding.btnDeletar.setOnClickListener {
            Toast.makeText(this, "botão Deletar clique aceito", Toast.LENGTH_SHORT).show()
        }

    }
}