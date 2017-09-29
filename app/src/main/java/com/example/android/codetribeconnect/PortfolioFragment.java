package com.example.android.codetribeconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RP on 2017/09/02.
 */

public class PortfolioFragment extends Fragment {
    FirebaseDatabase mFirebaseDatabase;
    FirebaseStorage mFirebaseStorage;
    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;
    private TextView profileName;
    private TextView profileSurname;
    private TextView profilePhoneNumber;
    private TextView profileEmail;
    private CircleImageView profileEdit;
    private TextView profileStatus;
    private TextView profileOccupation;
    ChildEventListener mChildEventListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_portfolio,container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("verified_user_profile");
        mStorageReference = mFirebaseStorage.getReference().child("verified_user_profile_photos");

        profileName = rootView.findViewById(R.id.profile_name);
        profileSurname = rootView.findViewById(R.id.profileSurname);
        profilePhoneNumber = rootView.findViewById(R.id.profile_mobile);
        profileEmail = rootView.findViewById(R.id.profile_email);
        profileStatus = rootView.findViewById(R.id.userStatus);
        profileOccupation = rootView.findViewById(R.id.occupation);
        profileEdit = rootView.findViewById(R.id.profile_edit);

        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),UserProfileEditorActivity.class);
                startActivity(intent);
            }
        });
        mChildEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ActiveUser activeUser = dataSnapshot.getValue(ActiveUser.class);
                profileName.setText(activeUser.getActiveUserName());
                profileSurname.setText(activeUser.getActiveUserSurname());
                profilePhoneNumber.setText(activeUser.getActiveUserNumber());
                profileEmail.setText(activeUser.getActiveUserEmail());
                profileStatus.setText(activeUser.getActiveUserStatus());
                profileOccupation.setText(activeUser.getActiveUserOccupation());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
        return rootView;
    }

    public PortfolioFragment() {
    }
}
