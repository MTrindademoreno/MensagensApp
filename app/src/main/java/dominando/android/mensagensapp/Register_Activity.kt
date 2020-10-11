package dominando.android.mensagensapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class Register_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        marquee.isSelected = true
        btn_register.setOnClickListener {
          register()
        }

        btn_select_foto.setOnClickListener {
            Log.d("Register","Try to show photo selector")
            //seleciona a foto
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }


        text_have_account.setOnClickListener {
            startActivity(Intent(this,Activity_login::class.java))
            Log.d("Register","Go to login_Activity")


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==0 && resultCode== Activity.RESULT_OK && data!=null){
            Log.d("Register","Photo was selected")

            val uri =data.data//recupera a uri da imagem
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,uri)//guarda a imagem no objeto criado

            val bitmapDrawable= BitmapDrawable(bitmap)
            btn_select_foto.setBackgroundDrawable(bitmapDrawable)//set a imagem
        }
    }
    private fun register(){
        val name = edit_name.text.toString()
        val email = edit_email.text.toString()
        val password = edit_Password.text.toString()
        Log.d("Register", "Name: $name")
        Log.d("Register", "Email: $email")
        Log.d("Register", "Password: $password")
        if(name.isNullOrEmpty() ||  email.isNullOrEmpty()||password.isNullOrEmpty()){
            Toast.makeText(this,"The Name, Email and Password cannot be empty!!",Toast.LENGTH_SHORT).show()
            return

        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                Toast.makeText(this,"User created successfully!!",Toast.LENGTH_SHORT).show()
                Log.d("Register","Usuário criado com sucesso? ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.d("Register","Falha ao criar usuário ${it.message}")
                Toast.makeText(this,"The Name, Email and Password cannot be empty!!",Toast.LENGTH_SHORT).show()

            }

    }


}