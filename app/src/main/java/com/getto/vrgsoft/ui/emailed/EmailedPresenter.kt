package com.getto.vrgsoft.ui.emailed

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.domain.interactor.nyusecase.GetEmailedUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class EmailedPresenter<V : EmailedContract.View> @Inject constructor(val emailedUseCase: GetEmailedUseCase,
                    compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), EmailedContract.Presenter<V> {
    override fun getEmailed() {
        emailedUseCase.execute(object : DisposableSingleObserver<Emailed>(){

            override fun onSuccess(emailed: Emailed) {
                view?.onSuccess(emailed)
                Log.d("EMAILED", "SUCCESS")
                Log.d("EMAILED", "url = " + emailed.results?.get(0)?.media?.get(0)?.metadata?.get(0)?.url)
            }
            override fun onError(e: Throwable) {
                Log.e("EMAILED", "ERROR = " + e.message)
            }
        }, GetEmailedUseCase.Params())
    }

}