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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class BloodBankActivity extends AppCompatActivity {

    private ListView listView;
    private AdView mAdView;
    private AdView mAdView2;

    private AlertDialog.Builder alertDialogBuilder;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);



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

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView2.loadAd(adRequest);

        setTitle("Blood Bank List");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        listView=findViewById(R.id.listviewId);




         String[] Bloodbanklist=getResources().getStringArray(R.array.bloodbanks);


        adapter=new ArrayAdapter<String>(BloodBankActivity.this,R.layout.bloodbanks_layout,R.id.bloodbanklistId,Bloodbanklist);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                alertDialogBuilder=new AlertDialog.Builder(BloodBankActivity.this);
                alertDialogBuilder.setTitle("CALL");
                alertDialogBuilder.setMessage(R.string.call);
                alertDialogBuilder.setIcon(R.drawable.callbutton);

                alertDialogBuilder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialogBuilder.setPositiveButton("CALL", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (position==0) {


                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "029139940";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }

                        }
                        if (position==1){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "01718176854";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }

                        }
                        if (position==2){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "028332481";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }

                        }
                        if (position==3){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "028624249";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }
                        }
                        if (position==4){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "01534982674";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }
                        }
                        if (position==5){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "029351969";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }
                        }if (position==6){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "01709848480";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }
                        }if (position==7){

                            Intent intent = new Intent(Intent.ACTION_DIAL);

                            String phone = "0258612545";

                            try {
                                if (phone.trim().isEmpty()) {
                                    Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                                } else {
                                    intent.setData(Uri.parse("tel:" + phone));

                                }
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                    Toast.makeText(BloodBankActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                    requestpermision();
                                } else {
                                    startActivity(intent);
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();


            }
            private void requestpermision(){

                ActivityCompat.requestPermissions(BloodBankActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bloodbank_search,menu);

        MenuItem menuItem=menu.findItem(R.id.bloodbankshowId);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
