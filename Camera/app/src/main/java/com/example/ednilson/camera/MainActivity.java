package com.example.ednilson.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = (Button)  findViewById(R.id.btn_toast);

        btnToast.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Toast Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_hello:
                Log.d("MainActivity -> ","Hello Menu");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
