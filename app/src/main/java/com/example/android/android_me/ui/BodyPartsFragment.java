package com.example.android.android_me.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/*
 TODO (2) Create new class called BodyPartsFragment that extends fragment to display an image of an Android_Me body part
 */

public class BodyPartsFragment extends Fragment {


    private static final String TAG = "BodyPartsFragment";

    private static final String IMAGE_IDs_List = "mImageIds";

    private static final String LIST_INDEX = "mListIndex";

    private List<Integer> mImageIds;

    private Integer mListIndex;
    /*
    TODO (3) Inside the class create empty constructor and override onCreateView
     */

    //Mandatory constructor for instantiating the fragment
    public BodyPartsFragment() {
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(Integer mListIndex) {
        this.mListIndex = mListIndex;
    }

    /*
            Inflate fragment layout and set any image resources
             */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // handling orientation changing
        if(savedInstanceState!=null){
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_IDs_List);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);

        final ImageView bodyPartImageView = (ImageView)rootView.findViewById(R.id.imageViewBodyPart);

        if(mImageIds!=null) {
            bodyPartImageView.setImageResource(mImageIds.get(mListIndex));

            // create on click listener to change image on clicking
            bodyPartImageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    }else {
                        mListIndex=0;
                    }
                    bodyPartImageView.setImageResource(mImageIds.get(mListIndex));
                }
            });

        }else {
            Log.i(TAG, "onCreateView: mImageIds List is empty");
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        //save app current state
        currentState.putIntegerArrayList(IMAGE_IDs_List, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);
    }
}
