package uz.itschool.geo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import uz.itschool.geo.helpers.SharedPrefHelper
import uz.itschool.geo.ui.theme.GEOTheme

class SignInScreen : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    val database= Firebase.database
    val myRef=database.reference
    lateinit var sharedPrefHelper: SharedPrefHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            GEOTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            val signInIntent = mGoogleSignInClient.signInIntent
                            startActivityForResult(signInIntent, 1)
                        }) {
                            Text(text = "Sign In Google")
                        }
                        Button(onClick = {
                            mGoogleSignInClient.signOut()

                        }) {
                            Text(text = "Sign Out")
                        }
                    }
                }
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
                Log.d("TAG", "onActivityResult: ")
            } catch (e: ApiException) {
                Log.d("TAG", "error: $e")

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {


        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    val userData = UserData(
                        user?.displayName,
                        user?.uid,
                        user?.email,
                        user?.photoUrl.toString()
                    )
                    val reference = Firebase.database.reference.child("contact")
                    var status = true
                    reference.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val children = snapshot.children
                            children.forEach {
                                val user = it.getValue(UserData::class.java)
                                if (user != null && user.uid == userData.uid) {
                                    status=false
                                }
                            }


                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.d("TAG", "onCancelled: ${error.message}")
                        }

                    })


                } else {
                    Log.d("TAG", "error: Authentication Failed.")
                }
            }
    }
}