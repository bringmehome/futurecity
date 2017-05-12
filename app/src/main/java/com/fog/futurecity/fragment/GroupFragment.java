package com.fog.futurecity.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fog.futurecity.R;
import com.fog.futurecity.helper.GroupItem;
import com.fog.futurecity.helper.GroupList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIN on 2017/5/10.
 */

public class GroupFragment extends Fragment {
    private String TAG = "---group---";

    private ListView group_lv;
    private SwipeRefreshLayout mSwipeLayout;
    private GroupAdapter groupAdapter; // ListView的adapter ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group_frag, container, false);


        initView(view);

        return view;
    }

    private void initView(View view) {
        TextView title_name_tv = (TextView) view.findViewById(R.id.title_name_tv);
        group_lv = (ListView) view.findViewById(R.id.group_lv);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeLayout);

        title_name_tv.setText(R.string.title_group);

        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            public void onRefresh() {
                getGroupList();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        mSwipeLayout.setColorSchemeResources(R.color.colorAccent);

        initAdapter();
    }

    private List<GroupItem> groupdata = new ArrayList<>();

    private void initAdapter() {
        groupAdapter = new GroupAdapter(getActivity(), groupdata, R.layout.group_item);
        group_lv.setAdapter(groupAdapter);
    }

    class GroupAdapter extends BaseAdapter {
        private Context acontext;
        private int resource;
        private List<GroupItem> data;

        public GroupAdapter(Context context, List<GroupItem> list, int resource) {
            this.acontext = context;
            this.data = list;
            this.resource = resource;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(acontext, resource, null);
                holder = new ViewHolder(convertView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (getCount() > 0) {
                try {
                    holder.group_i_name.setText(data.get(position).getName());
                    holder.group_i_id.setText(data.get(position).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.group_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "---GroupAdapter---");
                    }
                });
            }
            return convertView;
        }

        class ViewHolder {
            TextView group_i_id, group_i_name;
            LinearLayout group_ll;

            public ViewHolder(View convertView) {
                group_i_name = (TextView) convertView.findViewById(R.id.group_i_name);
                group_i_id = (TextView) convertView.findViewById(R.id.group_i_id);
                group_ll = (LinearLayout) convertView.findViewById(R.id.group_ll);

                convertView.setTag(this);
            }
        }
    }

    private void getGroupList() {
        String message = "{\"id\":\"123456\",\"name\":\"names\"}";
        Gson gson = new Gson();
        GroupItem data = gson.fromJson(message, GroupItem.class);
        groupdata.add(data);
        groupAdapter.notifyDataSetChanged();
    }
}
