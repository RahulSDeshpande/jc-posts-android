package com.rahulografy.jcposts.util.ext

fun <T> ArrayList<T>.replace(newList: List<T>) {
    clear()
    addAll(newList)
}