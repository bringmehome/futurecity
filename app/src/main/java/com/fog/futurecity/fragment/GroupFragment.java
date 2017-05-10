package com.fog.futurecity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fog.futurecity.R;

/**
 * Created by SIN on 2017/5/10.
 */

public class GroupFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group_frag, container, false);
        TextView title_name_tv = (TextView)view.findViewById(R.id.title_name_tv);
        title_name_tv.setText(R.string.title_group);
        return view;
    }
}
