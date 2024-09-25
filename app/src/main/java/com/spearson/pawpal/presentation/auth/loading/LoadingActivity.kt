package com.spearson.pawpal.presentation.auth.loading

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.spearson.pawpal.MainActivity
import com.spearson.pawpal.R
import com.spearson.pawpal.presentation.auth.signIn.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        Handler(Looper.getMainLooper()).postDelayed({
            val user = auth.currentUser

            if(user != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{

                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)

                finish()
            }


        },1000)

    }
}