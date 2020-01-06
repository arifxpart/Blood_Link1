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

public class RegABnegativeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView abnegativereg;

    private EditText abnegativename,abnegativeemail,abnegativephone,abnegativepassword,
            abnegativevillage,abnegativeuniversity,abnegativelastdonet,abnegativeconfirmpassword;

    private AutoCompleteTextView abnegativegender,abnegativedistrice;

    private Button abnegativeregbtn;

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
        setContentView(R.layout.activity_reg_abnegative);

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


        abnegativegender=findViewById(R.id.abnegativegenderId);

        abnegativegender.setThreshold(1);
        abnegativegender.setAdapter(new ArrayAdapter<>(RegABnegativeActivity.this,android.R.layout.simple_list_item_1,Genders));

        abnegativedistrice=findViewById(R.id.abnegativedistriceId);
        abnegativedistrice.setThreshold(1);
        abnegativedistrice.setAdapter(new ArrayAdapter<>(RegABnegativeActivity.this,android.R.layout.simple_list_item_1,districts));


        setTitle("AB Negative (AB-) Regestation Form :");

        abnegativereg=findViewById(R.id.abnegativeregId);

        abnegativename=findViewById(R.id.abnegativenameId);
        abnegativegender=findViewById(R.id.abnegativegenderId);
        abnegativeemail=findViewById(R.id.abnegativeemailId);
        abnegativephone=findViewById(R.id.abnegativephoneId);
        abnegativepassword=findViewById(R.id.abnegativepasswordId);
        abnegativedistrice=findViewById(R.id.abnegativedistriceId);
        abnegativevillage=findViewById(R.id.abnegativevillageId);
        abnegativeuniversity=findViewById(R.id.abnegativeuniversityId);
        abnegativelastdonet=findViewById(R.id.abnegativelastdonetId);
        abnegativeconfirmpassword=findViewById(R.id.abnegativeconfirmpasswordId);

        abnegativeregbtn=findViewById(R.id.abnegativeregbtnId);
        abnegativeregbtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.abnegativeregbtnId:
                regesterAnegative();
                break;
        }
    }

    private void regesterAnegative() {

        String newabnegativereg= abnegativereg.getText().toString().trim();
        String newabnegativename=abnegativename.getText().toString().trim();
        String newabnegativegender=abnegativegender.getText().toString().trim();
        String newabnegativeemail=abnegativeemail.getText().toString().trim();
        String newabnegativephone=abnegativephone.getText().toString().trim();
        String newabnegativepassword=abnegativepassword.getText().toString().trim();
        String newabnegativedistrice=abnegativedistrice.getText().toString().trim();
        String newabnegativevillage=abnegativevillage.getText().toString().trim();
        String newabnegativeuniversity=abnegativeuniversity.getText().toString().trim();
        String newabnegativelastdonet=abnegativelastdonet.getText().toString().trim();
        String newconfirmpassword=abnegativeconfirmpassword.getText().toString().trim();

        if (newabnegativereg.isEmpty())
        {
            abnegativereg.setError("Please type your Blood Group");
            abnegativereg.requestFocus();
            return;
        }
        if (newabnegativename.isEmpty())
        {
            abnegativename.setError("Please type your name");
            abnegativename.requestFocus();
            return;
        }
        if (newabnegativegender.isEmpty())
        {
            abnegativegender.setError("Please type your gender");
            abnegativegender.requestFocus();
            return;
        }
        if (newabnegativeemail.isEmpty())
        {
            abnegativeemail.setError("Please type your Email");
            abnegativeemail.requestFocus();
            return;
        }
        if (newabnegativephone.isEmpty())
        {
            abnegativephone.setError("Please type your phone no");
            abnegativephone.requestFocus();
            return;
        }

        if (newabnegativepassword.length()<6)
        {
            abnegativepassword.setError("Minimum 6 chareter password");
            abnegativepassword.requestFocus();
            return;
        }

        if (!newconfirmpassword.equals(newabnegativepassword))
        {
            abnegativeconfirmpassword.setError("Password not match");
            abnegativeconfirmpassword.requestFocus();
            return;
        }


        if (newabnegativedistrice.isEmpty())
        {
            abnegativedistrice.setError("Please type your district");
            abnegativedistrice.requestFocus();
            return;
        }if (newabnegativevillage.isEmpty())
        {
            abnegativevillage.setError("Please type your village");
            abnegativevillage.requestFocus();
            return;
        }if (newabnegativeuniversity.isEmpty())
        {
            abnegativeuniversity.setError("Please type your university");
            abnegativeuniversity.requestFocus();
            return;
        }if (newabnegativelastdonet.isEmpty())
        {
            abnegativelastdonet.setError("Please type your last blood donet date");
            abnegativelastdonet.requestFocus();
            return;
        }
        database = FirebaseDatabase.getInstance().getReference().child("DATAABNEGATIVE").child(abnegativename.getText().toString());
        Map hashMap = new HashMap();

        hashMap.put("abnegativereg",abnegativereg.getText().toString().trim());
        hashMap.put("abnegativename",abnegativename.getText().toString().trim());
        hashMap.put("abnegativegender",abnegativegender.getText().toString().trim());
        hashMap.put("abnegativeemail",abnegativeemail.getText().toString().trim());
        hashMap.put("abnegativephone",abnegativephone.getText().toString().trim());
        hashMap.put("abnegativepassword",abnegativepassword.getText().toString().trim());
        hashMap.put("abnegativedistrice",abnegativedistrice.getText().toString().trim());
        hashMap.put("abnegativevillage",abnegativevillage.getText().toString().trim());
        hashMap.put("abnegativeuniversity",abnegativeuniversity.getText().toString().trim());
        hashMap.put("abnegativelastdonet",abnegativeuniversity.getText().toString().trim());

        hashMap.put("abnegativeuserId",abnegativename.getText().toString().trim());


        database.setValue(hashMap);

        Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(newabnegativeemail,newabnegativepassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {



                    alertDialogBuilder=new AlertDialog.Builder(RegABnegativeActivity.this);
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
                        alertDialogBuilder=new AlertDialog.Builder(RegABnegativeActivity.this);
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