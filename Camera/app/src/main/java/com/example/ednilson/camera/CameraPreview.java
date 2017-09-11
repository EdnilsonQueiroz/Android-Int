package com.example.ednilson.camera;

import android.content.Context;
import android.graphics.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ednilson on 11/09/17.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Camera camera;

    public CameraPreview (Context context, Camera camera){
        super(context);
        this.camera = camera;
        this.holder = getHolder();
        this.holder.addCallback(this);
        this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreate (SurfaceHolder holder){
        try {
            if (camera == null){
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            }
        }catch (Exception ex){
            Log.d("Camera_preview ->", "Error Setting camera ");
        }
    }

    public void refreshCamera(Camera camera){
        if (holder.getSurface() ==   null){
            return;
        }

        Camera.stopPreview();

        setCamera(camera);

        try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
        }catch (Exception ex){
            Log.d("Camera_preview ->", "Error Setting camera ");
        }

    }

    public void setCamera(Camera camera){
        camera = camera;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w , int h){
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.release();

    }

}
