package com.mizani.movieapp.webservice

import com.mizani.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MovieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Authorization", BuildConfig.MOVIE_BEARER_TOKEN).build()

        return chain.proceed(request)
    }

}