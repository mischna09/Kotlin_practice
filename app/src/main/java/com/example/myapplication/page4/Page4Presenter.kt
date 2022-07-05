package com.example.myapplication.page4

import androidx.core.content.IntentCompat
import java.util.*

class Page4Presenter(val view: Page4Contract.View) : Page4Contract.Presenter {
    var letter = ('a'..'z')
    override fun fillInContent() {
        for (i in 1..15) {
            view.createItem("$i", letter.random().toString() + letter.random().toString())
        }

    }
}