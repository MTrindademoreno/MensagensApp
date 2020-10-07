package dominando.android.mensagensapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
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
            if(name.isNullOrEmpty() ||  email.isNullOrEmpty()||password.isNullOrEmpty()){
               Toast.makeText(this,"The Name, Email and Password cannot be empty!!",Toast.LENGTH_SHORT).show()
            return@setOnClickListener

           }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                 if(!it.isSuccessful) return@addOnCompleteListener
                    Log.d("Register","Usuário criado com sucesso? ${it.result?.user?.uid}")
                }
                .addOnFailureListener {
                    Log.d("Register","Falha ao criar usuário ${it.message}")
                    Toast.makeText(this,"The Name, Email and Password cannot be empty!!",Toast.LENGTH_SHORT).show()

                }





        }
        text_have_account.setOnClickListener {
            startActivity(Intent(this,Activity_login::class.java))
            Log.d("Register","Go to login_Activity")
        }
    }
}