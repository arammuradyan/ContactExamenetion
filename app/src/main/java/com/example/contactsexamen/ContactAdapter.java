package com.example.contactsexamen;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contacts=new ArrayList<>();
    private onRecyclerItemButtonsClickListener onButtonClick;

    public ContactAdapter(onRecyclerItemButtonsClickListener onButtonClick) {
        this.onButtonClick=onButtonClick;
    }
public void setContactList(List<Contact> contactsList){
        contacts=contactsList;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);

        return new ContactViewHolder(view,onButtonClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
    holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }



    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        private TextView rv_name_tv;
        private TextView rv_adress_tv;
        private TextView rv_number_tv;
        private ImageView rv_image_img;
        private Button rv_call_btn;
        private Button rv_delete_btn;
        private onRecyclerItemButtonsClickListener onButtonClick;

        public ContactViewHolder(@NonNull View itemView, final onRecyclerItemButtonsClickListener onButtonClick) {
            super(itemView);
            this.onButtonClick=onButtonClick;
            rv_name_tv=itemView.findViewById(R.id.rv_name_tv);
             rv_adress_tv=itemView.findViewById(R.id.rv_adress_tv);
             rv_number_tv=itemView.findViewById(R.id.rv_number_tv);
             rv_image_img=itemView.findViewById(R.id.rv_img);
             rv_call_btn=itemView.findViewById(R.id.call_btn);
             rv_delete_btn=itemView.findViewById(R.id.delete_btn);



        }

        public void bind(final Contact contact){

            rv_delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick.onButtonClick(view.getId(),contact);
                }
            });
            rv_call_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick.onButtonClick(view.getId(),contact);
                }
            });
            rv_adress_tv.setText(contact.getAdress());
            rv_name_tv.setText(contact.getName());
            rv_number_tv.setText(contact.getNumber());
            rv_image_img.setImageURI(Uri.parse(contact.getImage()));
           // rv_image_img.setImageURI(Uri.EMPTY);

        }
    }

    public interface onRecyclerItemButtonsClickListener{
       void onButtonClick(int buttonId,Contact contact);
    }
}
