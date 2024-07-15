package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.ui.ProductAdapter
import com.basebox.mytimbushop.ui.ProductViewModel
import com.basebox.mytimbushop.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private val viewModel: ProductViewModel by viewModels()

    private val organizationId: String = "67102ab35e7742088683036c5e8a368d"
    private val appId: String = "MZ2HN5PMS725RMD"
    private val apiKey: String = "1acfe466531340f79358fed1c54fe60620240708072804259309"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false )
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.products.observe(requireActivity(), Observer { products ->
            recyclerView.adapter = ProductAdapter(products)
        })
        viewModel.error.observe(requireActivity(), Observer { error ->
            Toast.makeText(requireContext(), "Error fetching data: $error", Toast.LENGTH_LONG).show()
        })

        viewModel.fetchProducts(organizationId, appId, apiKey)
    }
}