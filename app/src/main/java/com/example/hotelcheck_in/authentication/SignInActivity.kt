package com.example.hotelcheck_in.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hotelcheck_in.MainActivity
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phNum = intent.getStringExtra("phoneNo").toString()
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()

        binding.continueBtn.setOnClickListener {
            val name = binding.nameUser.editText?.text.toString()
            val email = binding.emailUser.editText?.text.toString()
            val idProof = binding.idProofUser.editText?.text.toString()

            if (name.equals("") || !email.contains("@") || email.equals("") || idProof.equals("")) {

                if (name.equals("")) {
                    binding.nameUser.editText?.setError("This field can't be empty")
                }

                if (!email.contains("@") || email.equals("")) {
                    binding.emailUser.editText?.setError("Enter your mail correctly")
                }

                if (idProof.equals("")) {
                    binding.idProofUser.editText?.setError("This field can't be empty")
                }

            } else {
                val hashMap = HashMap<String, Any>()
                hashMap["userId"] = userId
                hashMap["phoneNo"] = phNum
                hashMap["name"] = name
                hashMap["email"] = email
                hashMap["id"] = idProof

                FirebaseFirestore.getInstance().collection("Users").document(userId).set(hashMap).addOnCompleteListener {
                    Toast.makeText(this, "Account Created!!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(baseContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}