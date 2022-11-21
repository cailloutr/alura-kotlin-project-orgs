package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.FormularioProdutoActivity
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityListaProdutoBinding
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {
    private val produtoDao = ProdutoDao()
    private val listaProdutosAdapter = ListaProdutosAdapter(this, produtoDao.buscaTodos())

    private lateinit var binding: ActivityListaProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configuraRecyclerView()
        configuraFab()

    }

    override fun onResume() {
        super.onResume()
        listaProdutosAdapter.atualiza(produtoDao.buscaTodos())
    }

    fun configuraFab() {
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, FormularioProdutoActivity::class.java))
        }
    }

    fun configuraRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = listaProdutosAdapter
    }
}