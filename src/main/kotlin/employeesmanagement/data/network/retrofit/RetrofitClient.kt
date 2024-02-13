package employeesmanagement.data.network.retrofit

import employeesmanagement.data.network.api.ApiClient
import employeesmanagement.data.network.api.ApiConfig
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient<T> @Inject constructor(
    private val apiConfig: ApiConfig,
    private val _class: Class<T>
): ApiClient<T> {

    override val endpoints: T by lazy {
        val okHttpClient: OkHttpClient = okHttpBuilder().build()
        val retrofit: Retrofit = retrofitBuilder().build()
        retrofit.create(_class)
    }

    private fun retrofitBuilder(): Retrofit.Builder = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(apiConfig.baseUrl)
    }

    private fun okHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        retryOnConnectionFailure(true)
        readTimeout(apiConfig.timeOut.seconds, TimeUnit.SECONDS)
    }
}
