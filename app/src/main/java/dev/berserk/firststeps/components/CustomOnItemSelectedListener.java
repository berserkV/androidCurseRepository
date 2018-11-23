package dev.berserk.firststeps.components;

import android.view.View;
import android.widget.AdapterView;

import dev.berserk.firststeps.util.Util;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Util.showLog("Select", "Select "+parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
