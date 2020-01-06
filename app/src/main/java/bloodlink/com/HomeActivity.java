package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener , NavigationView.OnNavigationItemSelectedListener {

    private CardView searchCard,createPostCard,statuscard,bloodbankscard,factscard,aboutcard,ambulancecard;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    private long backPressedTime;
    private Toast backToast;


    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder alertDialogBuilder;


    private AdView mAdView;
    private AdView mAdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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



        mAuth=FirebaseAuth.getInstance();
        currentUser =mAuth.getCurrentUser();


        NavigationView navigationView =findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);

        searchCard=findViewById(R.id.searchCardId);
        createPostCard=findViewById(R.id.createPostCardId);
        statuscard=findViewById(R.id.statuscardId);
        bloodbankscard=findViewById(R.id.bloodbankscardId);
        factscard=findViewById(R.id.factscardId);
        aboutcard=findViewById(R.id.aboutcardId);
        ambulancecard=findViewById(R.id.ambulancecardId);



        createPostCard.setOnClickListener(this);
        searchCard.setOnClickListener(this);
        statuscard.setOnClickListener(this);
        bloodbankscard.setOnClickListener(this);
        factscard.setOnClickListener(this);
        aboutcard.setOnClickListener(this);
        ambulancecard.setOnClickListener(this);

        drawerLayout=findViewById(R.id.drawerId);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.searchCardId:
                Intent intentsearch=new Intent(getApplicationContext(),SearchItemActivity.class);
                startActivity(intentsearch);
                break;


            case R.id.createPostCardId:
                Intent intentpost=new Intent(getApplicationContext(),PostCreateActivity.class);
                startActivity(intentpost);
                break;


            case R.id.statuscardId:
                Intent intentpostshow=new Intent(getApplicationContext(),ShowPostActivity.class);
                startActivity(intentpostshow);
                break;

                case R.id.bloodbankscardId:
                Intent intentblodbank=new Intent(getApplicationContext(),BloodBankActivity.class);
                startActivity(intentblodbank);
                break;

            case R.id.factscardId:
                Intent intentfact=new Intent(getApplicationContext(),FactActivity.class);
                startActivity(intentfact);
                break;

            case R.id.aboutcardId:
                Intent intentabout=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intentabout);
                break;

            case R.id.ambulancecardId:


                alertDialogBuilder=new AlertDialog.Builder(HomeActivity.this);
                alertDialogBuilder.setTitle("Call 999 or Search");
                alertDialogBuilder.setMessage(R.string.ambulance);
                alertDialogBuilder.setIcon(R.drawable.callbutton);

                alertDialogBuilder.setNegativeButton("Ambulance Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent intentambulance=new Intent(getApplicationContext(),AmbulanceActivity.class);
                        startActivity(intentambulance);
                    }
                });

                alertDialogBuilder.setPositiveButton("Call 999", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Intent.ACTION_DIAL);

                        String phone="999";

                        try {
                            if (phone.trim().isEmpty()){
                                Toast.makeText(getApplicationContext(),"Number not Foount",Toast.LENGTH_LONG).show();

                            }else {
                                intent.setData(Uri.parse("tel:"+phone));

                            }
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                                Toast.makeText(HomeActivity.this,"Please Grant Permission",Toast.LENGTH_LONG).show();
                                requestpermision();
                            }
                            else {
                                startActivity(intent);
                            }
                        }catch (Exception e){

                        }

                    }
                    private void requestpermision(){

                        ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();



                break;


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.shareId) {
            Intent intentshare = new Intent(Intent.ACTION_SEND);
            intentshare.setType("text/plain");
            String subject = "Blood Donor App";
            String body = "This is very simple app for search blood donner";

            intentshare.putExtra(Intent.EXTRA_SUBJECT, subject);
            intentshare.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intentshare, "Complet share"));
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.addDonnerId)
        {
            Intent intentadd=new Intent(HomeActivity.this,UserProfileActivity.class);
            startActivity(intentadd);
        }
        if (item.getItemId()==R.id.logoutId)
        {
            mAuth.signOut();
            sentUserToLoginActivity();
        }
        if (item.getItemId()==R.id.inviteFriend)
        {

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", "Hi, I've joined Blood Link 0608. \n So you download this app: bloodlink.com");
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);

        }
        if (item.getItemId()==R.id.contactId)
        {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","bloodlink0608@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));

        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser == null)
        {

            sentUserToLoginActivity();
        }
    }

    private void sentUserToLoginActivity() {

        Intent loginintent=new Intent(HomeActivity.this,MainActivity.class);
        loginintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(loginintent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
    }else {

            Toasty.error(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
//            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
//            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

}
