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

public class RegABpositiveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView abpositivereg;
    private EditText abpositivename,abpositiveemail,abpositivephone,abpositivepassword,
            abpositivevillage,abpositiveuniversity,abpositivelastdonet,abpositiveconfirmpassword;

    private Button abpositiveregbtn;

    private AutoCompleteTextView abpositivegender,abpositivedistrice;

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
        setContentView(R.layout.activity_reg_abpositive);


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
        setTitle("AB positive (AB+) Regestation Form :");

        abpositivegender=findViewById(R.id.abpositivegenderId);

        abpositivegender.setThreshold(1);
        abpositivegender.setAdapter(new ArrayAdapter<>(RegABpositiveActivity.this,android.R.layout.simple_list_item_1,Genders));

        abpositivedistrice=findViewById(R.id.abpositivedistriceId);
        abpositivedistrice.setThreshold(1);
        abpositivedistrice.setAdapter(new ArrayAdapter<>(RegABpositiveActivity.this,android.R.layout.simple_list_item_1,districts));



        //text view
        abpositivereg=findViewById(R.id.abpositiveregId);

        //edittext id
        abpositivename=findViewById(R.id.abpositivenameId);
        abpositivegender=findViewById(R.id.abpositivegenderId);
        abpositiveemail=findViewById(R.id.abpositiveemailId);
        abpositivephone=findViewById(R.id.abpositivephoneId);
        abpositivepassword=findViewById(R.id.abpositivepasswordId);
        abpositivedistrice=findViewById(R.id.abpositivedistriceId);
        abpositivevillage=findViewById(R.id.abpositivevillageId);
        abpositiveuniversity=findViewById(R.id.abpositiveuniversityId);
        abpositivelastdonet=findViewById(R.id.abpositivelastdonetId);
        abpositiveconfirmpassword=findViewById(R.id.abpositiveconfirmpasswordId);

        //Button Id:
        abpositiveregbtn=findViewById(R.id.abpositiveregbtnId);
        abpositiveregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.abpositiveregbtnId:
                regester();
                break;
        }
    }

    private void regester() {

        String newabpositivereg=abpositivereg.getText().toString().trim();
        String newabpositivename=abpositivename.getText().toString().trim();
        String newabpositivegender=abpositivegender.getText().toString().trim();
        String newabpositiveemail=abpositiveemail.getText().toString().trim();
        String newabpositivephone=abpositivephone.getText().toString().trim();
        String newabpositivepassword=abpositivepassword.getText().toString().trim();
        String newabpositivedistrice=abpositivedistrice.getText().toString().trim();
        String newabpositivevillage=abpositivevillage.getText().toString().trim();
        String newabpositiveuniversity=abpositiveuniversity.getText().toString().trim();
        String newabpositivelastdonet=abpositivelastdonet.getText().toString().trim();
        String newabpositiveconfirmpassword=abpositiveconfirmpassword.getText().toString().trim();



        if (newabpositivereg.isEmpty())
        {
            abpositivereg.setError("");
            abpositivereg.requestFocus();
            return;
        }
        if (newabpositivename.isEmpty())
        {
            abpositivename.setError("Please type your name");
            abpositivename.requestFocus();
            return;
        }if (newabpositivegender.isEmpty())
        {
            abpositivegender.setError("Please type your Gender");
            abpositivegender.requestFocus();
            return;
        }

        if (newabpositiveemail.isEmpty())
        {
            abpositiveemail.setError("Please type your Email");
            abpositiveemail.requestFocus();
            return;
        }

        if (newabpositivephone.isEmpty())
        {
            abpositivephone.setError("Please type your Phone no");
            abpositivephone.requestFocus();
            return;
        }

        if (newabpositivepassword.isEmpty())
        {
            abpositivepassword.setError("Please type your password");
            abpositivepassword.requestFocus();
            return;
        }if (newabpositivepassword.length()<6)
        {
            abpositivepassword.setError("Minimum 6 character password");
            abpositivepassword.requestFocus();
            return;
        }if (!newabpositiveconfirmpassword.equals(newabpositivepassword))
        {
            abpositiveconfirmpassword.setError("password not match");
            abpositiveconfirmpassword.requestFocus();
            return;
        }

        if (newabpositivedistrice.isEmpty())
        {
            abpositivedistrice.setError("Please select your district");
            abpositivedistrice.requestFocus();
            return;
        }

        if (newabpositivevillage.isEmpty())
        {
            abpositivevillage.setError("Please type your villege");
            abpositivevillage.requestFocus();
            return;
        }

        if (newabpositiveuniversity.isEmpty())
        {
            abpositiveuniversity.setError("Please type your University");
            abpositiveuniversity.requestFocus();
            return;
        }

        if (newabpositivelastdonet.isEmpty())
        {
            abpositivelastdonet.setError("Please slecte you last donate date");
            abpositivelastdonet.requestFocus();
            return;
        }

        database = FirebaseDatabase.getInstance().getReference().child("DATAABPOSITIVE").child(abpositivename.getText().toString());
        Map hashMap = new HashMap();
        hashMap.put("abpositivereg",abpositivereg.getText().toString().trim());
        hashMap.put("abpositivename",abpositivename.getText().toString().trim());
        hashMap.put("abpositivegender",abpositivegender.getText().toString().trim());
        hashMap.put("abpositiveemail",abpositiveemail.getText().toString().trim());
        hashMap.put("abpositivephone",abpositivephone.getText().toString().trim());
        hashMap.put("abpositivepassword",abpositivepassword.getText().toString().trim());
        hashMap.put("abpositivedistrice",abpositivedistrice.getText().toString().trim());
        hashMap.put("abpositivevillage",abpositivevillage.getText().toString().trim());
        hashMap.put("abpositiveuniversity",abpositiveuniversity.getText().toString().trim());
        hashMap.put("abpositivelastdonet",abpositivelastdonet.getText().toString().trim());

        hashMap.put("abpositiveuserId",abpositivename.getText().toString().trim());


        database.setValue(hashMap);


        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newabpositiveemail,newabpositivepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {




                    alertDialogBuilder=new AlertDialog.Builder(RegABpositiveActivity.this);
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
                        alertDialogBuilder=new AlertDialog.Builder(RegABpositiveActivity.this);
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
    }@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}