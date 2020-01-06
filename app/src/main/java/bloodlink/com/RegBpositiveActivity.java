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

public class RegBpositiveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView bpositivereg;
    private EditText bpositivename,bpositiveemail,bpositivephone,bpositivepassword,
            bpositivevillage,bpositiveuniversity,bpositivelastdonet,bpositiveconfirmpassword;
    private Button bpositiveregbtn;

    private FirebaseAuth mAuth;


    private AlertDialog.Builder alertDialogBuilder;

    private AutoCompleteTextView bpositivegender,bpositivedistrice;

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
        setContentView(R.layout.activity_reg_bpositive);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        setTitle("B positive (B+) Regestation Form :");



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        bpositivegender=findViewById(R.id.bpositivegenderId);
        bpositivegender.setThreshold(1);
        bpositivegender.setAdapter(new ArrayAdapter<>(RegBpositiveActivity.this,android.R.layout.simple_list_item_1,Genders));

        bpositivedistrice=findViewById(R.id.bpositivedistriceId);
        bpositivedistrice.setThreshold(1);
        bpositivedistrice.setAdapter(new ArrayAdapter<>(RegBpositiveActivity.this,android.R.layout.simple_list_item_1,districts));


        //text view
        bpositivereg=findViewById(R.id.bpositiveregId);

        //edittext id
        bpositivename=findViewById(R.id.bpositivenameId);
        bpositivegender=findViewById(R.id.bpositivegenderId);
        bpositiveemail=findViewById(R.id.bpositiveemailId);
        bpositivephone=findViewById(R.id.bpositivephoneId);
        bpositivepassword=findViewById(R.id.bpositivepasswordId);
        bpositivedistrice=findViewById(R.id.bpositivedistriceId);
        bpositivevillage=findViewById(R.id.bpositivevillageId);
        bpositiveuniversity=findViewById(R.id.bpositiveuniversityId);
        bpositivelastdonet=findViewById(R.id.bpositivelastdonetId);
        bpositiveconfirmpassword=findViewById(R.id.bpositiveconfirmpasswordId);

        //Button Id:
        bpositiveregbtn=findViewById(R.id.bpositiveregbtnId);
        bpositiveregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bpositiveregbtnId:
                regester();
                break;
        }
    }

    private void regester() {

        String newbpositivereg=bpositivereg.getText().toString().trim();
        String newbpositivename=bpositivename.getText().toString().trim();
        String newbpositivegender=bpositivegender.getText().toString().trim();
        String newbpositiveemail=bpositiveemail.getText().toString().trim();
        String newbpositivephone=bpositivephone.getText().toString().trim();
        String newbpositivepassword=bpositivepassword.getText().toString().trim();
        String newbpositivedistrice=bpositivedistrice.getText().toString().trim();
        String newbpositivevillage=bpositivevillage.getText().toString().trim();
        String newbpositiveuniversity=bpositiveuniversity.getText().toString().trim();
        String newbpositivelastdonet=bpositivelastdonet.getText().toString().trim();
        String newbpositiveconfirmpassword=bpositiveconfirmpassword.getText().toString().trim();



        if (newbpositivereg.isEmpty())
        {
            bpositivereg.setError("");
            bpositivereg.requestFocus();
            return;
        }
        if (newbpositivename.isEmpty())
        {
            bpositivename.setError("Please type your name");
            bpositivename.requestFocus();
            return;
        }

        if (newbpositivegender.isEmpty())
        {
            bpositivegender.setError("Please type your Gender");
            bpositivegender.requestFocus();
            return;
        }

        if (newbpositiveemail.isEmpty())
        {
            bpositiveemail.setError("Please type your Email");
            bpositiveemail.requestFocus();
            return;
        }

        if (newbpositivephone.isEmpty())
        {
            bpositivephone.setError("Please type your Phone no");
            bpositivephone.requestFocus();
            return;
        }

        if (newbpositivepassword.isEmpty())
        {
            bpositivepassword.setError("Please type your password");
            bpositivepassword.requestFocus();
            return;
        }if (newbpositivepassword.length()<6)
        {
            bpositivepassword.setError("Minimum 6 type password");
            bpositivepassword.requestFocus();
            return;
        }if (newbpositiveconfirmpassword.length()<6)
        {
            bpositiveconfirmpassword.setError("Password not match");
            bpositiveconfirmpassword.requestFocus();
            return;
        }if (!newbpositiveconfirmpassword.equals(newbpositivepassword))
        {
            bpositiveconfirmpassword.setError("Password not match");
            bpositiveconfirmpassword.requestFocus();
            return;
        }


        if (newbpositivedistrice.isEmpty())
        {
            bpositivedistrice.setError("Please type your password");
            bpositivedistrice.requestFocus();
            return;
        }if (newbpositivevillage.isEmpty())
        {
            bpositivevillage.setError("Please type your password");
            bpositivevillage.requestFocus();
            return;
        }if (newbpositiveuniversity.isEmpty())
        {
            bpositiveuniversity.setError("Please type your password");
            bpositiveuniversity.requestFocus();
            return;
        }if (newbpositivelastdonet.isEmpty())
        {
            bpositivelastdonet.setError("Please type your password");
            bpositivelastdonet.requestFocus();
            return;
        }

        database = FirebaseDatabase.getInstance().getReference().child("DATABPOSITIVE").child(bpositivename.getText().toString());
        Map hashMap = new HashMap();
        hashMap.put("bpositivereg",bpositivereg.getText().toString().trim());
        hashMap.put("bpositivename",bpositivename.getText().toString().trim());
        hashMap.put("bpositivegender",bpositivegender.getText().toString().trim());
        hashMap.put("bpositiveemail",bpositiveemail.getText().toString().trim());
        hashMap.put("bpositivephone",bpositivephone.getText().toString().trim());
        hashMap.put("bpositivepassword",bpositivepassword.getText().toString().trim());
        hashMap.put("bpositivedistrice",bpositivedistrice.getText().toString().trim());
        hashMap.put("bpositivevillage",bpositivevillage.getText().toString().trim());
        hashMap.put("bpositiveuniversity",bpositiveuniversity.getText().toString().trim());
        hashMap.put("bpositivelastdonet",bpositivelastdonet.getText().toString().trim());

        hashMap.put("bpositiveuserId",bpositivename.getText().toString().trim());


        database.setValue(hashMap);


        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newbpositiveemail,newbpositivepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {




                    alertDialogBuilder=new AlertDialog.Builder(RegBpositiveActivity.this);
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
                        alertDialogBuilder=new AlertDialog.Builder(RegBpositiveActivity.this);
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

        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}