package com.getto.vrgsoft.ui.shared.description

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.domain.interactor.nyusecase.InsertDaoUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SharedDetailsPresenter<V : SharedDetailsContract.View>
@Inject constructor(val insertDaoUseCase: InsertDaoUseCase,
                    compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), SharedDetailsContract.Presenter<V> {
    override fun insertShared(results: Results) {
        insertDaoUseCase.execute(object : DisposableSingleObserver<String>(){
            override fun onSuccess(t: String) {
                Log.d("DATABASE",  t)
            }

            override fun onError(e: Throwable) {
            }

        }, InsertDaoUseCase.Params(results))
    }
}