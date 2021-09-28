package com.app.techalchemy.utils

import android.content.Context
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


// find all nav controllers from closest to farest
fun Fragment.findAllNavControllers(): List<NavController> {
    val navControllers = mutableListOf<NavController>()
    var parent = parentFragment
    while (parent != null) {
        if (parent is NavHostFragment) {
            navControllers.add(parent.navController)
        }
        parent = parent.parentFragment
    }
    return navControllers
}

// find one nav controller by fragment id
fun Fragment.findNavControllerById(@IdRes id: Int): NavController {
    var parent = parentFragment
    while (parent != null) {
        if (parent is NavHostFragment && parent.id == id) {
            return parent.navController
        }
        parent = parent.parentFragment
    }
    throw RuntimeException("NavController with specified id not found")
}

//Usages
//findAllNavControllers()[2]
//findNavControllerById(R.id.navHostFragment)

fun View.slideUp(duration: Int = 3000){
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown(duration: Int = 3000) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}


/**
 * extension function that make any view visible
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * extension function that hide any view (gone)
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 * extension function for the Toast class that takes a string
 */
fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


