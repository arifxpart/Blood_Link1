package bloodlink.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SearchItemActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView apositivesearch,anegativecard,bpositivecard,bnegativecard,abpositivecard,abnegativecard,opositivecard,onegativecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        setTitle("Blood Group Search");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        apositivesearch=findViewById(R.id.apositivesearchId);
        anegativecard=findViewById(R.id.anegativecardId);
        bpositivecard=findViewById(R.id.bpositivecardId);
        bnegativecard=findViewById(R.id.bnegativecardId);
        abpositivecard=findViewById(R.id.abpositivecardId);
        abnegativecard=findViewById(R.id.abnegativecardId);
        opositivecard=findViewById(R.id.opositivecardId);
        onegativecard=findViewById(R.id.onegativecardId);

        apositivesearch.setOnClickListener(this);
        anegativecard.setOnClickListener(this);
        bpositivecard.setOnClickListener(this);
        bnegativecard.setOnClickListener(this);
        abpositivecard.setOnClickListener(this);
        abnegativecard.setOnClickListener(this);
        opositivecard.setOnClickListener(this);
        onegativecard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.apositivesearchId:
                Intent intentasearch=new Intent(getApplicationContext(),ApositiveSearchActivity.class);
                startActivity(intentasearch);
                break;


            case R.id.anegativecardId:
                Intent intentanegative=new Intent(getApplicationContext(),AnegativeSearchActivity.class);
                startActivity(intentanegative);
                break;


            case R.id.bpositivecardId:
                Intent intentbpositive=new Intent(getApplicationContext(),BpositiveSearchActivity.class);
                startActivity(intentbpositive);
                break;


            case R.id.bnegativecardId:
                Intent intentbnegative=new Intent(getApplicationContext(),BnegativeSearchActivity.class);
                startActivity(intentbnegative);
                break;


            case R.id.abpositivecardId:
                Intent intentabpositive=new Intent(getApplicationContext(),ABpositiveSearchActivity.class);
                startActivity(intentabpositive);
                break;


            case R.id.abnegativecardId:
                Intent intentabnegative=new Intent(getApplicationContext(),ABnegativeSearchActivity.class);
                startActivity(intentabnegative);
                break;


            case R.id.opositivecardId:
                Intent intentopositive=new Intent(getApplicationContext(),OpositiveSearchActivity.class);
                startActivity(intentopositive);
                break;


             case R.id.onegativecardId:
                Intent intentonegative=new Intent(getApplicationContext(),OnegativeSearchActivity.class);
                startActivity(intentonegative);
                break;
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
