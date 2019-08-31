package com.example.contactsexamen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepasitory contactRepasitory;
    private LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
contactRepasitory=new ContactRepasitory(application);
allContacts=contactRepasitory.getAllContacts();
    }

public void insert(Contact contact){
        contactRepasitory.insert(contact);
}
    public void update(Contact contact){
        contactRepasitory.update(contact);
    }
    public void delete(Contact contact){
        contactRepasitory.delete(contact);
    }

    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }
}
