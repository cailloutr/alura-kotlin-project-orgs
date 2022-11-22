package com.example.orgs

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.model.Produto
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val produtoDao = ProdutoDao()
    private lateinit var binding: ActivityFormularioProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configuraBtnSalvar()

        binding.produtoImagem.setOnClickListener {
            val dialogBuilder = MaterialAlertDialogBuilder(this)
            dialogBuilder.setView(R.layout.carregar_imagem_dialog_layout)
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .setPositiveButton("Confirmar") { _, _ ->

                }
                .show()
        }
    }

        private fun configuraBtnSalvar() {
            val btnSalvar = findViewById<Button>(R.id.btn_salvar)
            btnSalvar.setOnClickListener {
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

            return Produto(nome, descricao, valor)
        }
    }