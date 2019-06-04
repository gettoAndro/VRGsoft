package com.getto.vrgsoft.ui.viewed.description

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.domain.interactor.nyusecase.InsertDaoUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class ViewedDetailsPresenter<V : ViewedDetailsContract.View>
    @Inject constructor(val insertDaoUseCase: InsertDaoUseCase,
                        compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), ViewedDetailsContract.Presenter<V>{
    override fun insertViewed(results: Results) {
        insertDaoUseCase.execute(object : DisposableSingleObserver<String>(){
            override fun onSuccess(t: String) {
                Log.d("DATABASE",  t)
            }

            override fun onError(e: Throwable) {
            }

        }, InsertDaoUseCase.Params(results))
    }
}