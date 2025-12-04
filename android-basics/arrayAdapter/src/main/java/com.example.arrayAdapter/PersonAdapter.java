package com.example.arrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    private LayoutInflater inflater;
    private int layout;
    private List<Person> persons;
    private int selectedPosition = -1;

    public PersonAdapter(Context context, int resource, List<Person> persons) {
        super(context, resource, persons);
        this.persons = persons;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = persons.get(position);
        viewHolder.nameView.setText(person.getFullName());

        if (position == selectedPosition) {
            convertView.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_blue_light));
        } else {
            convertView.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        }

        return convertView;
    }

    private class ViewHolder {
        TextView nameView;
        ViewHolder(View view) {
            nameView = view.findViewById(R.id.tvPersonName);
        }
    }
}