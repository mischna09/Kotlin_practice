package com.example.myapplication.page5

interface Page5Contract {
    interface View{
        fun createItem(title: String, content:String);
    }

    interface Presenter {
        fun fillInContent()
    }
}