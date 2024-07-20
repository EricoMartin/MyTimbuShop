package com.basebox.mytimbushop.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.basebox.mytimbushop.MyTimbuApplication
import com.basebox.mytimbushop.R
import com.basebox.mytimbushop.models.Orders
import com.basebox.mytimbushop.ui.viewmodels.OrderViewModel
import com.basebox.mytimbushop.ui.viewmodels.OrderViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

/**
 * A simple [Fragment] subclass.
 * Use the [CardDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardDetailsFragment : Fragment() {

    private var name = ""
    private var total = 0
    private var quantity = 0

    private val viewModel: OrderViewModel by viewModels{
        OrderViewModelFactory((requireActivity().application as MyTimbuApplication).orderRepository, Dispatchers.IO)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etCardNumber: EditText = view.findViewById(R.id.card_number)
        val etCVV: EditText = view.findViewById(R.id.cvv)
        val etExpiryDate: EditText = view.findViewById(R.id.card_date)
        val btnSubmit: Button = view.findViewById(R.id.button2)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)

        total = CardDetailsFragmentArgs.fromBundle(requireArguments()).total.toInt()
        name = CardDetailsFragmentArgs.fromBundle(requireArguments()).name
         quantity = CardDetailsFragmentArgs.fromBundle(requireArguments()).quantity

        tvPrice.text = total.toString()
        btnSubmit.setOnClickListener {
            val cardNumber = etCardNumber.text.toString().trim()
            val cvv = etCVV.text.toString().trim()
            val expiryDate = etExpiryDate.text.toString().trim()
            val price = total.toString()

            if (cardNumber.isEmpty() || cvv.isEmpty() || expiryDate.isEmpty()) {
                showErrorDialog("All fields must be filled.")
            } else {
                showConfirmationDialog(price)
            }
        }
         view.findViewById<ImageView>(R.id.imageView3).setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        view.findViewById<ImageView>(R.id.imageView4).setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
    }

    private fun showConfirmationDialog(price: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Purchase")
        builder.setMessage("Price: $price")
        builder.setPositiveButton("Proceed") { dialog, _ ->
            dialog.dismiss()
            showSuccessDialog("Order placed successfully.")

            viewLifecycleOwner.lifecycleScope.launch {
                delay(5000)
                findNavController().navigate(R.id.action_cardDetailsFragment_to_homeFragment)
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            showErrorDialog("Order canceled.")

            viewLifecycleOwner.lifecycleScope.launch {
                delay(5000)
                findNavController().navigate(R.id.action_cardDetailsFragment_to_homeFragment)
            }
        }
        builder.create().show()
    }

    private fun showSuccessDialog(message: String) {
        lifecycleScope.launch (Dispatchers.IO) {
            val newOrder = Orders(id = 0, orderId = UUID.randomUUID().toString(), productName = name,
                quantity = quantity, orderDate = Date(), status = "Completed", totalPrice = total.toDouble())
            viewModel.insert(newOrder)
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Success")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    private fun showErrorDialog(message: String) {
        lifecycleScope.launch (Dispatchers.IO) {
            val newOrder = Orders(id = 0, orderId = UUID.randomUUID().toString(), productName = name,
                quantity = quantity, orderDate = Date(), status = "Cancelled", totalPrice = total.toDouble())
            viewModel.insert(newOrder)
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }
}