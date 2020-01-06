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

public class RegApositiveActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView apositivereg;
    private EditText apositivename,apositiveemail,apositivephone,apositivepassword,apositivevillage,
            apositiveuniversity,apositivelastdonet,apositiveconfirmpassword;
    private Button apositiveregbtn;

    private AutoCompleteTextView apositivegender,apositivedistrice;
    private FirebaseAuth mAuth;


    private AlertDialog.Builder alertDialogBuilder;


     String[]Genders=new String[]{"Male","Female"};

    String []districts = new String[]{"Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur",
            "Chittagong","Comilla","Cox's Bazar","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati","Dhaka","Faridpur","Gazipur",
            "Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi",
            "Netrakona","Rajbari","Shariatpur","Sherpur","Tangail","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna",
            "Kushtia","Magura","Meherpur","Narail","Satkhira","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi",
            "Sirajganj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Habiganj",
            "Moulvibazar","Sunamganj","Sylhet",};

    private DatabaseReference database;
    private FirebaseDatabase databasePUSH;

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_apositive);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("A positive (A+) Regestation Form :");


        //autocomplet texview
        apositivegender=findViewById(R.id.apositivegenderId);
        apositivegender.setThreshold(1);
        apositivegender.setAdapter(new ArrayAdapter<>(RegApositiveActivity.this,android.R.layout.simple_list_item_1,Genders));

        apositivedistrice=findViewById(R.id.apositivedistriceId);
        apositivedistrice.setThreshold(1);
        apositivedistrice.setAdapter(new ArrayAdapter<>(RegApositiveActivity.this,android.R.layout.simple_list_item_1,districts));

        //text view
        apositivereg=findViewById(R.id.apositiveregId);

        //edittext id
        apositivename=findViewById(R.id.apositivenameId);

        apositiveemail=findViewById(R.id.apositiveemailId);
        apositivephone=findViewById(R.id.apositivephoneId);
        apositivepassword=findViewById(R.id.apositivepasswordId);

        apositivevillage=findViewById(R.id.apositivevillageId);
        apositiveuniversity=findViewById(R.id.apositiveuniversityId);
        apositivelastdonet=findViewById(R.id.apositivelastdonetId);
        apositiveconfirmpassword=findViewById(R.id.apositiveconfirmpasswordId);

        //Button Id:
        apositiveregbtn=findViewById(R.id.apositiveregbtnId);
        apositiveregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apositiveregbtnId:
                regester();
                break;
        }
    }

    private void regester() {

        String newapositivereg=apositivereg.getText().toString().trim();

        String newapositivename=apositivename.getText().toString().trim();

        String newapositivegender=apositivegender.getText().toString().trim();

        String newapositiveemail=apositiveemail.getText().toString().trim();

        String newapositivephone=apositivephone.getText().toString().trim();

        String newapositivepassword=apositivepassword.getText().toString().trim();

        String newapositivedistrice=apositivedistrice.getText().toString().trim();

        String newapositivevillage=apositivevillage.getText().toString().trim();

        String newapositiveuniversity=apositiveuniversity.getText().toString().trim();

        String newapositivelastdonet=apositivelastdonet.getText().toString().trim();

        String newapositiveconfirmpassword=apositiveconfirmpassword.getText().toString().trim();



            if (newapositivereg.isEmpty())
            {
                apositivereg.setError("");
                apositivereg.requestFocus();
                return;
            }



            if (newapositivename.isEmpty())
            {
                apositivename.setError("Please type your name");
                apositivename.requestFocus();
                return;
            }

            if (newapositivegender.isEmpty())
            {
                apositivegender.setError("Please Select your Gender");
                apositivegender.requestFocus();
                return;
            }

            if (newapositiveemail.isEmpty())
            {
                apositiveemail.setError("Please type your Email");
                apositiveemail.requestFocus();
                return;
            }

            if (newapositivephone.isEmpty())
            {
                apositivephone.setError("Please type your Phone no");
                apositivephone.requestFocus();
                return;
            }

            if (newapositivephone.length()<14)
            {
                apositivephone.setError("Please type your Phone no");
                apositivephone.requestFocus();
                return;
            }

            if (newapositivepassword.isEmpty())
            {
                apositivepassword.setError("Please type password");
                apositivepassword.requestFocus();
                return;
            }if (newapositivepassword.length()<6)
            {
                apositivepassword.setError("minimum 6 character password");
                apositivepassword.requestFocus();
                return;
            }
            if (!newapositiveconfirmpassword.equals(newapositivepassword))
            {
                apositiveconfirmpassword.setError("password not match");
                apositiveconfirmpassword.requestFocus();
                return;
            }if (newapositiveconfirmpassword.length()<6)
            {
                apositiveconfirmpassword.setError("password not match");
                apositiveconfirmpassword.requestFocus();
                return;
            }


           if (newapositivedistrice.isEmpty())
            {
                apositivedistrice.setError("Please select your districe");
                apositivedistrice.requestFocus();
                return;
            }
           if (newapositivevillage.isEmpty())
            {
                apositivevillage.setError("Please type your villege");
                apositivevillage.requestFocus();
                return;
            }
           if (newapositiveuniversity.isEmpty())
            {
                apositiveuniversity.setError("Please type your university");
                apositiveuniversity.requestFocus();
                return;
            }
           if (newapositivelastdonet.isEmpty())
            {
                apositivelastdonet.setError("Please type your last donner date");
                apositivelastdonet.requestFocus();
                return;
            }

            database = FirebaseDatabase.getInstance().getReference().child("DATAAPOSITIVE").child(apositivename.getText().toString());
            Map hashMap = new HashMap();
            hashMap.put("apositivereg",apositivereg.getText().toString().trim());
            hashMap.put("apositivename",apositivename.getText().toString().trim());
            hashMap.put("apositivegender",apositivegender.getText().toString().trim());
            hashMap.put("apositiveemail",apositiveemail.getText().toString().trim());
            hashMap.put("apositivephone",apositivephone.getText().toString().trim());
            hashMap.put("apositivepassword",apositivepassword.getText().toString().trim());
            hashMap.put("apositivedistrice",apositivedistrice.getText().toString().trim());
            hashMap.put("apositivevillage",apositivevillage.getText().toString().trim());
            hashMap.put("apositiveuniversity",apositiveuniversity.getText().toString().trim());
            hashMap.put("apositivelastdonet",apositivelastdonet.getText().toString().trim());

            hashMap.put("apositiveuserId",apositivename.getText().toString().trim());


            database.setValue(hashMap);


            Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


            mAuth.createUserWithEmailAndPassword(newapositiveemail,newapositivepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {



                        alertDialogBuilder=new AlertDialog.Builder(RegApositiveActivity.this);
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
                            alertDialogBuilder=new AlertDialog.Builder(RegApositiveActivity.this);
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
        }

        @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    }

