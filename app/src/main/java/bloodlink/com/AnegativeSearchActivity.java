package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnegativeSearchActivity extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView RecyclerView;

    private AutoCompleteTextView editText;

    List<DataAnegative> dataAnegatives ;
    AnegativeSearchActivity.Adapter myAdapter;
    DatabaseReference databaseReference;

    String []bloodgroups = new String[]{"Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur",
            "Chittagong","Comilla","Cox's Bazar","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati","Dhaka","Faridpur","Gazipur",
            "Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi",
            "Netrakona","Rajbari","Shariatpur","Sherpur","Tangail","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna",
            "Kushtia","Magura","Meherpur","Narail","Satkhira","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi",
            "Sirajganj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Habiganj",
            "Moulvibazar","Sunamganj","Sylhet",};

    private AdView mAdView;
    private AdView mAdView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anegative_search);



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

        setTitle("A Negative (A-) Blood Group");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        editText = findViewById(R.id.editText);
        editText.setThreshold(1);
        editText.setAdapter(new ArrayAdapter<>(AnegativeSearchActivity.this,android.R.layout.simple_list_item_1,bloodgroups));

        RecyclerView = (androidx.recyclerview.widget.RecyclerView)findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //  linearLayoutManager.setStackFromEnd(true);

        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(linearLayoutManager);

        dataAnegatives= new ArrayList<>();

        final AnegativeSearchActivity.Adapter myAdepter= new AnegativeSearchActivity.Adapter(this,dataAnegatives);
        RecyclerView.setAdapter(myAdepter);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Query query = FirebaseDatabase.getInstance().getReference().child("DATAANEGATIVE").orderByChild("anegativedistrice")
                        .startAt(s.toString())
                        .endAt(s.toString()+"uf0ff");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataAnegatives.clear();
                        if (dataSnapshot.exists())
                        {
                            for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                            {
                                DataAnegative String = ItemSnapshot.getValue(DataAnegative.class);
                                dataAnegatives.add(String);




                            }
                            myAdepter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        databaseReference = FirebaseDatabase.getInstance().getReference().child("DATAANEGATIVE");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataAnegatives.clear();
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                    {
                        DataAnegative String = ItemSnapshot.getValue(DataAnegative.class);
                        dataAnegatives.add(String);




                    }
                    myAdepter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<AnegativeSearchActivity.Adapter.MyHolder>
    {

        Context context;
        List<DataAnegative>dataAnegatives;

        public Adapter(Context context, List<DataAnegative> dataAnegatives) {
            this.context = context;
            this.dataAnegatives = dataAnegatives;
        }

        @NonNull
        @Override
        public AnegativeSearchActivity.Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.data_layout,parent,false);
            AnegativeSearchActivity.Adapter.MyHolder HV = new AnegativeSearchActivity.Adapter.MyHolder(view);

            return HV;
        }

        @Override
        public int getItemCount() {
            return dataAnegatives.size();
        }

        @Override
        public void onBindViewHolder(@NonNull AnegativeSearchActivity.Adapter.MyHolder holder, final int position) {

            holder.user_blood.setText(dataAnegatives.get(position).getAnegativereg());
            holder.user_name.setText(dataAnegatives.get(position).getAnegativeuserId());
            holder.user_number.setText(dataAnegatives.get(position).getAnegativelastdonet());
            holder.user_univeristy.setText(dataAnegatives.get(position).getAnegativeuniversity());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(AnegativeSearchActivity.this,ClickActivity.class);
                    intent.putExtra("apositivereg",dataAnegatives.get(position).getAnegativereg());
                    intent.putExtra("apositivename",dataAnegatives.get(position).getAnegativename());
                    intent.putExtra("apositivedistrice",dataAnegatives.get(position).getAnegativedistrice());
                    intent.putExtra("apositiveuniversity",dataAnegatives.get(position).getAnegativeuniversity());
                    intent.putExtra("apositivephone",dataAnegatives.get(position).getAnegativephone());
                    intent.putExtra("apositivevillage",dataAnegatives.get(position).getAnegativevillage());

                    intent.putExtra("apositiveuserId",dataAnegatives.get(position).getAnegativeuserId());
                    startActivity(intent);


                }

            });
        }


        class MyHolder extends  androidx.recyclerview.widget.RecyclerView.ViewHolder
        {

            private TextView user_name,user_number,user_blood,user_univeristy;

            public MyHolder(@NonNull View itemView) {
                super(itemView);

                user_name = (TextView)itemView.findViewById(R.id.user_name);
                user_number = (TextView)itemView.findViewById(R.id.user_number);
                user_blood=itemView.findViewById(R.id.user_blood);
                user_univeristy=itemView.findViewById(R.id.user_univeristy);


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