package com.dev.mahmoud_ashraf.tagssolutionstask.Register

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dev.mahmoud_ashraf.tagssolutionstask.Home.MainActivity
import com.dev.mahmoud_ashraf.tagssolutionstask.R
import com.dev.mahmoud_ashraf.tagssolutionstask.models.mLogin
import com.dev.mahmoud_ashraf.tagssolutionstask.reset.Api
import com.dev.mahmoud_ashraf.tagssolutionstask.utils.showLoadingDialog
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class RegisterActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog
    private  var  mPath : String?= null
    private  var  base64Image : String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        userImage.setOnClickListener {
            pickImage()
        }


        register.setOnClickListener {

            if (TextUtils.isEmpty(name.text.toString()) || TextUtils.isEmpty(email.text.toString())
                || TextUtils.isEmpty(password.text.toString()) || TextUtils.isEmpty(phone.text.toString())
                || TextUtils.isEmpty(address.text.toString()) || TextUtils.isEmpty(user_type.text.toString())
            ) {
                Toast.makeText(this,"Fill All Fields Plz!", Toast.LENGTH_LONG).show()
            }
            else{

                dialog = showLoadingDialog()

                Api.create().Register( name.text.toString(), email.text.toString(),password.text.toString(),
                    phone.text.toString(), address.text.toString(), user_type.text.toString(),""+base64Image)
                    .enqueue(object : Callback<mLogin> {
                        override  fun onResponse(call: Call<mLogin>, response: Response<mLogin>) {
                            if (response.isSuccessful) {


                                if (response.body()?.isAdded == 1) {
                                    dialog.dismiss()
                                    Toast.makeText(this@RegisterActivity, "Success :)", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                                    finish()
                                } else {
                                    dialog.dismiss()
                                    Toast.makeText(this@RegisterActivity,"Error happened .. "+response.body()?.message,Toast.LENGTH_LONG).show()

                                }


                            }
                            else{

                                dialog.dismiss()
                                Toast.makeText(this@RegisterActivity,"Error happened check connection and try again!",Toast.LENGTH_LONG).show()

                            }
                        }

                        override fun onFailure(call: Call<mLogin>, t: Throwable) {
                            dialog.dismiss()
                            Toast.makeText(this@RegisterActivity,"Error happened check connection and try again!",Toast.LENGTH_LONG).show()

                        }
                    })

            }
        }
    }

    private fun pickImage() {
        ImagePicker.with(this)
            .cropSquare()	//Crop square image, its same as crop(1f, 1f)

            //.crop(16f, 9f)               //Crop Square image(Optional)
            .compress(50)         //Final image size will be less than 1 MB(Optional)
            .maxResultSize(100, 100)  //Final image resolution will be less than 1080 x 1080(Optional)
            .start(170)

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(
            "data ++++",
            "onActivityResult() called with: requestCode = [$requestCode], resultCode = [$resultCode], data = [$data]"
        )
        if (requestCode == 170&& resultCode == AppCompatActivity.RESULT_OK) {

            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data
            /// imgProfile.setImageURI(fileUri)

            //You can get File object from intent
            val file: File = ImagePicker.getFile(data)!!

            //You can also get File Path from intent
            mPath = ImagePicker.getFilePath(data)!!
            loadImage()



        }
    }


    private fun loadImage() {

        Glide
            .with(this)
            .load(BitmapFactory.decodeFile(mPath))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            //.placeholder(R.drawable.pers)
            .into(imgView)


        base64Image = ConvertToBase64(BitmapFactory.decodeFile(mPath))

    }

    private fun ConvertToBase64(bitmap: Bitmap): String {

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}