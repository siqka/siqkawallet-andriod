package io.horizontalsystems.bankwallet.modules.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.net.Uri;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.horizontalsystems.bankwallet.R
import kotlinx.android.synthetic.main.fragment_browser.*
import android.content.Intent




class NewsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browser, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        browser.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        browser.webViewClient = object : WebViewClient() {
            override fun onPageFinished(webView: WebView, url: String?) {
                if (browser.canGoBack()) {
                    view.findViewById<Button>(R.id.btnBack)?.visibility = View.VISIBLE
                } else {
                    view.findViewById<Button>(R.id.btnBack)?.visibility = View.GONE
                }
                super.onPageFinished(webView, url)
            }
        }
        browser.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(webView: WebView, newProgress: Int) {
                view.findViewById<SwipeRefreshLayout>(R.id.swapToRefresh)?.isRefreshing = true
                if (newProgress == 100) {
                    view.findViewById<SwipeRefreshLayout>(R.id.swapToRefresh)?.isRefreshing = false

                }
                super.onProgressChanged(webView, newProgress)
            }
        }
        view.findViewById<Button>(R.id.btnBack)?.setOnClickListener {
            browser.goBack()
        }
        browser.loadUrl("https://discuss.siqka.com/")

        swapToRefresh.setOnRefreshListener {
            browser.loadUrl(browser.url!!)
        }

    }
}