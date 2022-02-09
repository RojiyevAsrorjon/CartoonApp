package uz.gita.bbc_news_demo.utils

import android.view.View

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.visible(bool: Boolean) {
    if (bool) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}