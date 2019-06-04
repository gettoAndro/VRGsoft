package com.getto.vrgsoft.ui.shared

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.domain.interactor.nyusecase.GetSharedUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SharedPresenter<V : SharedContract.View>
@Inject constructor(val sharedUseCase: GetSharedUseCase,
                    compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), SharedContract.Presenter<V> {
    override fun getShared() {
        sharedUseCase.execute(object : DisposableSingleObserver<Emailed>(){
            override fun onSuccess(t: Emailed) {
                view?.onSuccess(t)
            }

            override fun onError(e: Throwable) {
                Log.e("SHARED", "ERROR = " + e.message)
            }

        }, GetSharedUseCase.Params())
    }
}