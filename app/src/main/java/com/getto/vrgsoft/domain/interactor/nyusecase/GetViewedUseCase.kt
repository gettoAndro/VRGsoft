package com.getto.vrgsoft.domain.interactor.nyusecase

import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.domain.executor.PostExecutionThread
import com.getto.vrgsoft.domain.executor.ThreadExecutor
import com.getto.vrgsoft.domain.gateway.NytGateway
import com.getto.vrgsoft.domain.interactor.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetViewedUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    compositeDisposable: CompositeDisposable,
    private val nytGateway: NytGateway
) : UseCase<Emailed, GetViewedUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {


    override fun buildUseCaseObservable(params: GetViewedUseCase.Params): Single<Emailed> = nytGateway.getViewed()


    class Params
}