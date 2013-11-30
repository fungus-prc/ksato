package com.example.myapplication4;

import android.content.Context;
import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import javax.microedition.khronos.opengles.GL10;


public class MainActivity extends ActionBarActivity {

    /** Called when the activity is first created. */

    private GLSurfaceView myGLView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGLView = new MyOpenGLSurfaceView(this);
        setContentView(myGLView);
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        myGLView.onResume();
    }
}

class MyOpenGLSurfaceView extends GLSurfaceView {
    public MyOpenGLSurfaceView(Context context){
        super(context);
        setRenderer(new MyOpenGLRenderer());
    }
}
