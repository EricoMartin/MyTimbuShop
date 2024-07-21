package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.databinding.FragmentCartBinding
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.ui.adapters.CartAdapter
import com.basebox.mytimbushop.ui.viewmodels.CartViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding
    private val viewModel: CartViewModel by activityViewModels()
    private var totalSum: Double = 0.0
    private var totalTax: Double = 0.0
    private var totalVat: Double = 0.0
    private var productName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = binding.title
        val recycler1: RecyclerView = binding.rcv1
        recycler1.layoutManager = LinearLayoutManager(requireContext())
//        val recycler2: RecyclerView = binding.rcv2
//        recycler2.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launchWhenStarted {
            viewModel.cartItems.observe(requireActivity(), Observer { cartItem ->
                title.text = "All Items (${cartItem.size})"
                val adapter = CartAdapter(cartItem, object : CartAdapter.OnItemClickListener {
                    override fun onCartClick(cartItem: CartItem) {
                        viewModel.removeCartItem(cartItem)
                    }
                })
                cartItem.forEach {
                    productName = it.name
                    totalSum += it.price
                    totalTax = totalSum * 0.333
                    totalVat = totalSum * 0.667
                }
                recycler1.adapter = adapter
//                recycler2.adapter = CartAdapter(cartItem)
            })

        }
        binding.btn.setOnClickListener {
            val action = CartFragmentDirections.
            actionCartFragmentToCheckoutFragment(totalSum.toFloat(),
                totalTax.toFloat(), totalVat.toFloat(), productName)
            it.findNavController().navigate(action)
        }
        binding.imageView3.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        binding.imageView4.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
    }
}