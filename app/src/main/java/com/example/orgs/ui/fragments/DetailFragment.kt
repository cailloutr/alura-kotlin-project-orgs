package com.example.orgs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.orgs.databinding.FragmentDetailBinding
import com.example.orgs.model.Produto


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val produto = args.produto

        bindProduto(produto)
    }

    private fun bindProduto(produto: Produto) {
        binding.produtoImagem.load(produto.imageUrl)
        binding.produtoNome.text = produto.noma
        binding.produtoPreco.text = produto.getFormatedPrice()
        binding.produtoDescricao.text = produto.descricao
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}