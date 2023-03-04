package com.challenge.data_remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class APIKeyInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = BuildConfig.SPOONACULAR_API_KEY
        val url = original.url.newBuilder().addQueryParameter("apiKey", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}