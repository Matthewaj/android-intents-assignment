package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    public static final String CONTACT_INFO = "ContactInfo";
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText phoneNumET;
    private EditText emailET;
    private EditText addressET;
    private EditText websiteET;

    private Contact contact;

    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form_layout);

        firstNameET = findViewById(R.id.firstNameET);
        lastNameET = findViewById(R.id.lastNameET);
        phoneNumET = findViewById(R.id.phoneNumET);
        emailET = findViewById(R.id.emailET);
        addressET = findViewById(R.id.addressET);
        websiteET = findViewById(R.id.websiteET);


    }

    public void saveContact(View view) {
        Intent intent = new Intent(this, ContactInfoActivity.class);

        contact = new Contact();

        contact.setFirstName(firstNameET.getText().toString());
        contact.setLastName(lastNameET.getText().toString());
        contact.setPhoneNumber(phoneNumET.getText().toString());
        contact.setEmailAddress(emailET.getText().toString());
        contact.setAddress(addressET.getText().toString());
        contact.setWebsite(websiteET.getText().toString());

        intent.putExtra(CONTACT_INFO, contact);
        startActivity(intent);
    }


}
