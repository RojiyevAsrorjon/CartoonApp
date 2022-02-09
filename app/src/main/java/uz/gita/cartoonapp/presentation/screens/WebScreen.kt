package uz.gita.cartoonapp.presentation.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.cartoonapp.R
import uz.gita.cartoonapp.databinding.ScreenWebBinding
import uz.gita.cartoonapp.utils.scope

@AndroidEntryPoint
class WebScreen : Fragment(R.layout.screen_web){
    private val binding by viewBinding(ScreenWebBinding::bind)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progress.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progress.hide()
            }
        }

        webView.loadUrl("https://rickandmortyapi.com/")
    }
}