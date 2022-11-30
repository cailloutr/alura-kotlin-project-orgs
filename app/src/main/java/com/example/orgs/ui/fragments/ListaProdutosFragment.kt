package com.example.orgs.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.FragmentListaProdutosBinding
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter


class ListaProdutosFragment : Fragment() {

    private var _binding: FragmentListaProdutosBinding? = null
    private val binding get() = _binding!!

    private lateinit var produtoDao: ProdutoDao
    private lateinit var listaProdutosAdapter: ListaProdutosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        produtoDao = ProdutoDao()
        listaProdutosAdapter = ListaProdutosAdapter(requireContext(), produtoDao.buscaTodos())

        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListaProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        listaProdutosAdapter.atualiza(produtoDao.buscaTodos())
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    fun configuraFab() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(
                ListaProdutosFragmentDirections
                    .actionListaProdutosFragmentToFormularioProdutoFragment()
            )
        }
    }

    fun configuraRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = listaProdutosAdapter
        listaProdutosAdapter.quandoClicaNoItemListener = {
            findNavController().navigate(ListaProdutosFragmentDirections.actionListaProdutosFragmentToDetailFragment(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}