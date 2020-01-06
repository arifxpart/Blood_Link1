package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ABnegativeSearchActivity extends AppCompatActivity {

    private AdView mAdView;
    private AdView mAdView2;

    private androidx.recyclerview.widget.RecyclerView RecyclerView;

    private AutoCompleteTextView editText;

    List<DataABnegative> dataABnegatives;
    ABnegativeSearchActivity.Adapter myAdapter;
    DatabaseReference databaseReference;

    String[] districts = new String[]{"Barguna", "Barisal", "Bhola", "Jhalokati", "Patuakhali", "Pirojpur", "Bandarban", "Brahmanbaria", "Chandpur",
            "Chittagong", "Comilla", "Cox's Bazar", "Feni", "Khagrachhari", "Lakshmipur", "Noakhali", "Rangamati", "Dhaka", "Faridpur", "Gazipur",
            "Gopalganj", "Jamalpur", "Kishoreganj", "Madaripur", "Manikganj", "Munshiganj", "Mymensingh", "Narayanganj", "Narsingdi",
            "Netrakona", "Rajbari", "Shariatpur", "Sherpur", "Tangail", "Bagerhat", "Chuadanga", "Jessore", "Jhenaidah", "Khulna",
            "Kushtia", "Magura", "Meherpur", "Narail", "Satkhira", "Bogra", "Joypurhat", "Naogaon", "Natore", "Nawabganj", "Pabna", "Rajshahi",
            "Sirajganj", "Dinajpur", "Gaibandha", "Kurigram", "Lalmonirhat", "Nilphamari", "Panchagarh", "Rangpur", "Thakurgaon", "Habiganj",
            "Moulvibazar", "Sunamganj", "Sylhet",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abnegative_search);


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

        setTitle("AB Negative (AB-) Blood Group");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        editText = findViewById(R.id.editText);
        editText.setThreshold(1);
        editText.setAdapter(new ArrayAdapter<>(ABnegativeSearchActivity.this,android.R.layout.simple_list_item_1,districts));

        RecyclerView = (androidx.recyclerview.widget.RecyclerView)findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //  linearLayoutManager.setStackFromEnd(true);

        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(linearLayoutManager);

        dataABnegatives= new ArrayList<>();

        final ABnegativeSearchActivity.Adapter myAdepter= new ABnegativeSearchActivity.Adapter(this,dataABnegatives);
        RecyclerView.setAdapter(myAdepter);


        try {

    editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Query query = FirebaseDatabase.getInstance().getReference().child("DATAABNEGATIVE").orderByChild("abnegativedistrice")
                    .startAt(s.toString())
                    .endAt(s.toString()+"\uf8ff");
            query.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    dataABnegatives.clear();
                    if (dataSnapshot.exists())
                    {
                        for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                        {
                            DataABnegative String = ItemSnapshot.getValue(DataABnegative.class);
                            dataABnegatives.add(String);


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


            }catch (Exception e)
        {
                Toast.makeText(getApplicationContext(),"Data not found",Toast.LENGTH_LONG).show();
        }



                try {






        databaseReference = FirebaseDatabase.getInstance().getReference().child("DATAABNEGATIVE");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataABnegatives.clear();
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ItemSnapshot : dataSnapshot.getChildren())
                    {
                        DataABnegative String = ItemSnapshot.getValue(DataABnegative.class);
                        dataABnegatives.add(String);




                    }
                    myAdepter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Data not found",Toast.LENGTH_LONG).show();
                }

    }



    public class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<ABnegativeSearchActivity.Adapter.MyHolder>
    {

        Context context;
        List<DataABnegative>dataABnegatives;

        public Adapter(Context context, List<DataABnegative> dataABnegatives) {
            this.context = context;
            this.dataABnegatives = dataABnegatives;
        }

        @NonNull
        @Override
        public ABnegativeSearchActivity.Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.data_layout,parent,false);
            ABnegativeSearchActivity.Adapter.MyHolder HV = new ABnegativeSearchActivity.Adapter.MyHolder(view);

            return HV;
        }

        @Override
        public int getItemCount() {
            return dataABnegatives.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ABnegativeSearchActivity.Adapter.MyHolder holder, final int position) {

            holder.user_blood.setText(dataABnegatives.get(position).getAbnegativereg());
            holder.user_name.setText(dataABnegatives.get(position).getAbnegativeuserId());
            holder.user_number.setText(dataABnegatives.get(position).getAbnegativelastdonet());
            holder.user_univeristy.setText(dataABnegatives.get(position).getAbnegativeuniversity());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(ABnegativeSearchActivity.this,ClickActivity.class);
                    intent.putExtra("apositivereg",dataABnegatives.get(position).getAbnegativereg());
                    intent.putExtra("apositivename",dataABnegatives.get(position).getAbnegativename());
                    intent.putExtra("apositivedistrice",dataABnegatives.get(position).getAbnegativedistrice());
                    intent.putExtra("apositiveuniversity",dataABnegatives.get(position).getAbnegativeuniversity());
                    intent.putExtra("apositivephone",dataABnegatives.get(position).getAbnegativephone());
                    intent.putExtra("apositivevillage",dataABnegatives.get(position).getAbnegativevillage());

                    intent.putExtra("apositiveuserId",dataABnegatives.get(position).getAbnegativeuserId());
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