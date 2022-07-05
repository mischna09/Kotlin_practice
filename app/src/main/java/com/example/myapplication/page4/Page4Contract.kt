package com.example.myapplication.page4

interface Page4Contract {
    interface View {
        fun createItem(title: String, content: String);
    }

    interface Presenter {
        fun fillInContent()
    }
}