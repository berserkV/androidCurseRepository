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

public class DataFragment extends Fragment implements OnBackPressed {

    public static String TAG = DataFragment.class.getSimpleName();

    @BindView(R.id.textViewName)
    TextView textViewName;

    @BindView(R.id.textViewLastName)
    TextView textViewLastName;

    private String name;

    private String lastName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getArguments().getString(KeysConstants.NAME);
        lastName = getArguments().getString(KeysConstants.LAST_NAME);
        Util.showLog(TAG, "Message received "+name+" "+lastName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);

        ButterKnife.bind(this, view);
        textViewName.setText(name);
        textViewLastName.setText(lastName);

        return view;
    }

    public static DataFragment newInstance(String name, String lastName) {
        DataFragment myFragment = new DataFragment();
        Bundle args = new Bundle();

        args.putString(KeysConstants.NAME, name);
        args.putString(KeysConstants.LAST_NAME, lastName);
        myFragment.setArguments(args);

        return  myFragment;
    }

    @Override
    public void onBackPressed() {

    }
}
