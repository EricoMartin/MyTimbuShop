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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.MyTimbuApplication
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.databinding.FragmentFavouritesBinding
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Product
import com.basebox.mytimbushop.ui.adapters.CartAdapter
import com.basebox.mytimbushop.ui.adapters.LikesAdapter
import com.basebox.mytimbushop.ui.adapters.ProductAdapter
import com.basebox.mytimbushop.ui.viewmodels.ProductViewModel
import com.basebox.mytimbushop.ui.viewmodels.ProductViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {

    private lateinit var _binding: FragmentFavouritesBinding
    private val binding get() = _binding
    private val viewModel: ProductViewModel by viewModels{
        ProductViewModelFactory((requireActivity().application as MyTimbuApplication).repository, Dispatchers.IO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(layoutInflater, container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = binding.rcv1
        recycler.layoutManager= LinearLayoutManager(requireActivity())
        lifecycleScope.launch {
            viewModel.getItems.observe(requireActivity(), Observer { product->
                binding.title.text = "Likes (${product.size})"
                val adapter = LikesAdapter(product, object : LikesAdapter.OnItemClickListener {
                    override fun onLikeClick(cartItem: CartItem) {
                        val item = CartItem(
                            id,
                            name = cartItem.name,
                            price = cartItem.price,
                            desc = cartItem.desc,
                            img = cartItem.img,
                            availableQuantity = cartItem.availableQuantity,
                            isAvailable = cartItem.isAvailable,
                            userId = cartItem.userId,
                            itemId = cartItem.itemId,
                            organizationId = cartItem.organizationId,
                            liked = false
                        )
                        viewModel.updateItem(item)
                    }

                    override fun onDeleteClick(cartItem: CartItem) {
                        val item = CartItem(
                            id,
                            name = cartItem.name,
                            price = cartItem.price,
                            desc = cartItem.desc,
                            img = cartItem.img,
                            availableQuantity = cartItem.availableQuantity,
                            isAvailable = cartItem.isAvailable,
                            userId = cartItem.userId,
                            itemId = cartItem.itemId,
                            organizationId = cartItem.organizationId,
                            liked = false
                        )
                        viewModel.updateItem(item)
                    }
                })
                recycler.adapter = adapter
                    })
                }
        binding.imageView3.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        binding.imageView4.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }

        }
}