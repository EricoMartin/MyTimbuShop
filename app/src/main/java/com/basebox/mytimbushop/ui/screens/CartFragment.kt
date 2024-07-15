package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.databinding.FragmentCartBinding
import com.basebox.mytimbushop.models.Items
import com.basebox.mytimbushop.ui.CartAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var itemsList = arrayListOf<Items>(
            Items(1, "Multivitamins", 3700.0, R.drawable.adobe, "Helps keep you healthy"),
            Items(2, "Supplements", 4200.0, R.drawable.apple_phone, "Helps keep you healthy"),
            Items(3, "Antibiotics", 2000.0, R.drawable.lavalier, "Helps keep you healthy"),
            Items(4, "Painkillers", 1500.0, R.drawable.chromecast, "Helps keep you healthy"),
            Items(5, "Multivitamins Extra", 6700.0, R.drawable.adobe, "Helps keep you healthy"),
            Items(6, "Supplements Extra", 8200.0, R.drawable.apple_phone, "Helps keep you healthy"),
            Items(7, "Antibiotics Extra", 5000.0, R.drawable.lavalier, "Helps keep you healthy"),
            Items(8, "Painkillers Extra", 2500.0, R.drawable.chromecast, "Helps keep you healthy")
        )
        val recycler1: RecyclerView = binding.rcv1
        recycler1.layoutManager = LinearLayoutManager(requireContext())
        recycler1.adapter = CartAdapter(itemsList)

        val recycler2: RecyclerView = binding.rcv2
        recycler2.layoutManager = LinearLayoutManager(requireContext())
        recycler2.adapter = CartAdapter(itemsList)
    }
}