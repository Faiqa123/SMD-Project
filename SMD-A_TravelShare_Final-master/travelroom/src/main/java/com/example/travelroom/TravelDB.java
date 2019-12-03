package com.example.travelroom;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

public class TravelDB {
    private String DB_NAME = "TravelShare_Local";
    private DatabaseClass LocalDB;

        public TravelDB(Context context) {
            getInstance(context);
        }

        private DatabaseClass getInstance(Context context){
            if (LocalDB!=null){
                return LocalDB;
            }else{
                LocalDB = Room.databaseBuilder(context, DatabaseClass.class, DB_NAME).build();
            }
            return LocalDB;
        }

    public void insertUser(String pass,String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);
        myinsertUser(user);
    }

    public void myinsertUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("Local DB", "Inserting!!!! from Background Thread: " + Thread.currentThread().getId());
                LocalDB.daoAccess().InsertUser(user);
                return null;
            }
        }.execute();
    }

    public void insertLocation(String name) {
        Location location= new Location();
        location.setName(name);
        myinsertloc(location);
    }

    public void myinsertloc(final Location location){
            new AsyncTask<Void,Void,Void>(){
                @Override
                protected Void doInBackground(Void... voids)
                {
                    Log.e("Local DB", "Inserting!!!! from Background Thread: " + Thread.currentThread().getId());
                    LocalDB.daoAccess().InsertLocation(location);
                    return null;
                }
            }.execute();
    }



    }
