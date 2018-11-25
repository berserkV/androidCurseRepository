package dev.berserk.firststeps.viewPagerFormActivity.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.URL;
import dev.berserk.firststeps.viewPagerFormActivity.interfaces.OnBackPressed;

public class ImageFragment extends Fragment implements OnBackPressed {

    @BindView(R.id.circleImageViewProfile)
    CircleImageView circleImageViewProfile;

    public static String TAG = ImageFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);

        ButterKnife.bind(this, view);

        Glide.with(getActivity())
                .load(URL.VENOM_PROFILE_URL)
                .asGif()
                .centerCrop()
                .override(180, 180)
                .crossFade()
                .into(circleImageViewProfile);

        return view;
    }

    public static ImageFragment newInstance() {
        ImageFragment myFragment = new ImageFragment();
        return  myFragment;
    }

    @Override
    public void onBackPressed() {

    }
}
