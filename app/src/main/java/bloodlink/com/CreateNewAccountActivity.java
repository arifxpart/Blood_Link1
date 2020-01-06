package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class CreateNewAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView apositivecard,anegativecard,bpositivecard,bnegativecard,opositivecard,onegativecard,abpositivecard,abnegativecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //all card find:

        apositivecard=findViewById(R.id.apositivecardId);
        anegativecard=findViewById(R.id.anegativecardId);

        bpositivecard=findViewById(R.id.bpositivecardId);
        bnegativecard=findViewById(R.id.bnegativecardId);

        opositivecard=findViewById(R.id.opositivecardId);
        onegativecard=findViewById(R.id.onegativecardId);

        abpositivecard=findViewById(R.id.abpositivecardId);
        abnegativecard=findViewById(R.id.abnegativecardId);


        //card setOnclickListener

        apositivecard.setOnClickListener(this);
        anegativecard.setOnClickListener(this);

        bpositivecard.setOnClickListener(this);
        bnegativecard.setOnClickListener(this);

        opositivecard.setOnClickListener(this);
        onegativecard.setOnClickListener(this);

        abpositivecard.setOnClickListener(this);
        abnegativecard.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apositivecardId:
                Intent intentapositive=new Intent(getApplicationContext(),RegApositiveActivity.class);
                startActivity(intentapositive);
                break;


            case R.id.anegativecardId:
                Intent intentanegative=new Intent(getApplicationContext(),RegAnegativeActivity.class);
                startActivity(intentanegative);
                break;


            case R.id.bpositivecardId:
                Intent intentbpositive=new Intent(getApplicationContext(),RegBpositiveActivity.class);
                startActivity(intentbpositive);
                break;


            case R.id.bnegativecardId:
                Intent intentbnegative=new Intent(getApplicationContext(),RegBnegativeActivity.class);
                startActivity(intentbnegative);
                break;


            case R.id.opositivecardId:
                Intent intentopositive=new Intent(getApplicationContext(),RegOpositiveActivity.class);
                startActivity(intentopositive);
                break;


            case R.id.onegativecardId:
                Intent intentonegative=new Intent(getApplicationContext(),RegOnegativeActivity.class);
                startActivity(intentonegative);
                break;


            case R.id.abpositivecardId:
                Intent intentabpositive=new Intent(getApplicationContext(),RegABpositiveActivity.class);
                startActivity(intentabpositive);
                break;


            case R.id.abnegativecardId:
                Intent intentabnegative=new Intent(getApplicationContext(),RegABnegativeActivity.class);
                startActivity(intentabnegative);
                break;
        }
    }@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
