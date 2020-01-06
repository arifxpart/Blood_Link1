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

public class RegAnegativeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView anegativereg;

    private EditText anegativename,anegativeemail,anegativephone,anegativepassword,
            anegativevillage,anegativeuniversity,anegativelastdonet,anegativeconfirmpassword;

    private AutoCompleteTextView anegativegender,anegativedistrice;


    private Button anegativeregbtn;

    private FirebaseAuth mAuth;


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
        setContentView(R.layout.activity_reg_anegative);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        setTitle("A Negative (A-) Regestation Form :");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        anegativegender=findViewById(R.id.anegativegenderId);

        anegativegender.setThreshold(1);
        anegativegender.setAdapter(new ArrayAdapter<>(RegAnegativeActivity.this,android.R.layout.simple_list_item_1,Genders));

        anegativedistrice=findViewById(R.id.anegativedistriceId);
        anegativedistrice.setThreshold(1);
        anegativedistrice.setAdapter(new ArrayAdapter<>(RegAnegativeActivity.this,android.R.layout.simple_list_item_1,districts));



        anegativereg=findViewById(R.id.anegativeregId);

        anegativename=findViewById(R.id.anegativenameId);

        anegativeemail=findViewById(R.id.anegativeemailId);
        anegativephone=findViewById(R.id.anegativephoneId);
        anegativepassword=findViewById(R.id.anegativepasswordId);
        anegativedistrice=findViewById(R.id.anegativedistriceId);
        anegativevillage=findViewById(R.id.anegativevillageId);
        anegativeuniversity=findViewById(R.id.anegativeuniversityId);
        anegativelastdonet=findViewById(R.id.anegativelastdonetId);
        anegativeconfirmpassword=findViewById(R.id.anegativeconfirmpasswordId);

        anegativeregbtn=findViewById(R.id.anegativeregbtnId);
        anegativeregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.anegativeregbtnId:
                regesterAnegative();
                break;
        }
    }

    private void regesterAnegative() {

        String newanegativereg= anegativereg.getText().toString().trim();
        String newanegativename=anegativename.getText().toString().trim();
        String newanegativegender=anegativegender.getText().toString().trim();
        String newanegativeemail=anegativeemail.getText().toString().trim();
        String newanegativephone=anegativephone.getText().toString().trim();
        String newanegativepassword=anegativepassword.getText().toString().trim();
        String newanegativedistrice=anegativedistrice.getText().toString().trim();
        String newanegativevillage=anegativevillage.getText().toString().trim();
        String newanegativeuniversity=anegativeuniversity.getText().toString().trim();
        String newanegativelastdonet=anegativelastdonet.getText().toString().trim();
        String newanegativeconfirmpassword=anegativeconfirmpassword.getText().toString().trim();

        if (newanegativereg.isEmpty())
        {
            anegativereg.setError("Please type your Blood Group");
            anegativereg.requestFocus();
            return;
        }if (newanegativename.isEmpty())
        {
            anegativename.setError("Please type your name");
            anegativename.requestFocus();
            return;
        }if (newanegativegender.isEmpty())
        {
            anegativegender.setError("Please type your gender");
            anegativegender.requestFocus();
            return;
        }if (newanegativeemail.isEmpty())
        {
            anegativeemail.setError("Please type your Email");
            anegativeemail.requestFocus();
            return;
        }if (newanegativephone.isEmpty())
        {
            anegativephone.setError("Please type your phone no");
            anegativephone.requestFocus();
            return;
        }if (newanegativepassword.isEmpty())
        {
            anegativepassword.setError("Please type your password for login");
            anegativepassword.requestFocus();
            return;
        }
        if (newanegativepassword.length()<6)
        {
            anegativepassword.setError("minimum 6 Character password");
            anegativepassword.requestFocus();
            return;
        }
        if (newanegativeconfirmpassword.length()<6)
        {
            anegativeconfirmpassword.setError("Password not Match");
            anegativeconfirmpassword.requestFocus();
            return;

        }if (!newanegativeconfirmpassword.equals(newanegativepassword))
        {
            anegativeconfirmpassword.setError("Password not Match");
            anegativeconfirmpassword.requestFocus();
            return;
        }


        if (newanegativedistrice.isEmpty())
        {
            anegativedistrice.setError("Please type your district");
            anegativedistrice.requestFocus();
            return;
        }if (newanegativevillage.isEmpty())
        {
            anegativevillage.setError("Please type your village");
            anegativevillage.requestFocus();
            return;
        }if (newanegativeuniversity.isEmpty())
        {
            anegativeuniversity.setError("Please type your university");
            anegativeuniversity.requestFocus();
            return;
        }if (newanegativelastdonet.isEmpty())
        {
            anegativelastdonet.setError("Please type your last blood donet date");
            anegativelastdonet.requestFocus();
            return;
        }
        database = FirebaseDatabase.getInstance().getReference().child("DATAANEGATIVE").child(anegativename.getText().toString());
        Map hashMap = new HashMap();

        hashMap.put("anegativereg",anegativereg.getText().toString().trim());
        hashMap.put("anegativename",anegativename.getText().toString().trim());
        hashMap.put("anegativegender",anegativegender.getText().toString().trim());
        hashMap.put("anegativeemail",anegativeemail.getText().toString().trim());
         hashMap.put("anegativephone",anegativephone.getText().toString().trim());
        hashMap.put("anegativepassword",anegativepassword.getText().toString().trim());
        hashMap.put("anegativedistrice",anegativedistrice.getText().toString().trim());
        hashMap.put("anegativevillage",anegativevillage.getText().toString().trim());
         hashMap.put("anegativeuniversity",anegativeuniversity.getText().toString().trim());
        hashMap.put("anegativelastdonet",anegativeuniversity.getText().toString().trim());

        hashMap.put("anegativeuserId",anegativename.getText().toString().trim());


        database.setValue(hashMap);

        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newanegativeemail,newanegativepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {


                    alertDialogBuilder=new AlertDialog.Builder(RegAnegativeActivity.this);
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
                        alertDialogBuilder=new AlertDialog.Builder(RegAnegativeActivity.this);
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