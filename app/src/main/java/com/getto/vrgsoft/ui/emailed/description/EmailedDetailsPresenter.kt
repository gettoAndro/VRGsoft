package com.getto.vrgsoft.ui.emailed.description

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.domain.interactor.nyusecase.InsertDaoUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class EmailedDetailsPresenter<V : EmailedDetailsContract.View> @Inject constructor(val insertDaoUseCase: InsertDaoUseCase,
                                                                                   compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), EmailedDetailsContract.Presenter<V> {
    override fun insertEmailed(results: Results) {
        insertDaoUseCase.execute(object : DisposableSingleObserver<String>(){
            override fun onSuccess(t: String) {
                Log.d("DATABASE",  t)
                view?.onSuccess()
            }

            override fun onError(e: Throwable) {
            }

        }, InsertDaoUseCase.Params(results))
    }
}