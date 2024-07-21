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
import com.basebox.mytimbushop.databinding.FragmentCheckoutBinding
import com.basebox.mytimbushop.ui.adapters.CheckoutAdapter
import com.basebox.mytimbushop.ui.viewmodels.CartViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CheckoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment() {

    private lateinit var _binding: FragmentCheckoutBinding
    private val binding get() = _binding
    private val viewModel: CartViewModel by activityViewModels()

    private var totalSum: Float = 2F
    var size = 0
    var name = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tax = CheckoutFragmentArgs.fromBundle(requireArguments()).totalTax
        val total = CheckoutFragmentArgs.fromBundle(requireArguments()).totalSum
        val vat = CheckoutFragmentArgs.fromBundle(requireArguments()).totalVat
        name = CheckoutFragmentArgs.fromBundle(requireArguments()).name

        val recycler1: RecyclerView = binding.rcv1
        recycler1.layoutManager = LinearLayoutManager(requireContext())

        with(binding) {
            subtotalPrice.text = total.toString()
            taxPrice.text = tax.toString()
            shipPrice.text = vat.toString()
            totalPrice.text= (tax + total + vat).toString()
        }
        totalSum =(tax + total + vat)

        lifecycleScope.launchWhenStarted {
            viewModel.cartItems.observe(requireActivity(), Observer { cartItem ->
                binding.orderTitle.text = "Order Summary(${cartItem.size})"
                size = cartItem.size
                val adapter = CheckoutAdapter(cartItem)
                recycler1.adapter = adapter
            })
        }
        binding.btnTotal.setOnClickListener {
            val action = CheckoutFragmentDirections.actionCheckoutFragmentToCardDetailsFragment(totalSum, name, size)
            it.findNavController().navigate(action)
        }
        binding.imageView4.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
    }
}