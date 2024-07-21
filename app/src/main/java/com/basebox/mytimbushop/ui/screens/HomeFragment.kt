package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basebox.mytimbushop.MyTimbuApplication
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.ui.adapters.ProductAdapter
import com.basebox.mytimbushop.ui.viewmodels.ProductViewModel
import com.basebox.mytimbushop.databinding.FragmentHomeBinding
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Item
import com.basebox.mytimbushop.ui.viewmodels.CartViewModel
import com.basebox.mytimbushop.ui.viewmodels.ProductViewModelFactory
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers

private const val TAG = "HomeFragment"
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private val viewModel: ProductViewModel by viewModels{
        ProductViewModelFactory((requireActivity().application as MyTimbuApplication).repository, Dispatchers.IO)
    }
    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var drawerLayout: DrawerLayout

    private val organizationId: String = "67102ab35e7742088683036c5e8a368d"
    private val appId: String = "MZ2HN5PMS725RMD"
    private val apiKey: String = "1acfe466531340f79358fed1c54fe60620240708072804259309"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false )

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        lifecycleScope.launchWhenStarted {
            viewModel.products.observe(requireActivity(), Observer { products ->
                val adapter = ProductAdapter(products, object : ProductAdapter.OnItemClickListener {
                    override fun onItemClick(item: Item, pos: Int) {
                        Log.d(TAG, "onItemClick: item is $item, position is $pos")
                        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item, pos)
                        findNavController().navigate(action)
                    }
                    override fun onCartClick(cartItem: CartItem) {
                        cartViewModel.addCartItem(cartItem)
                    }

                    override fun onLikeClick(cartItem: CartItem) {
                        viewModel.addLikes(cartItem)
                    }

                })
                recyclerView.adapter = adapter
            })
            viewModel.error.observe(requireActivity(), Observer { error ->
                Toast.makeText(requireContext(), "Error fetching data: $error", Toast.LENGTH_LONG)
                    .show()
            })

            viewModel.fetchProducts(organizationId, appId, apiKey)
        }
        binding.imageView3.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        binding.imageView4.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
    }
}