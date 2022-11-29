package com.example.orgs

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.LoadImageDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(), LoadImageDialog.ImageDialogListener {

    private val produtoDao = ProdutoDao()
    private lateinit var binding: ActivityFormularioProdutoBinding

    private var url: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.formulario_produto_activity_app_title)

        configuraBtnSalvar()

        binding.produtoImagem.setOnClickListener {
            val dialog = LoadImageDialog(this.url)
            dialog.show(supportFragmentManager, "FormularioProduto")
        }

        binding.edtNome.setEndIconOnClickListener {
            Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configuraBtnSalvar() {
        binding.btnSalvar.setOnClickListener {
            val produto = criaProduto()
            produtoDao.adicionar(produto)
            finish()
        }
    }

    private fun criaProduto(): Produto {

        val nome = binding.edtNome.editText?.text.toString()

        val descricao = binding.edtDescricao.editText?.text.toString()

        val edtValorText = binding.edtValor.editText?.text.toString()

        val valor = if (edtValorText.isNotEmpty()) {
            BigDecimal(edtValorText)
        } else {
            BigDecimal.ZERO
        }

        return Produto(noma = nome, descricao = descricao, preco = valor, imageUrl = url)
    }

    override fun onDialogPositiveClick(dialog: LoadImageDialog) {
        url = dialog.url

        binding.produtoImagem.apply {
            tentaCarregarImagem(url)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }
}