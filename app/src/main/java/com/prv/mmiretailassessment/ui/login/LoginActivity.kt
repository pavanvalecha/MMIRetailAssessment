package com.prv.mmiretailassessment.ui.login

import timber.log.Timber
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.text.Editable
import android.content.Intent
import android.widget.EditText
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.prv.mmiretailassessment.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import androidx.appcompat.app.AppCompatActivity
import com.prv.mmiretailassessment.ui.MainActivity
import com.prv.mmiretailassessment.singletons.User
import com.google.android.gms.tasks.OnCompleteListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.prv.mmiretailassessment.viewModels.LoginViewModel
import com.prv.mmiretailassessment.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            login.isEnabled = loginState.isDataValid
            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                authenticateUser(username.text.toString(), password.text.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
    }

    private fun authenticateUser(email: String, password: String) {
        binding.loading.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Timber.d("signInWithEmail:success")
                    binding.loading.visibility = View.GONE
                    val user = auth.currentUser
                    User.UserId = auth.uid.toString()
                    fetchUserIDToken(user)
                } else {
                    Timber.e(task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.loading.visibility = View.GONE
                }
            }
    }

    private fun fetchUserIDToken(user: FirebaseUser?) {
        user?.getIdToken(true)
            ?.addOnCompleteListener(OnCompleteListener<GetTokenResult?> { task ->
                if (task.isSuccessful) {
                    val idToken: String = task.result!!.getToken()!!
                    loginSuccess(idToken, user.uid)
                } else {
                }
            })
    }

    private fun loginSuccess(idToken: String, userId: String) {
        Timber.tag("ID Token - ").d(idToken)
        User.UserAuthToken = idToken

        this@LoginActivity.finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}