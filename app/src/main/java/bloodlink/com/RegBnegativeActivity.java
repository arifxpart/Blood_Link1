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

public class RegBnegativeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView bnegativereg;

    private EditText bnegativename,bnegativeemail,bnegativephone,bnegativepassword,
            bnegativevillage,bnegativeuniversity,bnegativelastdonet,bnegativeconfirmpassword;

    private Button bnegativeregbtn;

    private FirebaseAuth mAuth;

    private AutoCompleteTextView bnegativegender,bnegativedistrice;

    private AlertDialog.Builder alertDialogBuilder;


    private DatabaseReference database;
    private FirebaseDatabase databasePUSH;


    String[]Genders=new String[]{"Male","Female"};

    String []districts = new String[]{"Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur",
            "Chittagong","Comilla","Cox's Bazar","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati","Dhaka","Faridpur","Gazipur",
            "Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi",
            "Netrakona","Rajbari","Shariatpur","Sherpur","Tangail","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna",
            "Kushtia","Magura","Meherpur","Narail","Satkhira","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi",
            "Sirajganj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Habiganj",
            "Moulvibazar","Sunamganj","Sylhet",};


    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_bnegative);

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

        setTitle("B Negative (B-) Regestation Form :");



        bnegativegender=findViewById(R.id.bnegativegenderId);

        bnegativegender.setThreshold(1);
        bnegativegender.setAdapter(new ArrayAdapter<>(RegBnegativeActivity.this,android.R.layout.simple_list_item_1,Genders));

        bnegativedistrice=findViewById(R.id.bnegativedistriceId);
        bnegativedistrice.setThreshold(1);
        bnegativedistrice.setAdapter(new ArrayAdapter<>(RegBnegativeActivity.this,android.R.layout.simple_list_item_1,districts));


        bnegativereg=findViewById(R.id.bnegativeregId);

        bnegativename=findViewById(R.id.bnegativenameId);
        bnegativegender=findViewById(R.id.bnegativegenderId);
        bnegativeemail=findViewById(R.id.bnegativeemailId);
        bnegativephone=findViewById(R.id.bnegativephoneId);
        bnegativepassword=findViewById(R.id.bnegativepasswordId);
        bnegativedistrice=findViewById(R.id.bnegativedistriceId);
        bnegativevillage=findViewById(R.id.bnegativevillageId);
        bnegativeuniversity=findViewById(R.id.bnegativeuniversityId);
        bnegativelastdonet=findViewById(R.id.bnegativelastdonetId);
        bnegativeconfirmpassword=findViewById(R.id.bnegativeconfirmpasswordId);

        bnegativeregbtn=findViewById(R.id.bnegativeregbtnId);
        bnegativeregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bnegativeregbtnId:
                regesterBnegative();
                break;
        }
    }

    private void regesterBnegative() {

        String newbnegativereg= bnegativereg.getText().toString().trim();
        String newbnegativename=bnegativename.getText().toString().trim();
        String newbnegativegender=bnegativegender.getText().toString().trim();
        String newbnegativeemail=bnegativeemail.getText().toString().trim();
        String newbnegativephone=bnegativephone.getText().toString().trim();
        String newbnegativepassword=bnegativepassword.getText().toString().trim();
        String newbnegativedistrice=bnegativedistrice.getText().toString().trim();
        String newbnegativevillage=bnegativevillage.getText().toString().trim();
        String newbnegativeuniversity=bnegativeuniversity.getText().toString().trim();
        String newbnegativelastdonet=bnegativelastdonet.getText().toString().trim();
        String newbnegativeconfirmpassword=bnegativeconfirmpassword.getText().toString().trim();

        if (newbnegativereg.isEmpty())
        {
            bnegativereg.setError("Please type your Blood Group");
            bnegativereg.requestFocus();
            return;
        }
        if (newbnegativename.isEmpty())
        {
            bnegativename.setError("Please type your name");
            bnegativename.requestFocus();
            return;
        }
        if (newbnegativegender.isEmpty())
        {
            bnegativegender.setError("Please type your gender");
            bnegativegender.requestFocus();
            return;
        }
        if (newbnegativeemail.isEmpty())
        {
            bnegativeemail.setError("Please type your Email");
            bnegativeemail.requestFocus();
            return;
        }

        if (newbnegativephone.isEmpty())
        {
            bnegativephone.setError("Please type your phone no");
            bnegativephone.requestFocus();
            return;
        }

        if (newbnegativepassword.isEmpty())
        {
            bnegativepassword.setError("Please type your password for login");
            bnegativepassword.requestFocus();
            return;
        }if (newbnegativepassword.length()<6)
        {
            bnegativepassword.setError("Minimum 6 type password");
            bnegativepassword.requestFocus();
            return;
        }if (!newbnegativeconfirmpassword.equals(newbnegativepassword))
        {
            bnegativeconfirmpassword.setError("Password Not Match");
            bnegativeconfirmpassword.requestFocus();
            return;
        }





        if (newbnegativedistrice.isEmpty())
        {
            bnegativedistrice.setError("Please type your district");
            bnegativedistrice.requestFocus();
            return;
        }if (newbnegativevillage.isEmpty())
        {
            bnegativevillage.setError("Please type your village");
            bnegativevillage.requestFocus();
            return;
        }if (newbnegativeuniversity.isEmpty())
        {
            bnegativeuniversity.setError("Please type your university");
            bnegativeuniversity.requestFocus();
            return;
        }if (newbnegativelastdonet.isEmpty())
        {
            bnegativelastdonet.setError("Please type your last blood donet date");
            bnegativelastdonet.requestFocus();
            return;
        }
        database = FirebaseDatabase.getInstance().getReference().child("DATABNEGATIVE").child(bnegativename.getText().toString());
        Map hashMap = new HashMap();

        hashMap.put("bnegativereg",bnegativereg.getText().toString().trim());
        hashMap.put("bnegativename",bnegativename.getText().toString().trim());
        hashMap.put("bnegativegender",bnegativegender.getText().toString().trim());
        hashMap.put("bnegativeemail",bnegativeemail.getText().toString().trim());
        hashMap.put("bnegativephone",bnegativephone.getText().toString().trim());
        hashMap.put("bnegativepassword",bnegativepassword.getText().toString().trim());
        hashMap.put("bnegativedistrice",bnegativedistrice.getText().toString().trim());
        hashMap.put("bnegativevillage",bnegativevillage.getText().toString().trim());
        hashMap.put("bnegativeuniversity",bnegativeuniversity.getText().toString().trim());
        hashMap.put("bnegativelastdonet",bnegativeuniversity.getText().toString().trim());

        hashMap.put("bnegativeuserId",bnegativename.getText().toString().trim());


        database.setValue(hashMap);

        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newbnegativeemail,newbnegativepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {



                    alertDialogBuilder=new AlertDialog.Builder(RegBnegativeActivity.this);
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
                        alertDialogBuilder=new AlertDialog.Builder(RegBnegativeActivity.this);
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