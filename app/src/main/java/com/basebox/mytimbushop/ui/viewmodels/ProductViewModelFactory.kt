package com.basebox.mytimbushop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.basebox.mytimbushop.repo.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher

class ProductViewModelFactory (private val repository: ProductRepository,
                               private val dispatcher: CoroutineDispatcher
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository, dispatcher) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}