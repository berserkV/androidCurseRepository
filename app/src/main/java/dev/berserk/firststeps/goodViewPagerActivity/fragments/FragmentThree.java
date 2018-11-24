package dev.berserk.firststeps.goodViewPagerActivity.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.Util;

public class FragmentThree extends Fragment {

    public static String TAG = FragmentThree.class.getSimpleName();

    @BindView(R.id.buttonFragThree)
    Button mButtonFragmentThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_three, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.buttonFragThree) public void clickFragmentThree(){
        Util.showToast("Click fragment three", getActivity());
    }

    public static FragmentThree newInstance() {
        FragmentThree myFragment = new FragmentThree();
        return  myFragment;
    }
}
