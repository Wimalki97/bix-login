package bixchat.xyz

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import bixchat.xyz.services.HTTP
//import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
//import com.smarteist.autoimageslider.SliderAnimations
//import com.smarteist.autoimageslider.SliderView

class Login : AppCompatActivity() {


    lateinit var login_email: EditText
    lateinit var login_psw: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        login_email = findViewById(R.id.email)
        login_psw = findViewById(R.id.password)


    }

    fun login(view: View) {
        var email = login_email.text.toString()
        var password = login_psw.text.toString()
        try {
            val url = this.getResources().getString(R.string.message_server_host);
            val http = HTTP(this, this);
            http.request(url+"/api/login","{\"username\":\""+ email +"\", \"password\":\"" + password + "\"}")
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override fun onBackPressed() {
    }
}