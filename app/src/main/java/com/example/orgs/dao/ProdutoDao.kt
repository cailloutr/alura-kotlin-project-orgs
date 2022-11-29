package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutoDao {

    fun adicionar(produto: Produto) {
        listaDeProdutos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return listaDeProdutos.toList()
    }

    companion object {
        val listaDeProdutos = mutableListOf(
            Produto(
                noma = "Salada de Frutas",
                descricao = "Mamão, banana, ovo",
                preco = BigDecimal("15.66")
            )
        )
    }


}