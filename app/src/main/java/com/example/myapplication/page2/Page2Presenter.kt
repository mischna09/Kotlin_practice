package com.example.myapplication.page2

import androidx.core.content.IntentCompat
import java.util.*

class Page2Presenter(val view: Page2Contract.View):Page2Contract.Presenter {
    override fun fillInContent(){
        for(i in 1..15){
            view.createItem("$i",('a'..'z').random().toString()+('a'..'z').random().toString())
        }

    }
}