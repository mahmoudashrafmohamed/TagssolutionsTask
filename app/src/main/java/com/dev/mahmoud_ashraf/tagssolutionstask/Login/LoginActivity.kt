package com.dev.mahmoud_ashraf.tagssolutionstask.Login

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.dev.mahmoud_ashraf.tagssolutionstask.R
import com.dev.mahmoud_ashraf.tagssolutionstask.models.mLogin
import com.dev.mahmoud_ashraf.tagssolutionstask.reset.Api
import com.dev.mahmoud_ashraf.tagssolutionstask.utils.showLoadingDialog
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import com.dev.mahmoud_ashraf.tagssolutionstask.Home.MainActivity
import com.dev.mahmoud_ashraf.tagssolutionstask.Register.RegisterActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        LoginBtn.setOnClickListener {

            if (TextUtils.isEmpty(email.text.toString()) || TextUtils.isEmpty(password.text.toString()) ){
                Toast.makeText(this,"Fill All Fields Plz!",Toast.LENGTH_LONG).show()
            }
            else{


              dialog = showLoadingDialog()

                Api.create().Login( email.text.toString(), password.text.toString())
                    .enqueue(object : Callback<mLogin> {
                        override  fun onResponse(call: Call<mLogin>, response: Response<mLogin>) {
                            if (response.isSuccessful) {


                                if (response.body()?.isAdded == 1) {
                                    dialog.dismiss()
                                    Toast.makeText(this@LoginActivity, "Success :)", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                    finish()
                                } else {
                                    dialog.dismiss()
                                    Toast.makeText(this@LoginActivity,"Error happened .. "+response.body()?.message,Toast.LENGTH_LONG).show()

                                }


                            }
                            else{

                                dialog.dismiss()
                                Toast.makeText(this@LoginActivity,"Error happened check connection and try again!",Toast.LENGTH_LONG).show()

                            }
                        }

                        override fun onFailure(call: Call<mLogin>, t: Throwable) {
                            dialog.dismiss()
                            Toast.makeText(this@LoginActivity,"Error happened check connection and try again!",Toast.LENGTH_LONG).show()

                        }
                    })



            }

        }

        register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}
