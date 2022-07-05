package com.example.myapplication.page2

import androidx.core.content.IntentCompat
import java.util.*

class Page3Presenter(val view: Page3Contract.View):Page3Contract.Presenter {
    var letter = ('a'..'z')
    override fun fillInContent(){
        for(i in 1..15){
            view.createItem("$i",letter.random().toString()+letter.random().toString())
        }

    }
}