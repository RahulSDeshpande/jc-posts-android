package com.rahulografy.jcposts.util.ext

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Returns an existing ViewModel or creates a new one in the scope (usually, a fragment or
 * an activity), associated with this {@code ViewModelProvider}.
 *
 *
 * The created ViewModel is associated with the given scope and will be retained
 * as long as the scope is alive (e.g. if it is an activity, until it is
 * finished or process is killed).
 *
 * @param viewModelClass The class of the ViewModel to create an instance of it if it is not
 *                       present.
 * @param T              The type parameter for the ViewModel.
 * @return A ViewModel that is an instance of the given type {@code T}.
 */
fun <T : ViewModel> FragmentActivity.initViewModel(viewModelClass: Class<T>) =
    ViewModelProviders
        .of(this)
        .get(viewModelClass)