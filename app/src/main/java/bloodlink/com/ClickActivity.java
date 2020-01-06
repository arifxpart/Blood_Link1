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

public class ClickActivity extends AppCompatActivity {


    private TextView showbloodgroup,showname,showdistrict,showuniversity,showphone,showvillage;

    private ImageView callbtn,share2btn;

    private AlertDialog.Builder alertDialogBuilder;

    private DatabaseReference database;
    private String push;
    List<DataApositive> dataApositives;

    private AdView mAdView;
    private AdView mAdView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);


        setTitle("Donner Information : ");

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


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView2 = findViewById(R.id.adView2);

        mAdView2.loadAd(adRequest);


        callbtn=findViewById(R.id.callbtnId);



        showvillage=findViewById(R.id.showvillageId);
        showbloodgroup=findViewById(R.id.showbloodgroupId);
        showname=findViewById(R.id.shownameId);
        showdistrict=findViewById(R.id.showdistrictId);
        showuniversity=findViewById(R.id.showuniversityId);
        showphone=findViewById(R.id.showphoneId);

        share2btn=findViewById(R.id.share2btnId);


        dataApositives= new ArrayList<>();

        final Intent intent = getIntent();
        showbloodgroup.setText(intent.getExtras().get("apositivereg").toString());
        showname.setText(intent.getExtras().get("apositivename").toString());
        showdistrict.setText(intent.getExtras().get("apositivedistrice").toString());
        showuniversity.setText(intent.getExtras().get("apositiveuniversity").toString());
        showphone.setText(intent.getExtras().get("apositivephone").toString());
        showvillage.setText(intent.getExtras().get("apositivevillage").toString());

        push = intent.getStringExtra("apositiveuserId");

        database = FirebaseDatabase.getInstance().getReference().child("DATAAPOSITIVE").child(push);



        share2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentshare2 = new Intent(Intent.ACTION_SEND);
                intentshare2.setType("text/plain");

                String body= "Blood Donner Info: "+"\n"+

                        "রক্তের গ্রুপঃ "+showbloodgroup.getText().toString()+"\n"+

                        "রক্তদাতার নামঃ "+showname.getText().toString()+"\n"+

                        "জেলাঃ "+showdistrict.getText().toString()+"\n"+

                        "মোবাইল নংঃ "+showphone.getText().toString();



                intentshare2.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(intentshare2,"Complet share"));

            }
        });


        //for Call
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialogBuilder=new AlertDialog.Builder(ClickActivity.this);
                alertDialogBuilder.setTitle("Notice");
                alertDialogBuilder.setMessage(R.string.notice);
                alertDialogBuilder.setIcon(R.drawable.notice);

                alertDialogBuilder.setPositiveButton("CALL", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent=new Intent(Intent.ACTION_DIAL);

                        String phone=showphone.getText().toString();

                        try {
                            if (phone.trim().isEmpty()){
                                Toast.makeText(getApplicationContext(),"Number not Foount",Toast.LENGTH_LONG).show();

                            }else {
                                intent.setData(Uri.parse("tel:"+phone));

                            }
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                                Toast.makeText(ClickActivity.this,"Please Grant Permission",Toast.LENGTH_LONG).show();
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

                ActivityCompat.requestPermissions(ClickActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

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
