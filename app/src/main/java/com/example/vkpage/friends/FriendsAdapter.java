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
import com.example.vkpage.asynctask.ImageTask;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends BaseAdapter implements Filterable {

    private List<FriendsList> friendList;
    private LayoutInflater layoutInflater;
    private List<String> originalData = null;
    private ItemFilter itemFilter = new ItemFilter();

    FriendsAdapter(Context context, List<FriendsList> friendList) {
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

        FriendsList friend = (FriendsList) getItem(position);

        CircleImageView friendPhoto = view.findViewById(R.id.friendPhoto);
        new ImageTask(friendPhoto).execute(friend.photo_200);

        TextView friendFullName = view.findViewById(R.id.friendFullName);

        friendFullName.setText(friend.first_name + "\n" + friend.last_name);

        TextView isFriendOnline = view.findViewById(R.id.isFriendOnline);

        if (friend.online == 1) {
            isFriendOnline.setText("online");
        } else {
            isFriendOnline.setText("");
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<String> list = originalData;
            int count = list.size();
            final ArrayList<String> myList = new ArrayList<>(count);
            String filterableString;
            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    myList.add(filterableString);
                }
            }

            results.values = myList;
            results.count = myList.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<String> filteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}