package com.example.data

class ApiConst private constructor() {

    init {
        throw AssertionError("Don't make entity of " + ApiConst::class.java.simpleName)
    }

    companion object {

        const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
        const val ENDPOINT = BASE_URL + ""

    }


}
