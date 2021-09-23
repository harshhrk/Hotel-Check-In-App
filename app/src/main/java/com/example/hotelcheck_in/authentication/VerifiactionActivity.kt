package com.example.hotelcheck_in.authentication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hotelcheck_in.MainActivity
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.databinding.ActivityVerifiactionBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.concurrent.TimeUnit

class VerifiactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifiactionBinding

    private var phoneNumber = "+91"
    private var verificationIdSent : String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifiactionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        phoneNumber += intent.getStringExtra("phoneNo")
        sendCodeForVerification(phoneNumber)

        binding.verifyCode.setOnClickListener {
//            val intent  = Intent(applicationContext, SignInActivity::class.java)
//            startActivity(intent)
            val code = Objects.requireNonNull(binding.pinView.text).toString()
            if (code.isNotEmpty()) {
                verifyCode(code)
            }

        }
    }

    private fun verifyCode(code: String) {
        val phoneAuthProviderCredentials = PhoneAuthProvider.getCredential(verificationIdSent, code)
        signInWithPhoneAuthCredential(phoneAuthProviderCredentials)
    }

    private fun sendCodeForVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:$credential")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(this@VerifiactionActivity, "" + e.message, Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            verificationIdSent = verificationId
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Verification Successful", Toast.LENGTH_SHORT).show()
                    FirebaseFirestore.getInstance().collection("Users")
                        .whereEqualTo("userId", FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .get().addOnCompleteListener {
                            if (it.isSuccessful) {
                                if (it.result!!.size() == 0) {
                                    val intent = Intent(applicationContext, SignInActivity::class.java)
                                    intent.putExtra("phoneNo", phoneNumber)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    val intent = Intent(baseContext, MainActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }
                } else {
                    Toast.makeText(this, "Verification Failed. Try Again!!!", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
    }
}