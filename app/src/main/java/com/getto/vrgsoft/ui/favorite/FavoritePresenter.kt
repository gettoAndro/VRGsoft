package com.getto.vrgsoft.ui.favorite

import android.util.Log
import com.getto.vrgsoft.data.nyt.service.Results
import com.getto.vrgsoft.domain.interactor.nyusecase.GetFavoriteDaoUseCase
import com.getto.vrgsoft.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class FavoritePresenter<V : FavoriteContract.View> @Inject constructor(val useCase: GetFavoriteDaoUseCase,
                                                                       compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), FavoriteContract.Presenter<V>  {
    override fun getFavorite() {
        useCase.execute(object : DisposableSingleObserver<List<Results>>(){
            override fun onSuccess(t: List<Results>) {
                view?.onSuccess(t)
            }

            override fun onError(e: Throwable) {
                Log.e("DATABASE", "ERROR = " + e.message)
            }

        }, GetFavoriteDaoUseCase.Params())
    }
}