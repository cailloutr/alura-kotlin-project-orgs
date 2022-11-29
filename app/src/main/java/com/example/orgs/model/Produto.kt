package com.example.orgs.model

import android.net.Uri
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

data class Produto(
    val noma: String,
    val descricao: String,
    val preco: BigDecimal,
    val imageUrl: Uri? = null
) {

    fun getFormatedPrice(): String =
        NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(preco)
}
