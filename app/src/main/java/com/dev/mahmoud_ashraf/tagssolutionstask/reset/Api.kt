package com.dev.mahmoud_ashraf.tagssolutionstask.reset

import com.dev.mahmoud_ashraf.tagssolutionstask.models.mLogin
import com.dev.mahmoud_ashraf.tagssolutionstask.models.mSplashSteps
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface Api {

    @FormUrlEncoded
    @POST("/UsersASMX.asmx/LoginUser")
    fun Login(
              @Field("Email")
              email : String,
              @Field("Password")
              password: String): Call<mLogin>



    @POST("/UsersASMX.asmx/getSplashSteps")
    fun getSplashSteps(): Call<mSplashSteps>


    @FormUrlEncoded
    @POST("/UsersASMX.asmx/RegisterUser")
    fun Register(
        @Field("Full_Name") name: String,
        @Field("Email") email: String,
        @Field("Password") password: String,
        @Field("Phone") phone: String,
        @Field("Address") Address: String,
        @Field("UserType") UserType: String,
        @Field("base64") base64: String
    ): Call<mLogin>


    companion object Factory {
        private var retrofit: Retrofit? = null
        fun create(): Api {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                var client = OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl("http://apitest.tagssolutions.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()


            }
            return retrofit!!.create(Api::class.java)
        }
    }

}