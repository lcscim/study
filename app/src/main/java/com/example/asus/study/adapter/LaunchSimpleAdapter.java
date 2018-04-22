package com.example.asus.study.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.asus.study.R;

import java.util.ArrayList;

public class LaunchSimpleAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<View> mViewList = new ArrayList<>();
    public LaunchSimpleAdapter(Context context,int[] imageArray){
        inflater = LayoutInflater.from(context);
        mContext = context;
        for(int i = 0;i<imageArray.length;i++){
            View view = inflater.inflate(R.layout.item_launch,null);
            ImageView imageView = view.findViewById(R.id.iv_launch);
            RadioGroup radioGroup = view.findViewById(R.id.rg_indicate);
            Button button = view.findViewById(R.id.btn_start);
            imageView.setImageResource(imageArray[i]);
            for (int j = 0;j<imageArray.length;j++){
                RadioButton radioButton = new RadioButton(mContext);
                radioButton.setLayoutParams(new LayoutParams
                        (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                radioButton.setButtonDrawable(R.drawable.launch_guide);
                radioButton.setPadding(10,10,10,10);
                radioGroup.addView(radioButton);
            }
            ((RadioButton)radioGroup.getChildAt(i)).setChecked(true);
            if (i==imageArray.length-1){
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"hello world",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            mViewList.add(view);
        }
    }
    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }
}
