package com.sunish.barrelrace;

import java.util.ArrayList;

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
import android.os.Vibrator;
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
	float x1,y1;
	float ratiox;
	float ratioy;
	float maxWidhtAcceleromter;
	float last_x1 = 0;
	float last_y1 = 0;
	int x,y;
	Context context;
    ArrayList<Integer> xarray = new ArrayList();
    ArrayList<Integer> yarray = new ArrayList();
    int start = 0;
    int InitialSetup = 0;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
    	mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST );
    	maxWidhtAcceleromter = mAccelerometer.getMaximumRange();
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
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
			
			x1 = -mAccelerometer.values[0];
	    	y1 = mAccelerometer.values[1];
	    	
	    	/*final float alpha = 0.10f;
	    	//final float sensi = 1f/128;
	    	float a = 0,b = 0,c = 0;
	    	a = alpha * a + (1 - alpha) * mAccelerometer.values[0];
	    	b = alpha * b + (1 - alpha) * mAccelerometer.values[1];
	    	c = alpha * c + (1 - alpha) * mAccelerometer.values[2];
	    	  
	    	// Remove the gravity contribution with the high-pass filter.
	    	x1 = mAccelerometer.values[0] - a;
	    	y1 = mAccelerometer.values[1] - b;
	    	z1 = mAccelerometer.values[2] - c;*/
	    	//x1 = x1 * sensi;
	    	//y1 = y1 * sensi;
	    	//z1 = z1 * sensi;
	    	/*if(x1<0){
	    		x1 = -(int) Math.pow(mAccelerometer.values[0], 2);	
	    	}else{
	    		x1 = (int) Math.pow(mAccelerometer.values[0], 2);	
	    	}
	    	if(y1<0){
	    		y1 = -(int) Math.pow(mAccelerometer.values[1], 2);
	    	}else{
	    		y1 = (int) Math.pow(mAccelerometer.values[1], 2);
	    	}*/
    		//x1 = (int) Math.pow(mAccelerometer.values[0], 3);	
    		//y1 = (int) Math.pow(mAccelerometer.values[1], 3);
	    	//int flag = 1;
			//Log.i("values", "x -->" + x1 + "y -->" + y1 + " z--> "+ z1);
	    	/*float new_x1 = (float) (((x/2)-(x1*ratiox)));
	    	float new_y1 = (float) (((0.95 * y)+(y1*ratioy)));
			Log.i("values", "last_x -->" + last_x1 + "last_y -->" + last_y1 + " new_x1--> "+ new_x1 + "new_y1--> "+ new_y1);
	    	if(last_x1-new_x1 > 46 || new_x1-last_x1 > 46 || last_y1-new_y1 > 46 || new_y1-last_y1 > 46 ){
	    		flag  = 0;
	    	}if((last_x1-x1 < 90 || x1-last_x1 < 90 || last_y1-y1 < 90 || last_y1-y1 < 90) && flag == 0 ){
	    		drawView.invalidate();
	    		last_x1 = new_x1;
	    		last_y1 = new_y1;
	    	}*/
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
			//Log.i("Hello","InDraw");
		    Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
			p.setStyle(Paint.Style.STROKE);
	        p.setStrokeWidth(20);
	        p.setColor(Color.BLACK);
	        x=getWidth();
			y=getHeight();
			//long startTime = 0;
			if(start == 0){
		        xarray.add(x/2);
		        yarray.add((int) (0.95 * y));
		        start++;
		    	//startTime = System.nanoTime();
			}
	        canvas.drawRect(0, 1*y/3, x, (float) (y-0.1*y) , p);
	        p.setColor(Color.WHITE);
	        canvas.drawLine((float) ((x/2) - ((0.025 * y)*3)) , (float)(y-0.1*y), (float)((x/2) + (0.025 * y)*3), (float)(y-0.1*y), p);
	        p.setStrokeWidth(5);
	        p.setColor(Color.BLACK);
	        canvas.drawCircle((float)x/4, (float) 0.51 * y , (float) 0.025 * y, p);
	        p.setColor(Color.BLACK);
	        canvas.drawCircle((float)3*x/4, (float) 0.51 * y , (float) 0.025 * y, p);
	        p.setColor(Color.BLACK);
	        canvas.drawCircle((float)x/2, (float) 0.75 * y , (float) 0.025 * y, p);
	        p.setColor(Color.RED);
			//ratiox = x/(maxWidhtAcceleromter);
			//ratioy = y/(maxWidhtAcceleromter);
			int newx1 = (int) (((Integer)xarray.get(xarray.size()-1) + x1));
			int newy1 = (int) (((Integer)yarray.get(xarray.size()-1) + y1));
			//Log.i("XY","X----> " + newx1+ "Y---> " + newy1);
			if(InitialSetup == 0){
				if(newx1 - (0.025 * y)  > ((x/2) - ((0.025 * y)*3)) && newx1 + (0.025 * y)< ((x/2) + ((0.025 * y)*3)) && newy1 < ((y-0.1*y) + (0.025 * y)) ){
					xarray.add(newx1);
					yarray.add(newy1);
					InitialSetup = 1;
				}
				if(newy1 + (0.025 * y)  > (getHeight()) && newx1 < 0 + 0.025 * y){
			        canvas.drawCircle((float)0.025 * y, (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1 + (0.025 * y)  > (getHeight()) && (newx1+0.025 * y > (getWidth()))){
		        	canvas.drawCircle((float)(getWidth()-(0.025 * y)) , (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1  < ((y-0.1*y) + (0.025 * y)) && newx1 < 0 + 0.025 * y){
		        	canvas.drawCircle((float)0.025 * y, (float) ((y-0.1*y) + (0.025 * y)) , (float) 0.025 * y, p);
		        }else if(newy1  < ((y-0.1*y) + (0.025 * y)) && newx1+0.025 * y > (getWidth())){
		        	canvas.drawCircle((float)(getWidth()-(0.025 * y)), (float) ((y-0.1*y) + (0.025 * y)) , (float) 0.025 * y, p);
		        }else if( newx1 < 0 + 0.025 * y){
		        	canvas.drawCircle( (float)0.025 * y , (float) newy1 , (float) 0.025 * y, p);
		        }else if(newx1+0.025 * y > (getWidth())){
		       		canvas.drawCircle( (float)(getWidth()-(0.025 * y)) , (float) newy1 , (float) 0.025 * y, p);
		        }else if(newy1 + (0.025 * y)  > (getHeight())){
			        canvas.drawCircle((float) newx1, (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1  < ((y-0.1*y) + (0.025 * y))){
			        canvas.drawCircle((float) newx1, (float) ((y-0.1*y) + (0.025 * y)) , (float) 0.025 * y, p);
		        }else{
			        canvas.drawCircle((float)newx1, (float) newy1 , (float) 0.025 * y, p);
					xarray.add(newx1);
					yarray.add(newy1);
			    }		
			}else{
				if(newx1  > ((x/2) - ((0.025 * y)*3)) && newx1 + (0.025 * y)  < ((x/2) + ((0.025 * y)*3)) && newy1 > ((y-0.1*y) + (0.025 * y)) ){
					InitialSetup = 0;
				}
				if(newy1 + (0.025 * y)  > (getHeight()) && newx1 < 0 + 0.025 * y){
			        canvas.drawCircle((float)0.025 * y, (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1 + (0.025 * y)  > (getHeight()) && (newx1+0.025 * y > (getWidth()))){
		        	canvas.drawCircle((float)(getWidth()-(0.025 * y)) , (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1  < (1*getHeight()/3 + (0.025 * y)) && newx1 < 0 + 0.025 * y){
		        	canvas.drawCircle((float)0.025 * y, (float) ((getHeight()/3)+(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1  < (1*getHeight()/3 + (0.025 * y)) && newx1+0.025 * y > (getWidth())){
		        	canvas.drawCircle((float)(getWidth()-(0.025 * y)), (float) ((getHeight()/3)+(0.025*y)) , (float) 0.025 * y, p);
		        }else if( newx1 < 0 + 0.025 * y){
		        	canvas.drawCircle( (float)0.025 * y , (float) newy1 , (float) 0.025 * y, p);
		        }else if(newx1+0.025 * y > (getWidth())){
		       		canvas.drawCircle( (float)(getWidth()-(0.025 * y)) , (float) newy1 , (float) 0.025 * y, p);
		        }else if((newx1 < ((x/2) - ((0.025 * y)*3)) || newx1 > ((x/2) + ((0.025 * y)*3))) && newy1 + (0.025 * y)  > ((y-0.1*y))){
					//Log.i("XY1","X----> " + newx1+ "Y---> " + newy1);
			        canvas.drawCircle((float) newx1, (float) ((y-0.1*y)-(0.025*y)) , (float) 0.025 * y, p);
		        }else if(newy1  < (1*getHeight()/3 + (0.025 * y))){
			        canvas.drawCircle((float) newx1, (float) ((getHeight()/3)+(0.025*y)) , (float) 0.025 * y, p);
		        }else{
					//Log.i("XY2","X----> " + newx1+ "Y---> " + newy1);
			        canvas.drawCircle((float)newx1, (float) newy1 , (float) 0.025 * y, p);
					xarray.add(newx1);
					yarray.add(newy1);
			    }	
			}
			
			float dist1 = (float) Math.sqrt((newx1 - x/4)*(newx1 - x/4) + (newy1 - 0.51*y)*(newy1 - 0.51*y));
			float dist2 = (float) Math.sqrt((newx1 - 3*x/4)*(newx1 - 3*x/4) + (newy1 - 0.51*y)*(newy1 - 0.51*y));
			float dist3 = (float) Math.sqrt((newx1 - x/2)*(newx1 - x/2) + (newy1 - 0.75*y)*(newy1 - 0.75*y));
			
			if(dist1 < (0.025 * y) + (0.025 * y)|| dist2 < (0.025 * y) + (0.025 * y)|| dist3 < (0.025 * y) + (0.025 * y)){
				/*long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				duration = duration/ (1000*60) % 60;
				String Time = Long.toString(duration);
				Log.i("Time", Time);*/
	        	v.vibrate(500);
			}
			
	        /*if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  > (getHeight()) && (int)(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
		        canvas.drawCircle((float)0.025 * y, (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
			   	 // Vibrate for 500 milliseconds
			   	 //v.vibrate(500);
	        }else if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  > (getHeight()) && (int)(-1)*(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	        	canvas.drawCircle((float)(getWidth()-(0.025 * y)) , (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
	        	//v.vibrate(500);
	        }else if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  < (1*getHeight()/3) && (int)(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	        	canvas.drawCircle((float)0.025 * y, (float) ((getHeight()/3)+(0.025*y)) , (float) 0.025 * y, p);
	        	//v.vibrate(500);
	        }else if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  < (1*getHeight()/3) && (int)(-1)*(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	        	canvas.drawCircle((float)(getWidth()-(0.025 * y)), (float) ((getHeight()/3)+(0.025*y)) , (float) 0.025 * y, p);
	        	//v.vibrate(500);
	        }else if((int)(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	        	canvas.drawCircle( (float)0.025 * y , (float) ((0.95 * y)+(y1*ratioy)) , (float) 0.025 * y, p);
	        	//v.vibrate(500);
	        }else  if((int)(-1)*(x1*ratiox)+0.025 * y > (int)(getWidth()/2)){
	       		canvas.drawCircle( (float)(getWidth()-(0.025 * y)) , (float) ((0.95 * y)+(y1*ratioy)) , (float) 0.025 * y, p);
	       		//v.vibrate(500);
	        }else if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  > (getHeight())){
		        canvas.drawCircle((float) (((x/2)-(x1*ratiox))), (float) (getHeight()-(0.025*y)) , (float) 0.025 * y, p);
		        //v.vibrate(500);
	        }else if(((0.95 * y)+(y1*ratioy) + (0.025 * y))  < (1*getHeight()/3)){
		        canvas.drawCircle((float) (((x/2)-(x1*ratiox))), (float) ((getHeight()/3)+(0.025*y)) , (float) 0.025 * y, p);
		        //v.vibrate(500);
	        }else{
		        canvas.drawCircle((int)(((x/2)-(x1*ratiox))), (int) (((0.95 * y)+(y1*ratioy))) , (float) 0.025 * y, p);
		        //last_x1= (float) (((x/2)-(x1*ratiox)));
	        	//last_y1 = (float) (((0.95 * y)+(y1*ratioy)));
	        }*/
	    }	
    }
}
