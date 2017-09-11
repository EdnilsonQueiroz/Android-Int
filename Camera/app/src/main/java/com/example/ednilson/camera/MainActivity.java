package com.example.ednilson.camera;

import android.content.Context;
import android.graphics.Camera;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.graphics.Camera.*;

public class MainActivity extends AppCompatActivity {

    private android.hardware.Camera camera;
    private android.hardware.Camera.PictureCallback mPicture;
    private Button btnCapture;
    private Context context;
    private LinearLayout camera_preview;
    private CameraPreview preview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = this;
        init();
    }
    public void init(){
        camera = android.hardware.Camera.open(openBackCamera());
        mPicture = getPictureCallback();
    }

    private int openBackCamera(){
        int numberOfCameras = android.hardware.Camera.getNumberOfCameras();
         int cameraId = -1;

        for (int i = 0; i< numberOfCameras;i++){
            android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();

            android.hardware.Camera.getCameraInfo(i, info);

            if (info.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK){
                cameraId = i;
            }

        }
        return cameraId;
    }

    private android.hardware.Camera.PictureCallback getPictureCallback () {
        android.hardware.Camera.PictureCallback picture = new android.hardware.Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
                File pictureFile = getOutMediaFile();

                if (pictureFile == null) {
                    return;
                }

                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);

                    fos.write(data);
                    fos.close();

                    Toast toast = Toast.makeText(context, "Picture Saved", Toast.LENGTH_LONG);
                    toast.show();

                } catch (FileNotFoundException ex) {

                } catch (IOException ex1) {


                }

                preview.refreshCamera(camera);

            }
        };


        return picture;

    }





    private File getOutMediaFile(){
        File mediaStorageDir = new File("/sdcard/","Camera");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return  null;
            }
        }
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediFile = new File ((mediaStorageDir.getPath() + File.separator + "img" + time + ".jpg"));

        return mediFile;
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
