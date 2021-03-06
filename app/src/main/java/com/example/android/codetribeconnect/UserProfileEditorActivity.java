package com.example.android.codetribeconnect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by RP on 2017/09/02.
 */

public class UserProfileEditorActivity extends AppCompatActivity {
    public static final int DEFAULT_MSG_LENGTH_LIMIT  = 1000;
    public static final int RC_PHOTO_PICKER = 2;
    private EditText userNameEditText;
    private EditText userSurnameEditText;
    private EditText userCurrentOccupation;
    private EditText userPhoneNumber;
    private EditText userEmailEditText;
    private Button userUpdateButton;
    DatabaseReference mDatabaseReference;
    StorageReference mStoragereference;
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_ALUMNI = 1;
    public static final int STATUS_INTERN = 2;
    private Spinner mStatusSpinner;
    private boolean mUserHasChanged = false;
    private int mStatus = STATUS_UNKNOWN;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mUserHasChanged = true;
            return false;
        }
    };


    public static boolean isValidGender(int gender){
        if (gender == STATUS_UNKNOWN || gender == STATUS_ALUMNI || gender == STATUS_INTERN){
            return true;
        }
        return false;}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityupdateprofile2);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("verified_user_profile");
        mStoragereference = FirebaseStorage.getInstance().getReference()
                .child("verified_user_profile_photos");
        userNameEditText = (EditText) findViewById(R.id.name_editView);
        userSurnameEditText = (EditText) findViewById(R.id.surnameEditText);
        userCurrentOccupation = (EditText) findViewById(R.id.occpateEditText);
        userPhoneNumber = (EditText) findViewById(R.id.cell_editTextView);
        userEmailEditText = (EditText) findViewById(R.id.emailEditText);
        userUpdateButton = (Button) findViewById(R.id.userUpdateButton);
        mStatusSpinner = (Spinner) findViewById(R.id.spinner_status);






        userUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                ActiveUser items = new ActiveUser();
                items.setActiveUserName(userNameEditText.getText().toString());
                items.setActiveUserSurname(userSurnameEditText.getText().toString());
                items.setActiveUserOccupation(userCurrentOccupation.getText().toString());
                items.setActiveUserEmail(userEmailEditText.getText().toString());
                items.setActiveUserStatus(mStatusSpinner.getSelectedItem().toString());
                items.setActiveUserEmail(userEmailEditText.getText().toString());
                items.setActiveUserNumber(userPhoneNumber.getText().toString());
                items.setActiveUserImageUrl(null);
                mDatabaseReference.push().setValue(items);
                userNameEditText.setText("");
                userSurnameEditText.setText("");
                userCurrentOccupation.setText("");
                userEmailEditText.setText("");
                userPhoneNumber.setText("");
                Toast.makeText(UserProfileEditorActivity.this, "Information saved...",Toast.LENGTH_SHORT).show();









            }
        });
        mStatusSpinner.setOnTouchListener(mTouchListener);
        setupSpinner();


        userNameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userSurnameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userCurrentOccupation.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userPhoneNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        userEmailEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mStatusSpinner.setAdapter(statusSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.status_alumni))) {
                        mStatus = STATUS_ALUMNI;
                    } else if (selection.equals(getString(R.string.status_intern))) {
                        mStatus = STATUS_INTERN;
                    } else {
                        mStatus = STATUS_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mStatus = STATUS_UNKNOWN;
            }
        });
    }
}

