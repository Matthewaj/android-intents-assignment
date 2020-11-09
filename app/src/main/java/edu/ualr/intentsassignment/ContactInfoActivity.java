package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import edu.ualr.intentsassignment.model.Contact;

import static edu.ualr.intentsassignment.ContactFormActivity.CONTACT_INFO;

public class ContactInfoActivity extends AppCompatActivity {
    private TextView nameView;
    private TextView phoneNumView;
    private TextView emailView;
    private TextView addressView;
    private TextView websiteView;

    private Chip callChip;
    private Chip textChip;
    private Chip emailChip;
    private Chip directionChip;
    private Chip webChip;

    private Contact contact;

    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info_layout);

        nameView = findViewById(R.id.nameView);
        phoneNumView = findViewById(R.id.phoneNumView);
        emailView = findViewById(R.id.emailView);
        addressView = findViewById(R.id.addressView);
        websiteView = findViewById(R.id.websiteView);

        callChip = findViewById(R.id.callButton);
        textChip = findViewById(R.id.textButton);
        emailChip = findViewById(R.id.emailButton);
        directionChip = findViewById(R.id.directionsButton);
        webChip = findViewById(R.id.webButton);

        callChip.setOnClickListener((View v) -> startCall(contact.getPhoneNumber()));
        textChip.setOnClickListener((View v) -> sendText(contact.getPhoneNumber()));
        emailChip.setOnClickListener((View v) -> sendEmail(contact.getEmailAddress()));
        directionChip.setOnClickListener((View v) -> openDirections(contact.getAddress()));
        webChip.setOnClickListener((View v) -> openWebsite(contact.getWebsite()));

        updateContactInfo();
    }

    private void updateContactInfo() {
        Intent intent = getIntent();
        contact = intent.getParcelableExtra(CONTACT_INFO);

        nameView.setText(String.format("%s %s", contact.getFirstName(), contact.getLastName()));
        phoneNumView.setText(contact.getPhoneNumber());
        emailView.setText(contact.getEmailAddress());
        addressView.setText(contact.getAddress());
        websiteView.setText(contact.getWebsite());
    }

    public void startCall(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendText(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("sms:" + phoneNum));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendEmail(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openDirections(String address) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0")
                .buildUpon()
                .appendQueryParameter("q", address)
                .build());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openWebsite(String website) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
