package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutoDao {

    fun adicionar(produto: Produto) {
        listaDeProdutos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return listaDeProdutos.toList()
    }

    companion object {
        val listaDeProdutos = mutableListOf<Produto>()
    }


}