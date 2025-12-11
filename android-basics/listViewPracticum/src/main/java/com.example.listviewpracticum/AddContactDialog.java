package com.example.listviewpracticum;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

public class AddContactDialog extends Dialog {
    private OnContactAddedListener listener;
    private EditText editName, editPhone;
    private Spinner spinnerAvatar;

    public AddContactDialog(@NonNull Context context, OnContactAddedListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_contact);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        spinnerAvatar = findViewById(R.id.spinnerAvatar);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCancel = findViewById(R.id.btnCancel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.avatar_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAvatar.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String avatarStr = spinnerAvatar.getSelectedItem().toString();

            if (name.isEmpty() || phone.isEmpty()) {
                return;
            }

            Contact.AvatarType avatarType = Contact.AvatarType.valueOf(avatarStr.toUpperCase());
            Contact newContact = new Contact(name, phone, avatarType);
            listener.onContactAdded(newContact);
            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());
    }

    public interface OnContactAddedListener {
        void onContactAdded(Contact newContact);
    }
}
