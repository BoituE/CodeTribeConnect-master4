package com.example.android.codetribeconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RP on 2017/09/02.
 */
public  class TribeChatFragment extends Fragment {

    private FloatingActionButton chatButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item,container, false );

        chatButton = rootView.findViewById(R.id.floatingActionButton1);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Tribe_DialogActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
