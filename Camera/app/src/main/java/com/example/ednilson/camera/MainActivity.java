package com.example.ednilson.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
                LayoutInflater inflater = getLayoutInflater();

                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast));

                TextView textToast = (TextView) layout.findViewById(R.id.text_toast);

                textToast.setText("button clicked with custom template");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
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
