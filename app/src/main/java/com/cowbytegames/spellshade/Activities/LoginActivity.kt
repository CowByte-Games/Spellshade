package com.cowbytegames.spellshade.Activities


import android.content.ContentValues.TAG
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.cowbytegames.spellshade.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var googleBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
    }

//    fun handleSignIn(result: GetCredentialResponse) {
//        val credential = result.credential
//
//        when (credential) {
//            is CustomCredential -> {
//                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
//                    try {
//                        val googleIdTokenCredential = GoogleIdTokenCredential
//                            .createFrom(credential.data)
//                        val googleIdToken = googleIdTokenCredential.id
//
//                        Toast.makeText(this, "Google ID: $googleIdToken", Toast.LENGTH_LONG).show()
//
//                    } catch (e: GoogleIdTokenParsingException) {
//                        Log.e(TAG, "Received an invalid google id token response", e)
//                    }
//                }
//                else {
//                    Log.e(TAG, "Unexpected type of credential")
//                }
//            }
//            else -> {
//                Log.e(TAG, "Unexpected type of credential")
//            }
//        }
//    }

    fun buttonGoogleSignIn(view: View) {
//        val credentialManager = CredentialManager.create(this@LoginActivity)
//
//        val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption.Builder(getString(R.string.web_client_id)).build()
//        val request: GetCredentialRequest = GetCredentialRequest.Builder()
//            .addCredentialOption(signInWithGoogleOption)
//            .build()
//
//
//        lifecycleScope.launch {
//            try {
//                val result = credentialManager.getCredential(
//                    request = request,
//                    context = this@LoginActivity,
//                )
//                handleSignIn(result)
//            } catch (e: GetCredentialException) {
//                Log.e(TAG, "GetCredentialException")
//            }
//        }
    }

    fun buttonPlayOffline(view: View) {

    }
}