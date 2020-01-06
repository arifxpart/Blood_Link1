package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PostCreateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText postrugerproblem,postdata,posthowmanybag,posttime,posthospital,postname,postrelaction,postnumber;




    private Button postsavebtn;



    private AutoCompleteTextView postdistrict,postbloodgroup;



    String []bloodgroups = new String[]{"B+","B-","A+","A-","O-","O+","AB-","AB+"};


    String[]districts=new String[]{"Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur",
            "Chittagong","Comilla","Cox's Bazar","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati","Dhaka","Faridpur","Gazipur",
            "Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi",
            "Netrakona","Rajbari","Shariatpur","Sherpur","Tangail","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna",
            "Kushtia","Magura","Meherpur","Narail","Satkhira","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi",
            "Sirajganj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Habiganj",
            "Moulvibazar","Sunamganj","Sylhet",

    };
    private AdView mAdView;


    String postdonnername="";
    String postdonnerphone="";

    private DatabaseReference database;
    private FirebaseDatabase databasePUSH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_create);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        setTitle("Create Post");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        donnername.setText(postdonnername.toString().trim());
//        donnerphone.setText(postdonnerphone.toString().trim());

        postdistrict=findViewById(R.id.postdistrictId);
        postdistrict.setThreshold(1);
        postdistrict.setAdapter(new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,districts));



        postbloodgroup=findViewById(R.id.postbloodgroupId);

        postbloodgroup.setThreshold(1);
        postbloodgroup.setAdapter(new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,bloodgroups));


        postrugerproblem=findViewById(R.id.postrugerproblemId);
        posthowmanybag=findViewById(R.id.posthowmanybagId);
        postdata=findViewById(R.id.postdataId);
        posttime=findViewById(R.id.posttimeId);
        posthospital=findViewById(R.id.posthospitalId);
        postname=findViewById(R.id.postnameId);
        postrelaction=findViewById(R.id.postrelactionId);
        postnumber=findViewById(R.id.postnumberId);


//        donnername=findViewById(R.id.donnernameId);
//        donnerphone=findViewById(R.id.donnerphoneId);



        postsavebtn=findViewById(R.id.postsavebtnId);


        postsavebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.postsavebtnId:

                createpost();
                break;
        }

    }

    private void createpost() {

        String newpostbloodgroup=postbloodgroup.getText().toString().trim();
        String newpostrugerproblem=postrugerproblem.getText().toString().trim();
        String newposthowmanybag=posthowmanybag.getText().toString().trim();
        String newpostdata=postdata.getText().toString().trim();
        String newposttime=posttime.getText().toString().trim();
        String newposthospital=posthospital.getText().toString().trim();
        String newpostname=postname.getText().toString().trim();
        String newpostrelaction=postrelaction.getText().toString().trim();
        String newpostnumber=postnumber.getText().toString().trim();
        String newpostdistrict=postdistrict.getText().toString().trim();



//        String newdonnername=donnername.getText().toString().trim();
//        String newdonnerphone=donnerphone.getText().toString().trim();

        try {

            if (newpostbloodgroup.isEmpty()){
                postbloodgroup.setError("Please Type Your Blood Group");
                postbloodgroup.requestFocus();
                return;
            }if (newpostrugerproblem.isEmpty()){
                postrugerproblem.setError("Please Type Your Problem");
                postrugerproblem.requestFocus();
                return;
            }if (newposthowmanybag.isEmpty()){
                posthowmanybag.setError("how many bag of blood");
                posthowmanybag.requestFocus();
                return;
            }if (newpostdata.isEmpty()){
                postdata.setError("Write Your Date");
                postdata.requestFocus();
                return;
            }if (newposttime.isEmpty()){
                posttime.setError("Please Type Your Time");
                posttime.requestFocus();
                return;
            }if (newposthospital.isEmpty()){
                posthospital.setError("Please Type Hospital Name");
                posthospital.requestFocus();
                return;
            }if (newpostname.isEmpty()){
                postname.setError("Please Type Your Name");
                postname.requestFocus();
                return;
            }if (newpostrelaction.isEmpty()){
                postrelaction.setError("Relationship");
                postrelaction.requestFocus();
                return;
            }if (newpostnumber.isEmpty()){
                postnumber.setError("Please Type Your Phone Number");
                postnumber.requestFocus();
                return;
            }if (newpostdistrict.isEmpty()){
                postdistrict.setError("Please Type Your District");
                postdistrict.requestFocus();
                return;
            }








            database = FirebaseDatabase.getInstance().getReference().child("DATA2").child(postname.getText().toString());

            Map hashMap = new HashMap();
            hashMap.put("postbloodgroup",postbloodgroup.getText().toString().trim());
            hashMap.put("postrugerproblem",postrugerproblem.getText().toString().trim());
            hashMap.put("posthowmanybag",posthowmanybag.getText().toString().trim());
            hashMap.put("postdata",postdata.getText().toString().trim());
            hashMap.put("posttime",posttime.getText().toString().trim());
            hashMap.put("posthospital",posthospital.getText().toString().trim());
            hashMap.put("postname",postname.getText().toString().trim());
            hashMap.put("postrelaction",postrelaction.getText().toString().trim());
            hashMap.put("postnumber",postnumber.getText().toString().trim());
            hashMap.put("postdistrict",postdistrict.getText().toString().trim());


            hashMap.put("donnername",postdonnername.toString().trim());
            hashMap.put("donnerphone",postdonnerphone.toString().trim());




            hashMap.put("userid",postname.getText().toString());

            database.setValue(hashMap);

            Toast.makeText(getApplicationContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();

        }catch (Exception e){

            postname.requestFocus();
            postname.setError("Do Not Use . or ()");
            return;
        }

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
