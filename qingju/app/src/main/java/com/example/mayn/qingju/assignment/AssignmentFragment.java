package com.example.mayn.qingju.assignment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mayn.qingju.R;
import com.example.mayn.qingju.customcontrol.ObservableScrollView;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 转让界面
 * Created by tsh on 2017/10/24.
 */

public class AssignmentFragment extends Fragment {
    @InjectView(R.id.tv_topView)
    TextView tvTopView;
    @InjectView(R.id.ll_topView)
    LinearLayout llTopView;
    @InjectView(R.id.sv_contentView)
    ObservableScrollView svContentView;
    @InjectView(R.id.ll_fixedView)
    LinearLayout llFixedView;
    private int mHeight;
    //private TextView mTextView;

    public AssignmentFragment() {

    }


    public static AssignmentFragment newInstance() {

        Bundle args = new Bundle();
        args.putString("text", "转让");
        AssignmentFragment fragment = new AssignmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        initViews(view);
        ButterKnife.inject(this, view);

        /*ViewTreeObserver viewTreeObserver = llTopView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTopView.getViewTreeObserver().removeGlobalOnLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {


                        mHeight = llTopView.getTop();
                    }
                });

            }
        });*/


        svContentView.setOnObservableScrollViewScrollChanged(new ObservableScrollView.OnObservableScrollViewScrollChanged() {
            @Override
            public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {
                if (t >= llTopView.getTop()) {
                    if (tvTopView.getParent() != llFixedView) {
                        llTopView.removeView(tvTopView);
                        llFixedView.addView(tvTopView);
                    }
                } else {
                    if (tvTopView.getParent() != llTopView) {
                        llFixedView.removeView(tvTopView);
                        llTopView.addView(tvTopView);
                    }
                }

            }
        });

        return view;
    }

    private void initViews(View view) {
        //mTextView = view.findViewById(R.id.tv_assignment);
        Bundle bundle = getArguments();
        //mTextView.setText(bundle == null ? "" : bundle.getString("text"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
