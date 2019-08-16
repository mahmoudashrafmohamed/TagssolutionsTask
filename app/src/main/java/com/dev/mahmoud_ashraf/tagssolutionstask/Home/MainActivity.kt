package com.dev.mahmoud_ashraf.tagssolutionstask.Home

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dev.mahmoud_ashraf.tagssolutionstask.R
import com.dev.mahmoud_ashraf.tagssolutionstask.models.mSplashSteps
import com.dev.mahmoud_ashraf.tagssolutionstask.reset.Api
import com.dev.mahmoud_ashraf.tagssolutionstask.utils.showLoadingDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutManager = LinearLayoutManager(this)
        splashRv.setLayoutManager(linearLayoutManager)


        dialog = showLoadingDialog()

        Api.create().getSplashSteps( )
            .enqueue(object : Callback<mSplashSteps> {
                override  fun onResponse(call: Call<mSplashSteps>, response: Response<mSplashSteps>) {
                    if (response.isSuccessful) {
                        dialog.dismiss()
                        if (response.body()?.toolData!= null)
                        splashRv.adapter = SplashStepsAdapter(response.body()?.toolData!!)

                    }
                    else{

                        dialog.dismiss()
                        Toast.makeText(this@MainActivity,"Error happened check connection and try again!",Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<mSplashSteps>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(this@MainActivity,"Error happened check connection and try again!",Toast.LENGTH_LONG).show()

                }
            })

    }
}
