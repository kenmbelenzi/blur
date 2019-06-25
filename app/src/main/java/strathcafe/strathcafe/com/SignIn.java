package strathcafe.strathcafe.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import Strathcafe.cairoButton;
import Strathcafe.cairoEditText;
import Strathcafe.cairoTextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class SignIn extends AppCompatActivity
{

    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    cairoButton _signInButton;
    cairoEditText _emailEditText,_passwordEditText;
    cairoTextView _forgetPasswordTextView;
    RelativeLayout _signUpRelativeLayout,_signUpFacebookRelativeLayout;
    View _lineView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();


        /////*     initialize view   */////
        _signInButton = findViewById(R.id.id_signIn_Button);
        _emailEditText = findViewById(R.id.id_email_EditText);
        _passwordEditText = findViewById(R.id.id_password_EditText);
        _forgetPasswordTextView = findViewById(R.id.id_forgetPassword_TextView);
        _signUpRelativeLayout = findViewById(R.id.id_sIGNuP_RelativeLayout);
        _signUpFacebookRelativeLayout= findViewById(R.id.id_SignUpFacebook_RelativeLayout);
        _lineView = findViewById(R.id.id_view_1);

        /////*     On Click         */////
      /*  _signInButton.setOnClickListener(this);
        _forgetPasswordTextView.setOnClickListener(this);
        _signUpRelativeLayout.setOnClickListener(this);
        _signUpFacebookRelativeLayout.setOnClickListener(this);
*/
        /////*      add Drawable to Edit Text and to line view     */////
        _emailEditText.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(SignIn.this, R.drawable.ic_email), null, null, null);
        _emailEditText.setCompoundDrawablePadding(8);
        _passwordEditText.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(SignIn.this, R.drawable.ic_password_lock), null, null, null);
        _passwordEditText.setCompoundDrawablePadding(8);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            _lineView.setBackgroundDrawable(ContextCompat.getDrawable(SignIn.this, R.drawable.line_view) );
        } else
            {
            _lineView.setBackgroundResource(R.color.linecolor);
        }



        _signInButton.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                final String email = _emailEditText.getText().toString();
                final String password =_passwordEditText.getText().toString();

                try {

                    if (password.length() > 0 && email.length() > 0) {
                        //PD.show();
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(
                                                        SignIn.this,
                                                    "Authentication Failed",
                                                    Toast.LENGTH_LONG).show();
                                            Log.v("error", task.getResult().toString());
                                        } else {
                                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                       // PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                SignIn.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        _signUpRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.id_forgetPassword_TextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),forgetpassword.class).putExtra("Mode", 0));
            }
        });

    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignIn.this, MainActivity.class));
            finish();
        }
        super.onResume();
    }
}



  /*  @Override
    public void onClick(View v)
    {
        if (v == _forgetPasswordTextView)
        {
            Toast.makeText(this," please insert  forgot Password Activity"  ,Toast.LENGTH_LONG).show();
        }

        if (v== _signUpFacebookRelativeLayout)
        {
            Toast.makeText(this," please insert Facebook SDK "  ,Toast.LENGTH_LONG).show();
        }
        if (v==_signInButton)
        {
            signinfunction();
        }

        if (v== _signUpRelativeLayout)
        {
            Intent i = new Intent(this,SignUp.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
        }

    }*/

   // private void signinfunction()
    //{
        /////*   Get  Email && Password    */////
      /*  String _emailS = _emailEditText.getText().toString();
        String  _passwordS=  _passwordEditText .getText().toString();*/
        /////*   Check if email and password written  valid   */////
       // if (!validate(_emailS,_passwordS))
       /* {
            return;
        }else
        {
            Login(_emailS,_passwordS);
        }

    }
*/
   /* public boolean validate(String email, String password)
    {
        boolean valid = true;

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            _emailEditText.setError(getString(R.string.validemail));
            valid = false;
        } else
        {
            _emailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 12)
        {
            _passwordEditText.setError(getString(R.string.validpassword));
            valid = false;
        } else
        {
            _passwordEditText.setError(null);
        }

        return valid;
    }

    private void Login(String email, String password)
    {
        Toast.makeText(this,"sign in : success" + email +" " + password ,Toast.LENGTH_LONG).show();

    }*/




