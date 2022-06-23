package info.camposha.loginsignup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginSignupPage : AppCompatActivity() {
    private lateinit var btnsignup: Button
    private lateinit var btnsignin: Button
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var signin_signup_btn: Button
    private lateinit var circleImageView: ImageView
    private lateinit var signin_signup_txt: TextView
    private lateinit var forgot_password: TextView


    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        mAuth = FirebaseAuth.getInstance()

        edtemail = findViewById(R.id.email)
        edtpassword = findViewById(R.id.password)
        btnsignup = findViewById(R.id.signup)
        btnsignin = findViewById(R.id.signin)
        signin_signup_btn = findViewById(R.id.signin_signup_btn)
        circleImageView = findViewById(R.id.circleImageView)
        signin_signup_txt = findViewById(R.id.signin_signup_txt)
        forgot_password = findViewById(R.id.forgot_password)

        signin_signup_btn.setOnClickListener {
            val email = edtemail.text.toString()
            val password = edtpassword.text.toString()

            signUp(email, password)

        }


        signin_signup_btn.setOnClickListener {
            val email = edtemail.text.toString()
            val password = edtpassword.text.toString()

            login(email, password)
        }



        btnsignin.setOnClickListener {
            btnsignin.setTextColor(Color.parseColor("#FFFFFF"))
            btnsignin.setBackgroundColor(Color.parseColor("#FF2729C3"))
            btnsignup.setTextColor(Color.parseColor("#FF2729C3"))
            btnsignup.setBackgroundResource(R.drawable.bordershape)
            circleImageView.setImageResource(R.drawable.sigin_boy_img)
            signin_signup_txt.text = "Sign In"
            signin_signup_btn.text = "Sign In"
            forgot_password.visibility = View.VISIBLE
        }
        btnsignup.setOnClickListener {
            btnsignup.setTextColor(Color.parseColor("#FFFFFF"))
            btnsignup.setBackgroundColor(Color.parseColor("#FF2729C3"))
            btnsignin.setTextColor(Color.parseColor("#FF2729C3"))
            btnsignin.setBackgroundResource(R.drawable.bordershape)
            circleImageView.setImageResource(R.drawable.sigup_boy_img)
            signin_signup_txt.text = "Sign Up"
            signin_signup_btn.text = "Sign Up"
            forgot_password.visibility = View.INVISIBLE
        }

    }

    private fun signUp(email: String, password: String){
        // login for logging user

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home page

                    addUserToDatabase(email,mAuth.currentUser?.uid!!)

                    val intent = Intent(this@LoginSignupPage, BlackActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LoginSignupPage, "Some error occurred", Toast.LENGTH_SHORT).show()

                }
            }

    }

    private fun login(email: String, password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for logging in user
                    val intent = Intent(this@LoginSignupPage, BlackActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LoginSignupPage, "User does no exist",Toast.LENGTH_SHORT).show()

                }
            }

    }

    private fun addUserToDatabase(email: String, uid: String){

    }
}
