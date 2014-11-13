package com.sunish.barrelrace;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;


public class MainActivity extends ActionBarActivity implements SensorEventListener {
	DrawView drawView;
	 private SensorManager mSensorManager;
	 private Sensor mAccelerometer;
	 float x1,y1,z1;
	 float ratiox;
	 float ratioy;
	 float maxWidhtAcceleromter;
	 //Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);
        setSensor();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setSensor(){
    	mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    	mAccelerometer =mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL );
    	maxWidhtAcceleromter = mAccelerometer.getMaximumRange();
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
      mSensorManager.unregisterListener(this);
      super.onPause();

    }

    @Override
    protected void onStop() {
      mSensorManager.unregisterListener(this);
      super.onStop();
    }


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		//Log.i("ACURRACY", accuracy +" ");
	}


	@Override
	public void onSensorChanged(SensorEvent mAccelerometer) {
		// TODO Auto-generated method stub
		if (mAccelerometer.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			//int surfaceOrientation = WindowManager.DefaultDisplay.Rotation;
			/*Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			//Display display = ((WindowManager) Context,getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			int rotation = display.getRotation();
			//int rotation = getWindowManager().getDefaultDisplay()
				//      .getRotation();
			//int rotation = getWindowManager().getDefaultDisplay().getRotation();
			switch (rotation) {
            case Surface.ROTATION_0:
            	Log.i("Rotation", "Rotation 0 ");
            	x1 = (int) Math.pow(mAccelerometer.values[1], 2);
            	y1 = (int) Math.pow(mAccelerometer.values[2], 2);
                break;
            case Surface.ROTATION_90:
            	Log.i("Rotation", "Rotation 90 ");
            	x1 = -(int) Math.pow(mAccelerometer.values[1], 2);
            	y1 = (int) Math.pow(mAccelerometer.values[2], 2);
                break;
            case Surface.ROTATION_180:
            	Log.i("Rotation", "Rotation 180 ");
            	x1 = -(int) Math.pow(mAccelerometer.values[1], 2);
            	y1 = -(int) Math.pow(mAccelerometer.values[2], 2);
                break;
            case Surface.ROTATION_270:
            	Log.i("Rotation", "Rotation 270 ");
            	x1 = (int) Math.pow(mAccelerometer.values[1], 2);
            	y1 = -(int) Math.pow(mAccelerometer.values[2], 2);
         }
			 //x1 = (int) Math.pow(mAccelerometer.values[1], 2); 
             //y1 = (int) Math.pow(mAccelerometer.values[2], 2);*/
			
			x1 = mAccelerometer.values[0];
	    	y1 = mAccelerometer.values[1];
	    	z1 = mAccelerometer.values[2];
	    	
	    	if(x1<0){
	    		x1 = -(int) Math.pow(mAccelerometer.values[0], 2);	
	    	}else{
	    		x1 = (int) Math.pow(mAccelerometer.values[0], 2);	
	    	}
	    	if(y1<0){
	    		y1 = -(int) Math.pow(mAccelerometer.values[1], 2);
	    	}else{
	    		y1 = (int) Math.pow(mAccelerometer.values[1], 2);
	    	}
	    	if(z1<0){
	    		z1 = -(int) Math.pow(mAccelerometer.values[2], 2);	
	    	}else{
	    		z1 = (int) Math.pow(mAccelerometer.values[2], 2);	
	    	}
	    	//Log.i("values", "x -->" + x1 + "y -->" + y1 + " z--> "+ z1);
	    	drawView.invalidate();
		}
		
	}
    class DrawView extends View {
    	Paint p = new Paint();
    	int i =0;
    	Canvas canvas;
    	static final int width = 50;
        static final int height = 50;
    	
		public DrawView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
	        Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
	        bitMap = bitMap.copy(bitMap.getConfig(), true);
	        canvas = new Canvas(bitMap);
	        canvas.drawColor(Color.BLACK);
	        p.setColor(Color.BLACK);
	        p.setStyle(Paint.Style.STROKE);
	        canvas.drawBitmap(bitMap, 0,0, p);
	        //canvas.setBounds(x1, y1, x1 + width, y1 + height);
		}
		
		public void onDraw(Canvas canvas) {
			p.setStyle(Paint.Style.STROKE);
			int x=getWidth();
			int y=getHeight();
			//Log.i("width", "x1 " + x + "y1" + (float)y);
			ratiox = (x)/(2*maxWidhtAcceleromter);
			ratiox = ratiox/2;
			float yy = (float) ((2*y/3) - (0.025 * y));
			ratioy = yy/(maxWidhtAcceleromter);
	    	Log.i("YY", "YY -->" + yy + "Max Acc" + maxWidhtAcceleromter + "Ratio" + ratioy);
			//ratioy = (ratioy/3);
	    	//Log.i("Ratio", "Ratio -->" + ratio + "Max Acc" + maxWidhtAcceleromter + "Max Width" + x);
	        p.setStrokeWidth(20);
	        canvas.drawRect(0, 1*y/3, x, (float) (y-0.1*y) , p);
	        p.setStrokeWidth(5);
	        canvas.drawCircle((float)x/4, (float) 0.51 * y , (float) 0.025 * y, p);
	        canvas.drawCircle((float)3*x/4, (float) 0.51 * y , (float) 0.025 * y, p);
	        canvas.drawCircle((float)x/2, (float) 0.75 * y , (float) 0.025 * y, p);
	        if((int)(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	        	canvas.drawCircle( (float)0.025 * y , (float) ((0.95 * y)+(y1*ratioy)) , (float) 0.025 * y, p);
	        }else  if((int)(-1)*(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	       		canvas.drawCircle( (float)(getWidth()-(0.025 * y)) , (float) ((0.95 * y)+(y1*ratioy)) , (float) 0.025 * y, p);
	        }else if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  > (getHeight())){
		        canvas.drawCircle((float) (((x/2)-(x1*ratiox))), (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
	        }else{
		        canvas.drawCircle((float)(((x/2)-(x1*ratiox))), (float) (((0.95 * y)+(y1*ratioy))) , (float) 0.025 * y, p);
	        }
	        //canvas.drawCircle((float)(((x/2)-(x1*ratio))), (float) (((0.95 * y)+y1)) , (float) 0.025 * y, p);
	    	//Log.i("values", "x -->" + x1 + "y -->" + y1 + " z--> "+ z1);
	    }	
    }
}
