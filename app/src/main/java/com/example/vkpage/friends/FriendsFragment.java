package com.example.vkpage.friends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.vkpage.R;

import java.util.List;

public class FriendsFragment extends Fragment implements FriendsObserver {

    private ListView listOfFriends;
    private List<FriendsModel> myList;
    private EditText editText;
    private FriendsList friendsModel;
    private FriendsAdapter friendsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        listOfFriends = view.findViewById(R.id.list_of_friends);
        friendsAdapter = new FriendsAdapter(getContext(), myList);
        listOfFriends.setAdapter(friendsAdapter);

        return view;

    }

    public FriendsFragment() {
        friendsModel = new FriendsList();
        friendsModel.setObserver(this);
        friendsModel.requestFriends();
        myList = friendsModel.getFriendList();
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {

        editText = getView().findViewById(R.id.friends_search);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    friendsAdapter.getFilter().filter(s);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void update() {

        friendsAdapter.notifyDataSetChanged();
    }
}