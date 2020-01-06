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

public class RegOpositiveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView opositivereg;
    private EditText opositivename,opositiveemail,opositivephone,opositivepassword,
            opositivevillage,opositiveuniversity,opositivelastdonet,opositiveconfirmpassword;


    private AutoCompleteTextView opositivegender,opositivedistrice;
    private Button opositiveregbtn;

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
        setContentView(R.layout.activity_reg_opositive);




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

        setTitle("O positive (O+) Regestation Form :");


        opositivegender=findViewById(R.id.opositivegenderId);

        opositivegender.setThreshold(1);
        opositivegender.setAdapter(new ArrayAdapter<>(RegOpositiveActivity.this,android.R.layout.simple_list_item_1,Genders));

        opositivedistrice=findViewById(R.id.opositivedistriceId);
        opositivedistrice.setThreshold(1);
        opositivedistrice.setAdapter(new ArrayAdapter<>(RegOpositiveActivity.this,android.R.layout.simple_list_item_1,districts));


        //text view
        opositivereg=findViewById(R.id.opositiveregId);

        //edittext id
        opositivename=findViewById(R.id.opositivenameId);
        opositivegender=findViewById(R.id.opositivegenderId);
        opositiveemail=findViewById(R.id.opositiveemailId);
        opositivephone=findViewById(R.id.opositivephoneId);
        opositivepassword=findViewById(R.id.opositivepasswordId);
        opositivedistrice=findViewById(R.id.opositivedistriceId);
        opositivevillage=findViewById(R.id.opositivevillageId);
        opositiveuniversity=findViewById(R.id.opositiveuniversityId);
        opositivelastdonet=findViewById(R.id.opositivelastdonetId);
        opositiveconfirmpassword=findViewById(R.id.opositiveconfirmpasswordId);

        //Button Id:
        opositiveregbtn=findViewById(R.id.opositiveregbtnId);
        opositiveregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.opositiveregbtnId:
                regester();
                break;
        }
    }

    private void regester() {

        String newopositivereg=opositivereg.getText().toString().trim();
        String newopositivename=opositivename.getText().toString().trim();
        String newopositivegender=opositivegender.getText().toString().trim();
        String newopositiveemail=opositiveemail.getText().toString().trim();
        String newopositivephone=opositivephone.getText().toString().trim();
        String newopositivepassword=opositivepassword.getText().toString().trim();
        String newopositivedistrice=opositivedistrice.getText().toString().trim();
        String newopositivevillage=opositivevillage.getText().toString().trim();
        String newopositiveuniversity=opositiveuniversity.getText().toString().trim();
        String newopositivelastdonet=opositivelastdonet.getText().toString().trim();
        String newopositiveconfirmpassword=opositiveconfirmpassword.getText().toString().trim();


        if (newopositivereg.isEmpty())
        {
            opositivereg.setError("");
            opositivereg.requestFocus();
            return;
        }
        if (newopositivename.isEmpty())
        {
            opositivename.setError("Please type your name");
            opositivename.requestFocus();
            return;
        }if (newopositivegender.isEmpty())
        {
            opositivegender.setError("Please type your Gender");
            opositivegender.requestFocus();
            return;
        }
        if (newopositiveemail.isEmpty())
        {
            opositiveemail.setError("Please type your Email");
            opositiveemail.requestFocus();
            return;
        }


        if (newopositivephone.isEmpty())
        {
            opositivephone.setError("Please type your Phone no");
            opositivephone.requestFocus();
            return;
        }

        if (newopositivepassword.isEmpty())
        {
            opositivepassword.setError("Please type your password");
            opositivepassword.requestFocus();
            return;
        }if (newopositivepassword.length()<6)
        {
            opositivepassword.setError("Minimum 6 type password");
            opositivepassword.requestFocus();
            return;
        }


        if (!newopositiveconfirmpassword.equals(newopositivepassword))
        {
            opositiveconfirmpassword.setError("password not match");
            opositiveconfirmpassword.requestFocus();
            return;
        }




        if (newopositivedistrice.isEmpty())
        {
            opositivedistrice.setError("Please type your password");
            opositivedistrice.requestFocus();
            return;
        }if (newopositivevillage.isEmpty())
        {
            opositivevillage.setError("Please type your password");
            opositivevillage.requestFocus();
            return;
        }if (newopositiveuniversity.isEmpty())
        {
            opositiveuniversity.setError("Please type your password");
            opositiveuniversity.requestFocus();
            return;
        }if (newopositivelastdonet.isEmpty())
        {
            opositivelastdonet.setError("Please type your password");
            opositivelastdonet.requestFocus();
            return;
        }

        database = FirebaseDatabase.getInstance().getReference().child("DATAOPOSITIVE").child(opositivename.getText().toString());
        Map hashMap = new HashMap();
        hashMap.put("opositivereg",opositivereg.getText().toString().trim());
        hashMap.put("opositivename",opositivename.getText().toString().trim());
        hashMap.put("opositivegender",opositivegender.getText().toString().trim());
        hashMap.put("opositiveemail",opositiveemail.getText().toString().trim());
        hashMap.put("opositivephone",opositivephone.getText().toString().trim());
        hashMap.put("opositivepassword",opositivepassword.getText().toString().trim());
        hashMap.put("opositivedistrice",opositivedistrice.getText().toString().trim());
        hashMap.put("opositivevillage",opositivevillage.getText().toString().trim());
        hashMap.put("opositiveuniversity",opositiveuniversity.getText().toString().trim());
        hashMap.put("opositivelastdonet",opositivelastdonet.getText().toString().trim());

        hashMap.put("opositiveuserId",opositivename.getText().toString().trim());


        database.setValue(hashMap);


        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newopositiveemail,newopositivepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {



                    alertDialogBuilder=new AlertDialog.Builder(RegOpositiveActivity.this);
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
                        alertDialogBuilder=new AlertDialog.Builder(RegOpositiveActivity.this);
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

