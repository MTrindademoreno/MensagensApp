package dominando.android.mensagensapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        marquee.isSelected = true
        btn_register.setOnClickListener {
            val name = edit_name.text.toString()
            val email = edit_email.text.toString()
            val password = edit_Password.text.toString()
            Log.d("Register", "Name: $name")
            Log.d("Register", "Email: $email")
            Log.d("Register", "Password: $password")

            if(name.isEmpty() || email.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"The Name, Email and Password cannot be empty!!",Toast.LENGTH_SHORT).show()
            }



        }
        text_have_account.setOnClickListener {
            startActivity(Intent(this,Activity_login::class.java))
            Log.d("Register","Go to login_Activity")
        }
    }
}