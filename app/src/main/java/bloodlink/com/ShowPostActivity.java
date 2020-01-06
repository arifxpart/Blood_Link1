package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowPostActivity extends AppCompatActivity {


    private androidx.recyclerview.widget.RecyclerView RecyclerView;



    List<DataPost> dataPosts ;
    ShowPostActivity.Adapter myAdapter;
    DatabaseReference databaseReference;

    private AdView mAdView;
    private AdView mAdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);


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

        setTitle("News Feed");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        RecyclerView = (androidx.recyclerview.widget.RecyclerView)findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //  linearLayoutManager.setStackFromEnd(true);

        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(linearLayoutManager);

        dataPosts= new ArrayList<>();

        final ShowPostActivity.Adapter myAdepter= new ShowPostActivity.Adapter(this,dataPosts);
        RecyclerView.setAdapter(myAdepter);




        databaseReference = FirebaseDatabase.getInstance().getReference().child("DATA2");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataPosts.clear();
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                    {
                        DataPost String = ItemSnapshot.getValue(DataPost.class);
                        dataPosts.add(String);




                    }
                    myAdepter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<ShowPostActivity.Adapter.MyHolder>
    {

        Context context;
        List<DataPost>dataPosts;

        public Adapter(Context context, List<DataPost> data2Lists) {
            this.context = context;
            this.dataPosts = data2Lists;
        }

        @NonNull
        @Override
        public ShowPostActivity.Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.data2_layout,parent,false);
            ShowPostActivity.Adapter.MyHolder HV = new ShowPostActivity.Adapter.MyHolder(view);

            return HV;
        }

        @Override
        public int getItemCount() {
            return dataPosts.size();
        }

        @Override
        public void onBindViewHolder(@NonNull final ShowPostActivity.Adapter.MyHolder holder, final int position) {

            holder.user_name.setText(dataPosts.get(position).getPostname());
            holder.user_number.setText(dataPosts.get(position).getPostnumber());
            holder.user_blood.setText(dataPosts.get(position).getPostbloodgroup());
            holder.user_detials.setText(dataPosts.get(position).getPostrugerproblem());

            holder.user_donaername.setText(dataPosts.get(position).getDonnername());
            holder.user_donerphone.setText(dataPosts.get(position).getDonnerphone());



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(ShowPostActivity.this,Click2Activity.class);
                    intent.putExtra("postbloodgroup",dataPosts.get(position).getPostbloodgroup());
                    intent.putExtra("postrugerproblem",dataPosts.get(position).getPostrugerproblem());
                    intent.putExtra("posthowmanybag",dataPosts.get(position).getPosthowmanybag());
                    intent.putExtra("postdata",dataPosts.get(position).getPostdata());
                    intent.putExtra("posttime",dataPosts.get(position).getPosttime());
                    intent.putExtra("posthospital",dataPosts.get(position).getPosthospital());
                    intent.putExtra("postname",dataPosts.get(position).getPostname());
                    intent.putExtra("postrelaction",dataPosts.get(position).getPostrelaction());
                    intent.putExtra("postnumber",dataPosts.get(position).getPostnumber());
                    intent.putExtra("userid",dataPosts.get(position).getPostname());
                    intent.putExtra("postdistrict",dataPosts.get(position).getPostdistrict());



                    intent.putExtra("donnerphone",dataPosts.get(position).getDonnerphone());
                    intent.putExtra("donnername",dataPosts.get(position).getDonnername());


                    startActivity(intent);

                }
            });

            holder.newaccpetbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent2 = new Intent(ShowPostActivity.this,DonnerAccpetFormActivity.class);
//                    intent.putExtra("postbloodgroup",dataPosts.get(position).getPostbloodgroup());
//                    intent.putExtra("postrugerproblem",dataPosts.get(position).getPostrugerproblem());
//                    intent.putExtra("posthowmanybag",dataPosts.get(position).getPosthowmanybag());
//                    intent.putExtra("postdata",dataPosts.get(position).getPostdata());
//                    intent.putExtra("posttime",dataPosts.get(position).getPosttime());
//                    intent.putExtra("posthospital",dataPosts.get(position).getPosthospital());
//                    intent.putExtra("postname",dataPosts.get(position).getPostname());
//                    intent.putExtra("postrelaction",dataPosts.get(position).getPostrelaction());
//                    intent.putExtra("postnumber",dataPosts.get(position).getPostnumber());
//                    intent.putExtra("userid",dataPosts.get(position).getPostname());
//                    intent.putExtra("postdistrict",dataPosts.get(position).getPostdistrict());



                    intent2.putExtra("donnerphone",dataPosts.get(position).getDonnerphone());
                    intent2.putExtra("donnername",dataPosts.get(position).getDonnername());
                    intent2.putExtra("userid",dataPosts.get(position).getPostname());


                    startActivity(intent2);

                }
            });
holder.newcallbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(Intent.ACTION_DIAL);

        String phone=holder.user_number.getText().toString();

        try {
            if (phone.trim().isEmpty()){
                Toast.makeText(getApplicationContext(),"Number not Foount",Toast.LENGTH_LONG).show();

            }else {
                intent.setData(Uri.parse("tel:"+phone));

            }
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){

                Toast.makeText(ShowPostActivity.this,"Please Grant Permission",Toast.LENGTH_LONG).show();
                requestpermision();
            }
            else {
                startActivity(intent);
            }
        }catch (Exception e){

        }
    }private void requestpermision(){

        ActivityCompat.requestPermissions(ShowPostActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
});

        }


        class MyHolder extends  androidx.recyclerview.widget.RecyclerView.ViewHolder
        {

            private TextView user_name,user_number,user_blood,user_detials,user_donaername,user_donerphone;
            private Button newaccpetbtn, newcallbtn;

            public MyHolder(@NonNull View itemView) {
                super(itemView);

                user_name = (TextView)itemView.findViewById(R.id.user_name);
                user_number = (TextView)itemView.findViewById(R.id.user_number);
                user_blood=itemView.findViewById(R.id.user_blood);

                user_detials=itemView.findViewById(R.id.user_detials);
                newaccpetbtn=itemView.findViewById(R.id.newaccpetbtnId);
                newcallbtn=itemView.findViewById(R.id.newcallbtnId);

                user_donaername=itemView.findViewById(R.id.user_donaername);
                user_donerphone=itemView.findViewById(R.id.user_donerphone);


            }
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