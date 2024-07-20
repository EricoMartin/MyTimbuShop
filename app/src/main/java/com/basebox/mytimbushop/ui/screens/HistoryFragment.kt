package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.MyTimbuApplication
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.databinding.FragmentHistoryBinding
import com.basebox.mytimbushop.ui.adapters.OrderAdapter
import com.basebox.mytimbushop.ui.viewmodels.OrderViewModel
import com.basebox.mytimbushop.ui.viewmodels.OrderViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    private lateinit var _binding: FragmentHistoryBinding
    private val binding get() = _binding
    private val viewModel: OrderViewModel by viewModels{
        OrderViewModelFactory((requireActivity().application as MyTimbuApplication).orderRepository, Dispatchers.IO)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return  _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.rcv1
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.allOrders.observe(viewLifecycleOwner, Observer { orderHistories ->
                recyclerView.adapter = OrderAdapter(orderHistories)
//            orderHistories?.let { adapter.submitList(it) }
            })

        binding.imageView3.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        binding.imageView4.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
    }
}