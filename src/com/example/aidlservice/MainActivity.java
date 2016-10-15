package com.example.aidlservice;

import android.os.Bundle;

import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private Button getData;
    private EditText name,author;
    private Song songBinder;
    private static final String TAG="ADILService";
	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name=(EditText)findViewById(R.id.name);
		author=(EditText)findViewById(R.id.author);
		getData=(Button)findViewById(R.id.getData);
		
		final Intent intent=new Intent();
		intent.setAction(TAG);
		bindService(intent,conn,Service.BIND_AUTO_CREATE);  //绑定Service
		getData.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				try{
					name.setText(songBinder.getName());
					author.setText(songBinder.getAuther());
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
	}
     private ServiceConnection conn=new ServiceConnection(){  //创建ServiceConnection对象

    	 @Override
 		public void onServiceDisconnected(ComponentName name) {

 			// TODO Auto-generated method stub
 			 songBinder=null;
 		}
    	 public void onServiceConnected(ComponentName name,IBinder service){

    		 songBinder=Song.Stub.asInterface(service);   //将代理类转化成IBinder对象
    	 }
		
     };
     protected void onDestory(){

    	 super.onDestroy();
    	 unbindService(conn);
     }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {


		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
