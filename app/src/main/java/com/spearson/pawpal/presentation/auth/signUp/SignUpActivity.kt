package com.spearson.pawpal.presentation.auth.signUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.spearson.pawpal.MainActivity
import com.spearson.pawpal.R
import com.spearson.pawpal.databinding.ActivitySignUpBinding
import com.spearson.pawpal.presentation.auth.signIn.SignInActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        binding.signInBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonSignUp.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val mail= binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()
            val phone = binding.etPhone.text.toString()

            if(checkAllField()){
                auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener{
                    if(it.isSuccessful){

                        val currentUserId = auth.currentUser?.uid.toString()
                        val user = mapOf(
                            "username" to userName,
                            "mail" to mail,
                            "name" to name,
                            "surname" to surname,
                        )


//                        usersRef.child(currentUserId).setValue(user)
//                            .addOnSuccessListener {
//                                Log.d("Firebase", "User1 data written successfully")
//                            }
//                            .addOnFailureListener { exception ->
//                                Log.e("Firebase", "Failed to write user1 data", exception)
//                            }

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("userCurrentMail", mail)
                        startActivity(intent)
                        finish()

                        Toast.makeText(this, "account created successfully", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("signup error", it.exception.toString())
                    }
                }
            }
        }
        fun signUp(){
            var email = binding.etMail.text.toString()
            var password = binding.etPassword.text.toString()
            var username = binding.etUsername.text.toString()
        }
    }
    private fun checkAllField(): Boolean{
        val email = binding.etMail.text.toString()

        if(binding.etUsername.text.toString() == ""){
            binding.textInputLayoutUsername.error ="This is a required field"
            return false
        }
        if(binding.etName.text.toString() == ""){
            binding.textInputLayoutName.error ="This is a required field"
            return false
        }
        if(binding.etSurname.text.toString() == ""){
            binding.textInputLayoutSurname.error ="This is a required field"
            return false
        }
        if(binding.etMail.text.toString() == ""){
            binding.textInputLayoutEmail.error ="This is a required field"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutEmail.error = "Check email format"
            return false
        }
        if(binding.etPassword.text.toString() == ""){
            binding.textInputLayoutPassword.error ="This is a required field"
            return false
        }
        if(binding.etPassword.length() <= 6){
            binding.textInputLayoutPassword.error ="password should be longer than 6 characters"
            return false
        }

        if(binding.etPhone.text.toString() == ""){
            binding.textInputLayoutPhone.error ="This is a required field"
            return false
        }

        return true
    }
}


