package bloodlink.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DonnerAccpetFormActivity extends AppCompatActivity {


    private EditText donnerconfiremname,donnerconfirmphone;
    private Button confirembtn;

    private DatabaseReference database;
    private String push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner_accpet_form);


        donnerconfiremname=findViewById(R.id.donnerconfiremnameId);
        donnerconfirmphone=findViewById(R.id.donnerconfirmphoneId);

        confirembtn=findViewById(R.id.confirembtnId);


        Intent intent = getIntent();
        donnerconfiremname.setText("");
        donnerconfirmphone.setText("");

        push = intent.getStringExtra("userid");

        database = FirebaseDatabase.getInstance().getReference().child("DATA2").child(push);



        confirembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap hashMap = new HashMap();
                hashMap.put("donnername",donnerconfiremname.getText().toString());
                hashMap.put("donnerphone",donnerconfirmphone.getText().toString());


                database.updateChildren(hashMap);
                Toast.makeText(DonnerAccpetFormActivity.this,"Save",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DonnerAccpetFormActivity.this,ShowPostActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
