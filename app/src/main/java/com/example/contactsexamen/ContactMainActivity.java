package com.example.contactsexamen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.prefs.PreferenceChangeEvent;

public class ContactMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ContactFragment contactFragment;
    private FrameLayout contactFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_main);
        contactFragment=new ContactFragment();

        showContactFragment();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(ContactMainActivity.this,PreviewActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_2){
            {
                logOut();

            }
        }
        return true;
    }

    private void showContactFragment(){
        FragmentManager fm= getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragment_frame,contactFragment,"contFragment").commit();
    }

    private void logOut(){
       SharedPreferences sharedPreferences=getSharedPreferences("loginPref",MODE_PRIVATE);


        sharedPreferences.edit().putString("checkValidation","invalid").apply();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
