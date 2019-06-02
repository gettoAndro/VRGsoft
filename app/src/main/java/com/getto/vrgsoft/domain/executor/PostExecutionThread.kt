package com.getto.vrgsoft.domain.executor

import io.reactivex.Scheduler



interface PostExecutionThread {
    val scheduler: Scheduler
}
