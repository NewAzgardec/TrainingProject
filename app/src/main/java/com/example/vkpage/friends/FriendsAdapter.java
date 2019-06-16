package com.example.vkpage.friends;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.vkpage.R;
import com.example.vkpage.asynctask.CircleImageTask;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends BaseAdapter {

    private List<FriendsModel> friendList;
    private LayoutInflater layoutInflater;
    private List<String> originalData = null;

    FriendsAdapter(Context context, List<FriendsModel> friendList) {
        this.friendList = friendList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.text_friends, parent, false);
        }

        FriendsModel friend = (FriendsModel) getItem(position);

        CircleImageView friendPhoto = view.findViewById(R.id.friendPhoto);
        new CircleImageTask(friendPhoto).execute(friend.photo_200);

        TextView friendFullName = view.findViewById(R.id.friendFullName);

        friendFullName.setText(friend.first_name + "\n" + friend.last_name);

        TextView isFriendOnline = view.findViewById(R.id.isFriendOnline);

        if (friend.online == 1) {
            isFriendOnline.setText(R.string.online_status);
        } else {
            isFriendOnline.setText("");
        }

        return view;
    }
}