package bixchat.xyz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import bixchat.xyz.services.SharedPreference

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val textView4: TextView = findViewById(R.id.textView4)
        val textView5: TextView = findViewById(R.id.textView5)
        val textView6: TextView = findViewById(R.id.textView6)
        val textView7: TextView = findViewById(R.id.textView7)

        val sp = SharedPreference(this)
        val x_access_token = sp.getPreference("x_access_token");
        val user_id = sp.getPreference("user_id");
        val public_id = sp.getPreference("public_id");
        val email = sp.getPreference("email");

        textView4.text = x_access_token;
        textView5.text = user_id;
        textView6.text = public_id;
        textView7.text = email;
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val sp = SharedPreference(this)
            sp.clearPreference();
            val intent = Intent(this, Splash::class.java)
            startActivity(intent)
            finish()
        }
        return super.onKeyLongPress(keyCode, event)
    }
}