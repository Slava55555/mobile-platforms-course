package com.example.arrayAdapter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private PersonAdapter adapter;
    private List<Person> persons;
    private Random random;
    private int personIdCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        persons = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            addRandomPerson();
        }

        listView = findViewById(R.id.listView);
        adapter = new PersonAdapter(this, R.layout.list_item_person, persons);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> adapter.setSelectedPosition(position));

        Button btnAddPerson = findViewById(R.id.btnAddPerson);
        btnAddPerson.setOnClickListener(v -> {
            addRandomPerson();
            adapter.notifyDataSetChanged();
            listView.smoothScrollToPosition(persons.size() - 1);
        });

        Button btnClearList = findViewById(R.id.btnClearList);
        btnClearList.setOnClickListener(v -> {
            persons.clear();
            personIdCounter = 1;
            adapter.setSelectedPosition(-1);
            adapter.notifyDataSetChanged();
            for (int i = 0; i < 5; i++) {
                addRandomPerson();
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void addRandomPerson() {
        String[] firstNames = getResources().getStringArray(R.array.first_names);
        String[] lastNames = getResources().getStringArray(R.array.last_names);

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        Person person = new Person(firstName, lastName, personIdCounter++);
        persons.add(person);
    }
}
