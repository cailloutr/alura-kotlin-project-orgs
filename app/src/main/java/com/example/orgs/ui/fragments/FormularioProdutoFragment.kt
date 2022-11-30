package com.example.orgs.ui.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.FragmentFormularioProdutoBinding
import com.example.orgs.extensions.tentaCarregarImagem
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.LoadImageDialog
import java.math.BigDecimal


class FormularioProdutoFragment : Fragment() {

    private var _binding: FragmentFormularioProdutoBinding? = null
    val binding get() = _binding!!

    private lateinit var produtoDao: ProdutoDao
    private var url: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        produtoDao = ProdutoDao()

        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFormularioProdutoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraBtnSalvar()

        binding.produtoImagem.setOnClickListener {
            val dialog = LoadImageDialog(this.url) { imagem ->
                url = imagem
                onPositiveClick()
            }
            dialog.show(childFragmentManager, "FormularioProduto")
        }
    }

    private fun configuraBtnSalvar() {
        binding.btnSalvar.setOnClickListener {
            val produto = criaProduto()
            produtoDao.adicionar(produto)

            findNavController().navigate(
                FormularioProdutoFragmentDirections
                    .actionFormularioProdutoFragmentToListaProdutosFragment()
            )
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

    fun onPositiveClick() {
        binding.produtoImagem.apply {
            tentaCarregarImagem(url)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}