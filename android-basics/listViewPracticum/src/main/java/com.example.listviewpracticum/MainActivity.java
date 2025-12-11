package com.example.listviewpracticum;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContactAdapter adapter;
    private List<Contact> contacts = new ArrayList<>();
    private Button btnSortName, btnSortPhone, btnSortAvatar, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadContactsFromJson();
        setupListView();
        setupButtons();
    }

    private void initializeViews() {
        listView = findViewById(R.id.listView);
        btnSortName = findViewById(R.id.btnSortName);
        btnSortPhone = findViewById(R.id.btnSortPhone);
        btnSortAvatar = findViewById(R.id.btnSortAvatar);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void loadContactsFromJson() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("contacts.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String json = stringBuilder.toString();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            contacts = gson.fromJson(json, listType);

        } catch (IOException e) {
            e.printStackTrace();
            contacts = createSampleContacts();
        }
    }

    private List<Contact> createSampleContacts() {
        List<Contact> sampleContacts = new ArrayList<>();
        sampleContacts.add(new Contact("Алексей Иванов", "+7 (912) 345-67-89", Contact.AvatarType.HERO));
        sampleContacts.add(new Contact("Мария Петрова", "+7 (923) 456-78-90", Contact.AvatarType.PRINCESS));
        sampleContacts.add(new Contact("Дмитрий Смирнов", "+7 (956) 789-01-23", Contact.AvatarType.ROBOT));
        sampleContacts.add(new Contact("Анна Волкова", "+7 (967) 890-12-34", Contact.AvatarType.ALIEN));
        return sampleContacts;
    }

    private void setupListView() {
        adapter = new ContactAdapter(this, R.layout.item_contact, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            adapter.setSelectedPosition(position);
            Toast.makeText(MainActivity.this,
                    "Выбран: " + contacts.get(position).name(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void setupButtons() {
        btnSortName.setOnClickListener(v -> sortByName());

        btnSortPhone.setOnClickListener(v -> sortByPhone());

        btnSortAvatar.setOnClickListener(v -> sortByAvatar());

        btnAdd.setOnClickListener(v -> showAddContactDialog());
    }

    private void sortByName() {
        Collections.sort(contacts, (c1, c2) -> c1.name().compareToIgnoreCase(c2.name()));
        adapter.notifyDataSetChanged();
    }

    private void sortByPhone() {
        Collections.sort(contacts, (c1, c2) -> c1.phoneNumber().compareToIgnoreCase(c2.phoneNumber()));
        adapter.notifyDataSetChanged();
    }

    private void sortByAvatar() {
        Collections.sort(contacts, (c1, c2) -> c1.avatarType().compareTo(c2.avatarType()));
        adapter.notifyDataSetChanged();
    }

    private void showAddContactDialog() {
        AddContactDialog dialog = new AddContactDialog(this, newContact -> {
            contacts.add(newContact);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Контакт добавлен", Toast.LENGTH_SHORT).show();
        });
        dialog.show();
    }
}