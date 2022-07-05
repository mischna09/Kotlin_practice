package com.example.myapplication

interface MainContract {
    interface View{
        fun showToast(i :Int);
        fun goPage2();
    }

    interface Presenter {
        fun Count();
    }
}