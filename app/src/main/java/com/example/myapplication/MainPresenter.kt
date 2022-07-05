package com.example.myapplication

class MainPresenter(val view: MainContract.View):MainContract.Presenter {
    var count:Int = 0

    override fun Count(){
        count++
        view.showToast(count)
        if(count ==3){
            view.goPage2()
        }
    }

}