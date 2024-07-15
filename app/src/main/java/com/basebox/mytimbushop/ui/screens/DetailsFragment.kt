package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.databinding.FragmentDetailsBinding
import com.basebox.mytimbushop.models.Photo
import com.basebox.mytimbushop.models.Photos
import com.basebox.mytimbushop.ui.ImageAdapter
import com.basebox.mytimbushop.ui.ProductViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var _binding: FragmentDetailsBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        val itemList = listOf(
            Photos(R.drawable.firemax),
            Photos(R.drawable.adobe),
            Photos(R.drawable.chromecast),
            Photos(R.drawable.timbumed),
            Photos(R.drawable.lavalier)
        )
        val viewPager = binding.viewPager
        viewPager.adapter = ImageAdapter(itemList)
        binding.img.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
//        viewModel.products.observe(requireActivity()) { product ->
//            product.items.forEach {
//                Log.d(TAG, "Images: ${it.photos.first()}")
//                viewPager.adapter = ImageAdapter(it.photos)
//            }
//        }
//        viewModel.error.observe(requireActivity()){ error ->
//            Log.d(TAG, "Images Error: $error")
//            Toast.makeText(requireContext(), "Error fetching data: $error", Toast.LENGTH_LONG).show()
//        }
    }
}