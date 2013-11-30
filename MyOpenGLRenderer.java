package com.example.myapplication4;

import android.opengl.GLU;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by satoru on 13/11/30.
 */
public class MyOpenGLRenderer implements GLSurfaceView.Renderer{

    private FloatBuffer myVBuffer;
    private FloatBuffer myNBuffer;

    private FloatBuffer toBuffer(float[] myarray, FloatBuffer mybuffer){
        ByteBuffer vbb = ByteBuffer.allocateDirect(myarray.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mybuffer = vbb.asFloatBuffer();
        mybuffer.put(myarray);
        mybuffer.position(0);
        return mybuffer;
    }

    private void initShapes(){
        float d = 0.6f, s = 2.0f;
        float pi = 3.1415f, t;
        float[] myVertex = new float[26 * 3];
        float[] myNormal = new float[26 * 3];

        for(int i=0; i<13; i++){
            t = -i * (2 * pi/12f);

            myNormal[6*i]     = (float) Math.cos(t);
            myNormal[6*i + 1] = 0f;
            myNormal[6*i + 2] = (float) Math.sin(t);

            myNormal[6*i + 3] = (float) Math.cos(t);
            myNormal[6*i + 1] = 0f;
            myNormal[6*i + 2] = (float) Math.sin(t);

            myVertex[6*i]     = d*(float) Math.cos(t);
            myVertex[6*i + 1] = s;
            myVertex[6*i + 2] = d*(float) Math.sin(t);

            myVertex[6*i + 3] = d*(float) Math.cos(t);
            myVertex[6*i + 4] = -s;
            myVertex[6*i + 5] = d*(float) Math.sin(t);
        }
        myVBuffer = toBuffer(myVertex, myVBuffer);
        myNBuffer = toBuffer(myNormal, myNBuffer);
    }
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        initShapes();
        float lightPosition[] = { 100.0f, 100.0f, 100.0f, 1.0f};

        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPosition, 0);
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);
        gl.glEnable(GL10.GL_DEPTH_TEST);
    }

    public void onDrawFrame(GL10 gl){
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 40f,15f, 20f, 0f,0f,0f, 0f,1f,0f);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        //gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glNormalPointer(GL10.GL_FLOAT, 0, myNBuffer);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, myVBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 26);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height){
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 10f, (float) width / (float) height, 1f, 100f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }
}