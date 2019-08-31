package com.example.contactsexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {
private EditText name_et;
private EditText adress_et;
private EditText number_et;
private ImageView preview_img;
private Button save_btn;
private  String imgUri="";

private Uri photoURI;

private final int IMAGE_URI_REQUEST_CODE=23;
private final int  STORAGE_READ_REQUEST_CODE=456;


private ContactViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        name_et=findViewById(R.id.name_et);
        adress_et=findViewById(R.id.adress_et);
        number_et=findViewById(R.id.number_et);
        preview_img=findViewById(R.id.preview_img);
        save_btn=findViewById(R.id.save_btn);

        viewModel= ViewModelProviders.of(this).get(ContactViewModel.class);


        preview_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PreviewActivity.this,"click on image",Toast.LENGTH_SHORT).show();

                if( ActivityCompat.checkSelfPermission(PreviewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.
                        PERMISSION_GRANTED ){
                    ActivityCompat.requestPermissions(PreviewActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            STORAGE_READ_REQUEST_CODE);

                }else{
                    Intent uriIntent=new Intent(Intent.ACTION_PICK);
                    uriIntent.setType("image/*");
                    photoURI=null;
                    startActivityForResult(uriIntent,IMAGE_URI_REQUEST_CODE);
                }

            }
        });


        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact=new Contact(name_et.getText().toString(),adress_et.getText().toString(),number_et.getText().toString(),photoURI.toString());
                viewModel.insert(contact);
                onBackPressed();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_URI_REQUEST_CODE && resultCode == RESULT_OK) {
            if(photoURI == null){
                photoURI=data.getData();
            }
            preview_img.setImageURI(photoURI);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==STORAGE_READ_REQUEST_CODE){
            for (int i = 0; i <permissions.length ; i++) {
                if ( permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    Intent uriIntent=new Intent(Intent.ACTION_PICK);
                    uriIntent.setType("image/*");
                    photoURI=null;
                    startActivityForResult(uriIntent,IMAGE_URI_REQUEST_CODE);                }
            }
        }
    }
}
