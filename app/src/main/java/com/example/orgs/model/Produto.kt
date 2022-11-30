package com.example.orgs.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

@Parcelize
data class Produto(
    val noma: String,
    val descricao: String,
    val preco: BigDecimal,
    val imageUrl: Uri? = null
) : Parcelable {

    fun getFormatedPrice(): String =
        NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(preco)
}
