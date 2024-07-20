package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.databinding.FragmentDetailsBinding
import com.basebox.mytimbushop.models.CartItem
import com.basebox.mytimbushop.models.Product
import com.basebox.mytimbushop.ui.adapters.ImageAdapter
import com.basebox.mytimbushop.ui.viewmodels.CartViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private lateinit var _binding: FragmentDetailsBinding
    private val binding get() = _binding
    private val viewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = DetailsFragmentArgs.fromBundle(requireArguments()).products
//        val position: Int  = DetailsFragmentArgs.fromBundle(requireArguments()).pos
        val viewPager = binding.viewPager

        binding.img.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
        product?.let {
            with(binding){
                tv.text = it.name
                tv1.text = it.uniqueId
                tv3.text = it.description
                tv5.text = it.currentPrice[0].nGN[0].toString()
                viewPager.adapter = ImageAdapter(it.photos)
                button.setOnClickListener { view ->
                    val cartItem = CartItem(
                        id = 0,
                        name = it.name,
                        price = it.currentPrice[0].nGN[0].toString().toDouble(),
                        desc = it.description,
                        img = it.photos,
                        availableQuantity = it.availableQuantity,
                        isAvailable = it.isAvailable,
                        userId = it.userId,
                        itemId = it.id,
                        organizationId = it.organizationId,
                        liked = false
                    )
                    viewModel.addCartItem(cartItem)
                }
            }
        }
    }

    companion object {
        fun newInstance(product: Product, position: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable("product", product)
            args.putInt("pos", position)
            fragment.arguments = args
            return fragment
        }
    }
}