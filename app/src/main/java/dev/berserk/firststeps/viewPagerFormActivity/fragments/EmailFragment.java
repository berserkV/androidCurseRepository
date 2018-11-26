package dev.berserk.firststeps.viewPagerFormActivity.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.KeysConstants;
import dev.berserk.firststeps.util.Util;
import dev.berserk.firststeps.viewPagerFormActivity.interfaces.OnBackPressed;

public class EmailFragment extends Fragment implements OnBackPressed {

    public static String TAG = EmailFragment.class.getSimpleName();

    @BindView(R.id.textViewEmail)
    TextView textViewEmail;

    private String email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getArguments().getString(KeysConstants.EMAIL);
        Util.showLog(TAG, "Message received "+email);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_email, container, false);

        ButterKnife.bind(this, view);
        textViewEmail.setText(email);

        return view;
    }

    public static EmailFragment newInstance(String email) {
        EmailFragment myFragment = new EmailFragment();
        Bundle args = new Bundle();

        args.putString(KeysConstants.EMAIL, email);
        myFragment.setArguments(args);

        return  myFragment;
    }

    @Override
    public void onBackPressed() {

    }
}
