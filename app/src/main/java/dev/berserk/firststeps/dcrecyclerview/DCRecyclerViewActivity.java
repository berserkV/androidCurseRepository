package dev.berserk.firststeps.dcrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.databaseandrecyclerview.RealRecyclerSampleActivity;
import dev.berserk.firststeps.dcrecyclerview.adapter.DCAdapter;
import dev.berserk.firststeps.dcrecyclerview.models.CharacterDC;
import dev.berserk.firststeps.util.Util;

public class DCRecyclerViewActivity extends AppCompatActivity {

    private static String TAG = DCRecyclerViewActivity.class.getSimpleName();

    private List<CharacterDC> characterDCList = new ArrayList<>();

    private DCAdapter dcAdapter;

    @BindView(R.id.recyclerViewDC)
    RecyclerView mRecyclerViewDC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avenger_recycler_view);

        ButterKnife.bind(this);
        createDoomies();
        setUpRecyclerView();
    }

    private void setUpRecyclerView( ) {
        dcAdapter = new DCAdapter(characterDCList, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerViewDC.setAdapter(dcAdapter);
        mRecyclerViewDC.setLayoutManager(linearLayoutManager);
    }

    public void createDoomies() {
        characterDCList.add(new CharacterDC("Batman 1"
                ,"2001"
                ,"Batman"
                ,"https://static.vix.com/es/sites/default/files/styles/large/public/btg/cine.batanga.com/files/curiosidades-batman-begins.jpg?itok=o6IHnBZF"));

        characterDCList.add(new CharacterDC("Wonder Woman"
                ,"2017"
                ,"Wonder Woman"
                ,"https://laopinionla.files.wordpress.com/2017/08/96268248_2ca421dc-198a-4888-aeb3-db18c3fba916.gif?w=940"));

        characterDCList.add(new CharacterDC("Man of steel"
                ,"2005"
                ,"Superman"
                ,"http://wallpapershdnow.com/images/movies/superhero/superman-man-of-steel/superman-man-of-steel-wallpaper-9.jpg"));

    }
}
