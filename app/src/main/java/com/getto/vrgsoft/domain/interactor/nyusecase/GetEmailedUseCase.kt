package com.getto.vrgsoft.domain.interactor.nyusecase

import com.getto.vrgsoft.data.nyt.service.Emailed
import com.getto.vrgsoft.domain.executor.PostExecutionThread
import com.getto.vrgsoft.domain.interactor.UseCase
import com.getto.vrgsoft.domain.executor.ThreadExecutor
import com.getto.vrgsoft.domain.gateway.NytGateway
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class GetEmailedUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    compositeDisposable: CompositeDisposable,
    private val nytGateway: NytGateway
) : UseCase<Emailed, GetEmailedUseCase.Params>(threadExecutor, postExecutionThread, compositeDisposable) {

    override fun buildUseCaseObservable(params: Params): Single<Emailed> = nytGateway.getEmailed()

    class Params
}