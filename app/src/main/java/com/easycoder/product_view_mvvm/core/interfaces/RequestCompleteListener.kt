package com.easycoder.product_view_mvvm.core.interfaces

interface RequestCompleteListener<T> {
    fun onRequestSuccess(data: T)
    fun onRequestFailed(errorMessage: String)
}