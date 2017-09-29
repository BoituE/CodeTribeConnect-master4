package com.example.android.codetribeconnect;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by RP on 2017/09/02.
 */

public class ActiveUsersAdapterActivity extends ArrayAdapter<ActiveUser> {

    public ActiveUsersAdapterActivity(@NonNull Context context, @LayoutRes int resource, @NonNull List<ActiveUser> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_other_user_profiles,parent,false);
        }

        TextView activeUserName = listItemView.findViewById(R.id.activeUserName);
        TextView activeUserSurname = listItemView.findViewById(R.id.activeUserSurname);
        TextView activeUserStatus = listItemView.findViewById(R.id.activeUserStatus);
        ImageView activeUserImage = listItemView.findViewById(R.id.profile_image);
        ActiveUser activeUser = getItem(position);
        boolean isImage = activeUser.getActiveUserImageUrl() != null;
        if (isImage){

            Glide.with(activeUserImage.getContext())
                    .load(activeUser.getActiveUserImageUrl())
                    .into(activeUserImage);
            activeUserName.setText(activeUser.getActiveUserName());
            activeUserSurname.setText(activeUser.getActiveUserSurname());
            activeUserStatus.setText(activeUser.getActiveUserStatus());
        }
        else{

            activeUserImage.setImageResource(R.drawable.icon_profile_empty);
            activeUserName.setText(activeUser.getActiveUserName());
            activeUserSurname.setText(activeUser.getActiveUserSurname());
            activeUserStatus.setText(activeUser.getActiveUserStatus());
        }
        return listItemView;



    }


}
