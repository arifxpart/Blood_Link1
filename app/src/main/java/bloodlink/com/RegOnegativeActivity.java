package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegOnegativeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView onegativereg;

    private EditText onegativename,onegativeemail,onegativephone,onegativepassword,
            onegativevillage,onegativeuniversity,onegativelastdonet,onegativeconfirmpassword;

    private Button onegativeregbtn;

    private AutoCompleteTextView onegativegender,onegativedistrice;

    private FirebaseAuth mAuth;

    private AlertDialog.Builder alertDialogBuilder;


    private DatabaseReference database;
    private FirebaseDatabase databasePUSH;

    private AdView mAdView;


    String[]Genders=new String[]{"Male","Female"};

    String []districts = new String[]{"Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur",
            "Chittagong","Comilla","Cox's Bazar","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati","Dhaka","Faridpur","Gazipur",
            "Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi",
            "Netrakona","Rajbari","Shariatpur","Sherpur","Tangail","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna",
            "Kushtia","Magura","Meherpur","Narail","Satkhira","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi",
            "Sirajganj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Habiganj",
            "Moulvibazar","Sunamganj","Sylhet",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_onegative);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        setTitle("O Negative (O-) Regestation Form :");


        onegativegender=findViewById(R.id.onegativegenderId);

        onegativegender.setThreshold(1);
        onegativegender.setAdapter(new ArrayAdapter<>(RegOnegativeActivity.this,android.R.layout.simple_list_item_1,Genders));

        onegativedistrice=findViewById(R.id.onegativedistriceId);
        onegativedistrice.setThreshold(1);
        onegativedistrice.setAdapter(new ArrayAdapter<>(RegOnegativeActivity.this,android.R.layout.simple_list_item_1,districts));


        onegativereg=findViewById(R.id.onegativeregId);

        onegativename=findViewById(R.id.onegativenameId);
        onegativegender=findViewById(R.id.onegativegenderId);
        onegativeemail=findViewById(R.id.onegativeemailId);
        onegativephone=findViewById(R.id.onegativephoneId);
        onegativepassword=findViewById(R.id.onegativepasswordId);
        onegativedistrice=findViewById(R.id.onegativedistriceId);
        onegativevillage=findViewById(R.id.onegativevillageId);
        onegativeuniversity=findViewById(R.id.onegativeuniversityId);
        onegativelastdonet=findViewById(R.id.onegativelastdonetId);
        onegativeconfirmpassword=findViewById(R.id.onegativeconfirmpasswordId);

        onegativeregbtn=findViewById(R.id.onegativeregbtnId);
        onegativeregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.onegativeregbtnId:
                regesterAnegative();
                break;
        }
    }

    private void regesterAnegative() {

        String newonegativereg= onegativereg.getText().toString().trim();
        String newonegativename=onegativename.getText().toString().trim();
        String newonegativegender=onegativegender.getText().toString().trim();
        String newonegativeemail=onegativeemail.getText().toString().trim();
        String newonegativephone=onegativephone.getText().toString().trim();
        String newonegativepassword=onegativepassword.getText().toString().trim();
        String newonegativedistrice=onegativedistrice.getText().toString().trim();
        String newonegativevillage=onegativevillage.getText().toString().trim();
        String newonegativeuniversity=onegativeuniversity.getText().toString().trim();
        String newonegativelastdonet=onegativelastdonet.getText().toString().trim();
        String newonegativeconfirmpassword=onegativeconfirmpassword.getText().toString().trim();

        if (newonegativereg.isEmpty())
        {
            onegativereg.setError("Please type your Blood Group");
            onegativereg.requestFocus();
            return;
        }if (newonegativename.isEmpty())
        {
            onegativename.setError("Please type your name");
            onegativename.requestFocus();
            return;
        }if (newonegativegender.isEmpty())
        {
            onegativegender.setError("Please type your gender");
            onegativegender.requestFocus();
            return;
        }if (newonegativeemail.isEmpty())
        {
            onegativeemail.setError("Please type your Email");
            onegativeemail.requestFocus();
            return;
        }if (newonegativephone.isEmpty())
        {
            onegativephone.setError("Please type your phone no");
            onegativephone.requestFocus();
            return;
        }

        if (newonegativepassword.isEmpty())
        {
            onegativepassword.setError("Please type your password for login");
            onegativepassword.requestFocus();
            return;
        }if (newonegativepassword.length()<6)
        {
            onegativepassword.setError("Minimum 6 type password");
            onegativepassword.requestFocus();
            return;
        }if (!newonegativeconfirmpassword.equals(newonegativepassword))
        {
            onegativeconfirmpassword.setError("Password not match");
            onegativeconfirmpassword.requestFocus();
            return;
        }

        if (newonegativedistrice.isEmpty())
        {
            onegativedistrice.setError("Please type your district");
            onegativedistrice.requestFocus();
            return;
        }if (newonegativevillage.isEmpty())
        {
            onegativevillage.setError("Please type your village");
            onegativevillage.requestFocus();
            return;
        }if (newonegativeuniversity.isEmpty())
        {
            onegativeuniversity.setError("Please type your university");
            onegativeuniversity.requestFocus();
            return;
        }if (newonegativelastdonet.isEmpty())
        {
            onegativelastdonet.setError("Please type your last blood donet date");
            onegativelastdonet.requestFocus();
            return;
        }
        database = FirebaseDatabase.getInstance().getReference().child("DATAONEGATIVE").child(onegativename.getText().toString());
        Map hashMap = new HashMap();

        hashMap.put("onegativereg",onegativereg.getText().toString().trim());
        hashMap.put("onegativename",onegativename.getText().toString().trim());
        hashMap.put("onegativegender",onegativegender.getText().toString().trim());
        hashMap.put("onegativeemail",onegativeemail.getText().toString().trim());
        hashMap.put("onegativephone",onegativephone.getText().toString().trim());
        hashMap.put("onegativepassword",onegativepassword.getText().toString().trim());
        hashMap.put("onegativedistrice",onegativedistrice.getText().toString().trim());
        hashMap.put("onegativevillage",onegativevillage.getText().toString().trim());
        hashMap.put("onegativeuniversity",onegativeuniversity.getText().toString().trim());
        hashMap.put("onegativelastdonet",onegativeuniversity.getText().toString().trim());

        hashMap.put("onegativeuserId",onegativename.getText().toString().trim());


        database.setValue(hashMap);

        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newonegativeemail,newonegativepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {



                    alertDialogBuilder=new AlertDialog.Builder(RegOnegativeActivity.this);
                    alertDialogBuilder.setTitle("Welcome");
                    alertDialogBuilder.setMessage("Your Regestation is Complete");
                    alertDialogBuilder.setIcon(R.drawable.welcom);

                    alertDialogBuilder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alertDialog=alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    if (task.getException()instanceof FirebaseAuthUserCollisionException)
                    {
                        alertDialogBuilder=new AlertDialog.Builder(RegOnegativeActivity.this);
                        alertDialogBuilder.setTitle("Sorry !");
                        alertDialogBuilder.setMessage("This Email have already account");
                        alertDialogBuilder.setIcon(R.drawable.error_black_24dp);

                        alertDialogBuilder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                        AlertDialog alertDialog=alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    } @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}