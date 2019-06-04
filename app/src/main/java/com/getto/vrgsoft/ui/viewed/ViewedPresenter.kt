package com.getto.vrgsoft.ui.viewed

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.domain.interactor.nyusecase.GetViewedUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class ViewedPresenter<V : ViewedContract.View>
@Inject constructor(val useCase: GetViewedUseCase,
                    compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable),
                                                                ViewedContract.Presenter<V> {
    override fun getViewed() {
        useCase.execute(object : DisposableSingleObserver<Emailed>(){
            override fun onSuccess(t: Emailed) {
                view?.onSuccess(t)
            }

            override fun onError(e: Throwable) {
                Log.e("VIEWED", "ERROR = " + e.message)
            }

        }, GetViewedUseCase.Params())
    }


}