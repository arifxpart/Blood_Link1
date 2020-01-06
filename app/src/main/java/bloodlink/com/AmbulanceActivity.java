package bloodlink.com;

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

public class AmbulanceActivity extends AppCompatActivity {

    private ListView ambulancelistview;

    private AlertDialog.Builder alertDialogBuilder;
    ArrayAdapter<String> adapter;


    private AdView mAdView;
    private AdView mAdView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        mAdView2 = findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2.loadAd(adRequest);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });





        setTitle("Ambulance List");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ambulancelistview=findViewById(R.id.ambulancelistviewId);

        String[] ambulanclist=getResources().getStringArray(R.array.ambulance);


        adapter=new ArrayAdapter<String>(AmbulanceActivity.this,R.layout.ambulance_layout,R.id.ambulanceviewId,ambulanclist);

        ambulancelistview.setAdapter(adapter);

       ambulancelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


               alertDialogBuilder=new AlertDialog.Builder(AmbulanceActivity.this);
               alertDialogBuilder.setTitle("CALL");
               alertDialogBuilder.setMessage(R.string.call);
               alertDialogBuilder.setIcon(R.drawable.callbutton);

               alertDialogBuilder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });

               alertDialogBuilder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       if (position==0)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01765436700";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                       if (position==1)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01941354079";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                       if (position==2)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01998556514";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                       if (position==3)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01761853924";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       } if (position==4)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01671059513";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                        if (position==5)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01937697201";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                        if (position==6)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01721520585";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                        if (position==7)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01715636615";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       } if (position==8)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01716159228";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }if (position==9)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01911506698";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }if (position==10)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01761859324";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }
                        if (position==11)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01786003430";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       } if (position==12)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01877744622";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }

                        if (position==13)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01819380000";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       } if (position==14)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "031-650000";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }if (position==15)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "031-619584";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
                                   requestpermision();
                               } else {
                                   startActivity(intent);
                               }
                           } catch (Exception e) {

                           }
                       }if (position==16)
                       {
                           Intent intent = new Intent(Intent.ACTION_DIAL);

                           String phone = "01716074497";

                           try {
                               if (phone.trim().isEmpty()) {
                                   Toast.makeText(getApplicationContext(), "Number not Foount", Toast.LENGTH_LONG).show();

                               } else {
                                   intent.setData(Uri.parse("tel:" + phone));

                               }
                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                   Toast.makeText(AmbulanceActivity.this, "Please Grant Permission", Toast.LENGTH_LONG).show();
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
           }private void requestpermision(){

               ActivityCompat.requestPermissions(AmbulanceActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

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
    }@Override
    public boolean onOptionsItemSelected( MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
