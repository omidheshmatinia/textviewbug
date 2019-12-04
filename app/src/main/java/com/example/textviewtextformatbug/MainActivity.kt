package com.example.textviewtextformatbug

import android.os.Bundle
import android.text.BidiFormatter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTextViewTexts(R.id.tvLtrOneLine, formatWithCurrency(), R.string.text_short_english)
        setTextViewTexts(R.id.tvLtrTwoLines, formatWithCurrency(), R.string.text_long_english)
        setTextViewTexts(R.id.tvRtlOneLine, formatWithCurrency(), R.string.text_short_arabic)
        setTextViewTexts(R.id.tvRtlOneLineBidi, bidiFormatter(formatWithCurrency()), R.string.text_short_arabic)
        setTextViewTexts(R.id.tvRtlOneLineRtlFormatter, rtlMaker(formatWithCurrency()), R.string.text_short_arabic)
        setTextViewTexts(R.id.tvRtlTwoLines, formatWithCurrency(), R.string.text_long_arabic)
        setTextViewTexts(R.id.tvRtlTwoLinesBidi, bidiFormatter(formatWithCurrency()), R.string.text_long_arabic)
        setTextViewTexts(R.id.tvRtlTwoLinesRtlFormatter, rtlMaker(formatWithCurrency()), R.string.text_long_arabic)
    }

    private fun setTextViewTexts(textViewId: Int, text: String, stringResource: Int) {
        findViewById<TextView>(textViewId).text = getString(stringResource, text, text)
    }

    private fun formatWithCurrency(): String {
        val currency = "USD$"
        val price = 200
        val priceBuilder = StringBuilder("")
        priceBuilder.append(currency)
        priceBuilder.append(getDecimalFormattedPrice(price))
        return priceBuilder.toString()
    }

    private fun getDecimalFormattedPrice(price: Int): String {
        return DecimalFormat("0.00").format(price)
    }

    private fun rtlMaker(text: String): String {
        return "\u2066" + bidiFormatter(text) + "\u2069"
    }

    private fun bidiFormatter(text: String): String {
        return BidiFormatter.getInstance().unicodeWrap(text)
    }
}
