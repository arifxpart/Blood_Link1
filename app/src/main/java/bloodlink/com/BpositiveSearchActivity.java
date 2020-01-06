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

public class BpositiveSearchActivity extends AppCompatActivity {


    private androidx.recyclerview.widget.RecyclerView RecyclerView;

    private AutoCompleteTextView editText;

    List<DataBpositive> dataBpositives ;
    BpositiveSearchActivity.Adapter myAdapter;
    DatabaseReference databaseReference;

    private AdView mAdView;
    private AdView mAdView2;

    String []bloodgroups = new String[]{"Barguna","Barisal","Bhola","Jhalokati","Patuakhali","Pirojpur","Bandarban","Brahmanbaria","Chandpur",
            "Chittagong","Comilla","Cox's Bazar","Feni","Khagrachhari","Lakshmipur","Noakhali","Rangamati","Dhaka","Faridpur","Gazipur",
            "Gopalganj","Jamalpur","Kishoreganj","Madaripur","Manikganj","Munshiganj","Mymensingh","Narayanganj","Narsingdi",
            "Netrakona","Rajbari","Shariatpur","Sherpur","Tangail","Bagerhat","Chuadanga","Jessore","Jhenaidah","Khulna",
            "Kushtia","Magura","Meherpur","Narail","Satkhira","Bogra","Joypurhat","Naogaon","Natore","Nawabganj","Pabna","Rajshahi",
            "Sirajganj","Dinajpur","Gaibandha","Kurigram","Lalmonirhat","Nilphamari","Panchagarh","Rangpur","Thakurgaon","Habiganj",
            "Moulvibazar","Sunamganj","Sylhet",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpositive_search);



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

        setTitle("B Positive (B+) Blood Group");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        editText = findViewById(R.id.editText);
        editText.setThreshold(1);
        editText.setAdapter(new ArrayAdapter<>(BpositiveSearchActivity.this,android.R.layout.simple_list_item_1,bloodgroups));

        RecyclerView = (androidx.recyclerview.widget.RecyclerView)findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //  linearLayoutManager.setStackFromEnd(true);

        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(linearLayoutManager);

        dataBpositives= new ArrayList<>();

        final BpositiveSearchActivity.Adapter myAdepter= new BpositiveSearchActivity.Adapter(this,dataBpositives);
        RecyclerView.setAdapter(myAdepter);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Query query = FirebaseDatabase.getInstance().getReference().child("DATABPOSITIVE").orderByChild("bpositivedistrice")
                        .startAt(s.toString())
                        .endAt(s.toString()+"uf0ff");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataBpositives.clear();
                        if (dataSnapshot.exists())
                        {
                            for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                            {
                                DataBpositive String = ItemSnapshot.getValue(DataBpositive.class);
                                dataBpositives.add(String);




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





        databaseReference = FirebaseDatabase.getInstance().getReference().child("DATABPOSITIVE");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataBpositives.clear();
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                    {
                        DataBpositive String = ItemSnapshot.getValue(DataBpositive.class);
                        dataBpositives.add(String);




                    }
                    myAdepter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<BpositiveSearchActivity.Adapter.MyHolder>
    {

        Context context;
        List<DataBpositive>dataBpositives;

        public Adapter(Context context, List<DataBpositive> dataBpositives) {
            this.context = context;
            this.dataBpositives = dataBpositives;
        }

        @NonNull
        @Override
        public BpositiveSearchActivity.Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.data_layout,parent,false);
            BpositiveSearchActivity.Adapter.MyHolder HV = new BpositiveSearchActivity.Adapter.MyHolder(view);

            return HV;
        }

        @Override
        public int getItemCount() {
            return dataBpositives.size();
        }

        @Override
        public void onBindViewHolder(@NonNull BpositiveSearchActivity.Adapter.MyHolder holder, final int position) {

            holder.user_blood.setText(dataBpositives.get(position).getBpositivereg());
            holder.user_name.setText(dataBpositives.get(position).getBpositivename());
            holder.user_number.setText(dataBpositives.get(position).getBpositivelastdonet());
            holder.user_univeristy.setText(dataBpositives.get(position).getBpositiveuniversity());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(BpositiveSearchActivity.this,ClickActivity.class);
                    intent.putExtra("apositivereg",dataBpositives.get(position).getBpositivereg());
                    intent.putExtra("apositivename",dataBpositives.get(position).getBpositivename());
                    intent.putExtra("apositivedistrice",dataBpositives.get(position).getBpositivedistrice());
                    intent.putExtra("apositiveuniversity",dataBpositives.get(position).getBpositiveuniversity());
                    intent.putExtra("apositivephone",dataBpositives.get(position).getBpositivephone());
                    intent.putExtra("apositivevillage",dataBpositives.get(position).getBpositivevillage());

                    intent.putExtra("apositiveuserId",dataBpositives.get(position).getBpositiveuserId());
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
