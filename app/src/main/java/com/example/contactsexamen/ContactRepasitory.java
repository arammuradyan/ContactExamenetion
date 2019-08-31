package com.example.contactsexamen;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactRepasitory {

    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepasitory(Application application){

        ContactDatabase contactDatabase=ContactDatabase.getInstance(application);
        contactDao=contactDatabase.contactDao();
        allContacts=contactDao.getAllContacts();

    }

    public void insert(Contact contact){
        new InsertTaskAsyncTask(contactDao).execute(contact);
    }
    public void update(Contact contact){
        new UpdateTaskAsyncTask(contactDao).execute(contact);

    }
    public void delete(Contact contact){
        new DeleteTaskAsyncTask(contactDao).execute(contact);
    }


    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Contact,Void,Void> {
        private ContactDao contactDao;

        public InsertTaskAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Contact,Void,Void> {

        private ContactDao contactDao;

        public UpdateTaskAsyncTask(ContactDao contactDao) {


                this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.update(contacts[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Contact,Void,Void> {
        private ContactDao contactDao;

        public DeleteTaskAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }

        @Override
        protected Void doInBackground(Contact... tasks) {
            contactDao.delete(tasks[0]);
            return null;
        }
    }
}
