package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;


    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout)!=null){
            isTwoPane = true;

            GridView imagesGridView = (GridView)findViewById(R.id.images_grid_view);
            imagesGridView.setNumColumns(2);

            Button nextButton = (Button)findViewById(R.id.button_next);
            nextButton.setVisibility(View.INVISIBLE);

            if (savedInstanceState == null) {

                int headIndex = getIntent().getIntExtra("HI",0);
                int bodyIndex = getIntent().getIntExtra("BI",0);
                int legIndex = getIntent().getIntExtra("LI",0);
                createFragment(AndroidImageAssets.getHeads(), headIndex, R.id.fragmentHeadContainer);
                createFragment(AndroidImageAssets.getBodies(), bodyIndex, R.id.fragmentBodyContainer);
                createFragment(AndroidImageAssets.getLegs(), legIndex, R.id.fragmentLegContainer);
            }
        }else {
            isTwoPane = false;
        }

    }

    private void createFragment(List<Integer> mImageIds, Integer mListIndex, Integer bodyPartId) {
        BodyPartsFragment partFragment = new BodyPartsFragment();

        partFragment.setmImageIds(mImageIds);
        partFragment.setmListIndex(mListIndex);

        FragmentManager mFragmentManager = getSupportFragmentManager();

        if(isTwoPane){
            mFragmentManager.beginTransaction()
                    .replace(bodyPartId,partFragment)
                    .commit();
        }else {
            mFragmentManager.beginTransaction()
                    .add(bodyPartId, partFragment)
                    .commit();
        }
    }

    @Override
    public void OnImageSelected(int position) {
        int bodyPartNumber = position/12;

        int listIndex = position%12;

        if(isTwoPane){
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    createFragment(AndroidImageAssets.getHeads(), headIndex, R.id.fragmentHeadContainer);
                    break;
                case 1:
                    bodyIndex = listIndex;
                    createFragment(AndroidImageAssets.getBodies(), bodyIndex, R.id.fragmentBodyContainer);
                    break;
                case 2:
                    createFragment(AndroidImageAssets.getLegs(), legIndex, R.id.fragmentLegContainer);
                    legIndex = listIndex;
                    break;

                default:
                    break;
            }

        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;

                default:
                    break;
            }

            Bundle saveIndices = new Bundle();
            saveIndices.putInt("HI", headIndex);
            saveIndices.putInt("BI", bodyIndex);
            saveIndices.putInt("LI", legIndex);

            final Intent mIntent = new Intent(this, AndroidMeActivity.class);
            mIntent.putExtras(saveIndices);

            Button nextButton = (Button) findViewById(R.id.button_next);

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(mIntent);
                }
            });
        }

    }
}
