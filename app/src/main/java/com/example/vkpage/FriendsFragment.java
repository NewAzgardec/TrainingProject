package com.example.vkpage;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;


public class FriendsFragment extends Fragment {

    private ListView listOfFriends;
    private EditText editText;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        listOfFriends = getView().findViewById(R.id.list_of_friends);
        editText = getView().findViewById(R.id.friends_search);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    adapter.getFilter().filter(s);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final VKRequest vkRequest = VKApi.friends().get(VKParameters.from("fields", "first_name, last_name, photo_200", "order", "hints"));

        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKList vkList = (VKList) response.parsedModel;

                adapter = new ArrayAdapter<String>(getContext(), R.layout.text_friends, R.id.text_for_friends, vkList);
                listOfFriends.setAdapter(adapter);
            }

        });
        listOfFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

}