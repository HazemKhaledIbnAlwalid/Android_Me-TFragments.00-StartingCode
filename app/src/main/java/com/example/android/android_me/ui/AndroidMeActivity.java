/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
             /* TODO (5) Create BodyPartsFragment instance and display it using Fragment Manager
                 Hint : user FragmentManager of android.support.v4.app.FragmentManager
            */
            int headIndex = getIntent().getIntExtra("HI",0);
            int bodyIndex = getIntent().getIntExtra("BI",0);
            int legIndex = getIntent().getIntExtra("LI",0);
            createFragment(AndroidImageAssets.getHeads(), headIndex, R.id.fragmentHeadContainer);
            createFragment(AndroidImageAssets.getBodies(), bodyIndex, R.id.fragmentBodyContainer);
            createFragment(AndroidImageAssets.getLegs(), legIndex, R.id.fragmentLegContainer);
        }

    }

    private void createFragment(List<Integer> mImageIds, Integer mListIndex, Integer bodyPartId) {
        BodyPartsFragment partFragment = new BodyPartsFragment();

        partFragment.setmImageIds(mImageIds);
        partFragment.setmListIndex(mListIndex);

        FragmentManager mFragmentManager = getSupportFragmentManager();

        mFragmentManager.beginTransaction()
                .add(bodyPartId, partFragment)
                .commit();
    }
}
