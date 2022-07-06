package com.example.myapplication.page3

interface Page3Contract {
    interface View{
        fun createItem(title: String, content:String);
    }

    interface Presenter {
        fun fillInContent()
    }
}