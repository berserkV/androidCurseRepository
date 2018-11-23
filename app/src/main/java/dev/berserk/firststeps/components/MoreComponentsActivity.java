package dev.berserk.firststeps.components;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dev.berserk.firststeps.R;

public class MoreComponentsActivity extends AppCompatActivity {

    private Spinner mSpinnerCountries;

    private Spinner mSpinnerList;

    private Button mButtonOptionsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_components);

        setUpAllViews();

        addListenerOnSpinnerItemSelection();
        addItemsOnListenerList();
        addListenerOnButton();
    }

    // get the selected dropdown list value
    private void addListenerOnButton() {
        mButtonOptionsSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSimpleDialog(MoreComponentsActivity.this);
            }
        });
    }

    public void createSimpleDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Chosen")
                .setMessage("\nSpinnerCountries : "+String.valueOf(mSpinnerCountries.getSelectedItem()) +
                        "\nSpinnerList : "+ String.valueOf(mSpinnerList.getSelectedItem()))
                .setPositiveButton("Accepted",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        builder.show();
    }

    // add items into spinner dynamically
    private void addItemsOnListenerList() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>( this,
                R.layout.spinner_item, items);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        mSpinnerList.setAdapter(dataAdapter);
    }

    private void addListenerOnSpinnerItemSelection() {
        mSpinnerCountries.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private void setUpAllViews() {
        mSpinnerCountries = findViewById(R.id.spinnerCountries);
        mSpinnerList = findViewById(R.id.spinnerList);
        mButtonOptionsSelected = findViewById(R.id.buttonOptionsSelected);
    }
}