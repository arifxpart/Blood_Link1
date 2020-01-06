package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.chrono.MinguoChronology;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText logineditemail,logineditpassword;
    private Button loginbutton;
    private TextView createnewaccount;

    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    private ProgressBar progressbarId;

    private FirebaseUser currentUser;

    private FirebaseAuth mAuth;

    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressbarId=findViewById(R.id.progressbarId);

        saveLoginCheckBox=findViewById(R.id.saveLoginCheckBox);

        //all edit text id
        logineditemail=findViewById(R.id.logineditemailId);
        logineditpassword=findViewById(R.id.logineditpasswordId);

        //all button id
        loginbutton=findViewById(R.id.loginbuttonId);

        //all text view id
        createnewaccount=findViewById(R.id.createnewaccountId);

        //check box




        createnewaccount.setOnClickListener(this);
        loginbutton.setOnClickListener(this);







        mAuth = FirebaseAuth.getInstance();
        currentUser =mAuth.getCurrentUser();


//check box
        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();


        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            logineditemail.setText(loginPreferences.getString("username", ""));
            logineditpassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.createnewaccountId:
                Intent intentnewaccount=new Intent(getApplicationContext(),CreateNewAccountActivity.class);
                startActivity(intentnewaccount);
                break;


                case R.id.loginbuttonId:
                    login();

//                    Intent intentlogin=new Intent(getApplicationContext(),HomeActivity.class);
//                    startActivity(intentlogin);
                    break;
        }
    }

    private void login() {
        String email = logineditemail.getText().toString().trim();
        String password = logineditpassword.getText().toString().trim();



//validity Check for email and password
        if (email.isEmpty()) {
            logineditemail.setError("Enter an email address");
            logineditemail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            logineditemail.setError("Enter a valid email address");
            logineditemail.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            logineditpassword.setError("Enter a password");
            logineditpassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            logineditpassword.setError("You must give 6 charaster password");
            logineditpassword.requestFocus();
            return;
        }



        progressbarId.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressbarId.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Sorry !");
                    alertDialogBuilder.setMessage("Email or Password is not correct");
                    alertDialogBuilder.setIcon(R.drawable.error_black_24dp);

                    alertDialogBuilder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            }
        });

        if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", email);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();


        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser !=null)
        {
            sentUserToMainActivity();
        }
    }

    private void sentUserToMainActivity() {

        Intent loginintent=new Intent(MainActivity.this,HomeActivity.class);
        loginintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginintent);
        finish();
    }
}