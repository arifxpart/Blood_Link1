package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Click2Activity extends AppCompatActivity {


    private TextView show2bloodgroup,show2rugerproblem,show2howmanybag,show2date,show2time,show2hospital,show2relaction,show2phone,
            show2gogagogname,show2district,showdonnername,showdonnerphone;

    private ImageView show2callbtn,show2sharebtn,showaccpetcallbtn;


    private DatabaseReference database;
    private String push;
    List<DataPost> dataPosts;

    private AdView mAdView;
    private AlertDialog.Builder alertDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click2);





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


        show2callbtn=findViewById(R.id.show2callbtnId);
        show2sharebtn=findViewById(R.id.show2sharebtnId);

        showaccpetcallbtn=findViewById(R.id.showaccpetcallbtnId);

        show2district=findViewById(R.id.show2districtId);
        show2bloodgroup=findViewById(R.id.show2bloodgroupId);
        show2rugerproblem=findViewById(R.id.show2rugerproblemId);
        show2howmanybag=findViewById(R.id.show2howmanybagId);
        show2date=findViewById(R.id.show2dateId);
        show2time=findViewById(R.id.show2timeId);
        show2hospital=findViewById(R.id.show2hospitalId);
        show2relaction=findViewById(R.id.show2relactionId);
        show2phone=findViewById(R.id.show2phoneId);
        show2gogagogname=findViewById(R.id.show2gogagognameId);


        showdonnername=findViewById(R.id.showdonnernameId);
        showdonnerphone=findViewById(R.id.showdonnerphoneId);








        dataPosts= new ArrayList<>();

        final Intent intent = getIntent();
        show2bloodgroup.setText(intent.getExtras().get("postbloodgroup").toString());
        show2rugerproblem.setText(intent.getExtras().get("postrugerproblem").toString());
        show2howmanybag.setText(intent.getExtras().get("posthowmanybag").toString());
        show2date.setText(intent.getExtras().get("postdata").toString());
        show2hospital.setText(intent.getExtras().get("posthospital").toString());
        show2time.setText(intent.getExtras().get("posttime").toString());
        show2relaction.setText(intent.getExtras().get("postrelaction").toString());
        show2phone.setText(intent.getExtras().get("postnumber").toString());
        show2gogagogname.setText(intent.getExtras().get("userid").toString());
        show2district.setText(intent.getExtras().get("postdistrict").toString());


        showdonnername.setText(intent.getExtras().get("donnername").toString());
        showdonnerphone.setText(intent.getExtras().get("donnerphone").toString());


        push = intent.getStringExtra("userid");

        database = FirebaseDatabase.getInstance().getReference().child("DATA2").child(push);


        showaccpetcallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);

                String phone=showdonnerphone.getText().toString();

                try {
                    if (phone.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Number not Foount",Toast.LENGTH_LONG).show();

                    }else {
                        intent.setData(Uri.parse("tel:"+phone));

                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(Click2Activity.this,"Please Grant Permission",Toast.LENGTH_LONG).show();
                        requestpermision();
                    }
                    else {
                        startActivity(intent);
                    }
                }catch (Exception e){

                }

            }private void requestpermision(){

                ActivityCompat.requestPermissions(Click2Activity.this,new String[]{Manifest.permission.CALL_PHONE},1);

            }
        });

        show2callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialogBuilder=new AlertDialog.Builder(Click2Activity.this);
                alertDialogBuilder.setTitle("Notice");
                alertDialogBuilder.setMessage(R.string.notice);
                alertDialogBuilder.setIcon(R.drawable.distrub);

                alertDialogBuilder.setPositiveButton("CALL", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent(Intent.ACTION_DIAL);

                        String phone=show2phone.getText().toString();

                        try {
                            if (phone.trim().isEmpty()){
                                Toast.makeText(getApplicationContext(),"Number not Foount",Toast.LENGTH_LONG).show();

                            }else {
                                intent.setData(Uri.parse("tel:"+phone));

                            }
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                                Toast.makeText(Click2Activity.this,"Please Grant Permission",Toast.LENGTH_LONG).show();
                                requestpermision();
                            }
                            else {
                                startActivity(intent);
                            }
                        }catch (Exception e){

                        }


                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();



            }
            private void requestpermision(){

                ActivityCompat.requestPermissions(Click2Activity.this,new String[]{Manifest.permission.CALL_PHONE},1);

            }
        });

        show2sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intentshare = new Intent(Intent.ACTION_SEND);
                intentshare.setType("text/plain");

                String body= "Emergency Blood Needed"+"\n"+

                        "রক্তের গ্রুপঃ"+show2bloodgroup.getText().toString()+"\n"+
                        "রোগীর সমস্যাঃ"+show2rugerproblem.getText().toString()+"\n"+
                        "রক্তের পরিমাণঃ"+show2howmanybag.getText().toString()+"\n"+
                        "জেলাঃ"+show2district.getText().toString()+"\n"+
                        "তারিখঃ"+show2date.getText().toString()+"\n"+
                        "সময়ঃ"+show2time.getText().toString()+"\n"+

                        "রক্তদানের স্থানঃ"+show2hospital.getText().toString()+"\n"+
                        "মোবাইল নংঃ"+show2phone.getText().toString()
                        ;


                intentshare.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(intentshare,"Complet share"));
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
