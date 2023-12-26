package com.example.day_quote

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.day_quote.QuoteApi.QuoteModel
import com.example.day_quote.QuoteApi.RetrofitInstance
import com.example.day_quote.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hide status and navigation bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val attributes = window.attributes
            attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = attributes
        }
        // end

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getQuote()

        binding.nextBtn.setOnClickListener {
            getQuote()
        }

        binding.shareBtn.setOnClickListener {
            shareQuote()
        }
        var isImageClicked = false
        binding.heartIv.setOnClickListener {

            if (isImageClicked) {
                binding.heartIv.setColorFilter(Color.parseColor("#FF7E7E7E"))
                Toast.makeText(
                    applicationContext,
                    "Remove from Favorite ",
                    Toast.LENGTH_SHORT
                ).show()
                isImageClicked = false
            } else {
                binding.heartIv.setColorFilter(Color.parseColor("#a70000"))
                Toast.makeText(
                    applicationContext,
                    "Added to Favorite ",
                    Toast.LENGTH_SHORT
                ).show()
                isImageClicked = true

            }

        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getQuote() {
        setInProgress(true)

        GlobalScope.launch {
            try {
                val response = RetrofitInstance.quoteApi.getRandomQuote()
                runOnUiThread {
                    setInProgress(false)
                    response.body()?.first()?.let {
                        setUI(it)
                    }

                }
            } catch (e: Exception) {
                runOnUiThread {
                    setInProgress(false)
                    Toast.makeText(
                        applicationContext,
                        "check internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun setUI(quoteModel: QuoteModel) {

        binding.quoteText.text = quoteModel.q
        binding.quoteAuthor.text = quoteModel.a

    }

    private fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.nextBtn.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.nextBtn.visibility = View.VISIBLE
        }
    }

    private fun shareQuote() {
        val quoteModel =
            QuoteModel(binding.quoteText.text.toString(), binding.quoteAuthor.text.toString())
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, quoteModel.q)
        startActivity(intent)
    }


}
