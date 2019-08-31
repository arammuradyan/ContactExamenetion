package com.example.contactsexamen;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactFragment extends Fragment implements ContactAdapter.onRecyclerItemButtonsClickListener {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private final int CALL_PERMISION_RQUEST_CODE=89;
    private ContactViewModel viewModel;
    private String contactForPermission="";


    public ContactFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= ViewModelProviders.of(this).get(ContactViewModel.class);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fargment_contact,null);
        recyclerView=view.findViewById(R.id.content_rv);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        contactAdapter=new ContactAdapter(this);


        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
          contactAdapter.setContactList(contacts);
            }
        });
        LinearLayoutManager lm=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(contactAdapter);


    }

    @Override
    public void onButtonClick(int buttonId, Contact contact) {
        switch (buttonId){
            case R.id.delete_btn:
            {
                viewModel.delete(contact);
                break;
            }
            case R.id.call_btn:
            {
                makeCall(contact);
                break;
            }
        }

    }
    private void makeCall(Contact contact){
        contactForPermission=contact.getNumber();

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CALL_PHONE},CALL_PERMISION_RQUEST_CODE);
        }else{

            Intent intent=new Intent(Intent.ACTION_CALL);
            String uri= "tel:"+contact.getNumber();
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISION_RQUEST_CODE) {

            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.CALL_PHONE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent=new Intent(Intent.ACTION_CALL);

                    String uri= "tel:"+contactForPermission;
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
            }
        }

    }

}
