package id.ceolabs.superizqi.googlemapsgoogleplaces;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServicesOK()){
            init();
        }
    }

    private void init(){
        Button btnMap = (Button)findViewById(R.id.btn_maps);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MapActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean isServicesOK(){
    Log.d(TAG,"isServiesOk: check google services version");

    int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

    if (available == ConnectionResult.SUCCESS){
//        everything is fine
        Log.d(TAG,"isServicesOK:Google Play Services is working");
        return true;
    }
    else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
//        an error occured
        Log.d(TAG,"isServicesOK: an error occured but we can fix it");
        Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
        dialog.show();
    }else {
        Toast.makeText(this,"You can't make request",Toast.LENGTH_SHORT).show();
    }
//    kenapa return false
    return false;
    }
}
