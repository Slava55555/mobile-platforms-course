package com.example.listviewpracticum;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private List<Contact> contacts;
    private int selectedPosition = -1;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.contacts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
            holder = new ViewHolder();
            holder.avatarImageView = convertView.findViewById(R.id.avatarImageView);
            holder.nameTextView = convertView.findViewById(R.id.nameTextView);
            holder.phoneTextView = convertView.findViewById(R.id.phoneTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = contacts.get(position);

        holder.nameTextView.setText(contact.name());
        holder.phoneTextView.setText(contact.phoneNumber());

        switch (contact.avatarType()) {
            case HERO:
                holder.avatarImageView.setImageResource(R.drawable.avatar_hero);
                break;
            case PRINCESS:
                holder.avatarImageView.setImageResource(R.drawable.avatar_princess);
                break;
            case ROBOT:
                holder.avatarImageView.setImageResource(R.drawable.avatar_robot);
                break;
            case ALIEN:
                holder.avatarImageView.setImageResource(R.drawable.avatar_alien);
                break;
        }

        if (position == selectedPosition) {
            convertView.setBackgroundColor(Color.parseColor("#E3F2FD"));
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView avatarImageView;
        TextView nameTextView;
        TextView phoneTextView;
    }
}