package com.example.myapplication.page2

interface Page2Contract {
    interface View{
        fun createItem(title: String, content:String);
    }

    interface Presenter {
        fun fillInContent()
    }
}