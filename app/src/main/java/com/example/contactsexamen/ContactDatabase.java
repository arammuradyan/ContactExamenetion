package com.example.contactsexamen;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities =Contact.class, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
private static ContactDatabase instance;

public  abstract ContactDao contactDao();

public static synchronized ContactDatabase getInstance(Context context){
    if(instance==null){
        instance= Room.databaseBuilder(context.getApplicationContext(),
                ContactDatabase.class, "contact_database").
                fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
    }
    return instance;
}

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopuletTaskDatabaseAsyncTask(instance).execute();
        }
    };

    private static class PopuletTaskDatabaseAsyncTask extends AsyncTask<Void,Void,Void> {

        private ContactDao contactDao;

        public PopuletTaskDatabaseAsyncTask(ContactDatabase db) {
            contactDao = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact("Aram","leningradyan","098 96 79 80","asdhasjk"));
            contactDao.insert(new Contact("Aram","leningradyan","098 96 79 80","asdhasjk"));
            contactDao.insert(new Contact("Aram","leningradyan","098 96 79 80","asdhasjk"));
            contactDao.insert(new Contact("Aram","leningradyan","098 96 79 80","asdhasjk"));
            contactDao.insert(new Contact("Aram","leningradyan","098 96 79 80","asdhasjk"));
            contactDao.insert(new Contact("Aram","leningradyan","098 96 79 80","asdhasjk"));
            return null;
        }
    }
}
