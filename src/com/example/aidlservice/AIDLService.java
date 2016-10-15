package com.example.aidlservice;

import java.util.Timer;
import java.util.TimerTask;


import com.example.aidlservice.Song.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
      private String[] names=new String[]{

    		  "love the way you lie","≥…»´","∏È«≥"};
      private String[] authors=new String[]{

    		  "¿Ÿπ˛ƒ»","¡÷Â∂ºŒ","÷‹Ω‹¬◊"};
      
      private String name,author;
      private SongBinder songBinder;
      
      private Timer timer=new Timer();
      
      public class SongBinder extends Stub{


    	  public String  getName()throws RemoteException{


    		  return name;
    	  }
    	 
		@Override
		public String getAuther() throws RemoteException {


			// TODO Auto-generated method stub
			return author;
		}
      }
      
     
	@Override
	public IBinder onBind(Intent intent) 

{

		// TODO Auto-generated method stub
		return songBinder;
	}


	@Override
	public void onCreate() {

		// TODO Auto-generated method stub
		super.onCreate();
		songBinder=new SongBinder();
		timer.schedule(new TimerTask(){

			public void run(){

				int rand=(int)(Math.random()*3);
				name=names[rand];
				author=authors[rand];
			}
		},10000);
		}


	@Override
	public void onDestroy() {

		// TODO Auto-generated method stub
		super.onDestroy();
		timer.cancel();
	}
	

}
