package dev.berserk.firststeps.goodViewPagerActivity.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;

public class FragmentOne extends Fragment {

    @BindView(R.id.imageViewFragOne)
    ImageView mImgFragmentOne;

    public static String TAG = FragmentOne.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ButterKnife.bind(this, view);

        Glide.with(getActivity())
                .load("https://png2.kisspng.com/sh/4d362f1a35000394d753be3de90c3302/L0KzQYm3U8I3N5xpj5H0aYP2gLBuTfRqaZ9mReJ7aX7mdX76lgBmephuit42c4XzdcP0gf4ua5DygdV8LXbofbLzhb14d59pfeQ2d3BwcbA0VfFjQWo6TalqZkW4dIO1VsY2O2U9UKg6NUK1QYK4WMMyP2I9SpD5bne=/kisspng-diana-prince-supergirl-superman-comics-female-wonder-woman-5ab99557af55d2.6653488615221118317182.png")
                .crossFade()
                .into(mImgFragmentOne);

        return view;
    }

    public static FragmentOne newInstance() {
        FragmentOne myFragment = new FragmentOne();
        return  myFragment;
    }
}
