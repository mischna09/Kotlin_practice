package com.example.myapplication.page5

import androidx.core.content.IntentCompat
import java.util.*

class Page5Presenter(val view: Page5Contract.View):Page5Contract.Presenter {
    var letter = ('a'..'z')
    override fun fillInContent(){
        for(i in 1..15){
            view.createItem("$i",letter.random().toString()+letter.random().toString())
        }

    }
}