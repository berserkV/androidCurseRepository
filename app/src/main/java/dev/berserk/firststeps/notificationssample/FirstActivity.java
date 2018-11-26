package dev.berserk.firststeps.notificationssample;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.KeysConstants;
import dev.berserk.firststeps.util.Util;

public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.buttonSend)
    Button mButtonSend;

    @BindView(R.id.editTextMessage)
    EditText mEditTextMessage;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSend) public void sendMessage() {
        HashMap<String, Object> extraData = new HashMap<>();

        message = mEditTextMessage.getText().toString();
        extraData.put(KeysConstants.MESSAGE, message);

        if (!message.isEmpty()) {
            Util.changeActivityAndFinish(this, NotificationsActivity.class, extraData,
                    true);
        }
        else {
            createSimpleDialog(this);
        }
    }

    private void createSimpleDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.empty_message)
                .setMessage(R.string.insert_message_to_send)
                .setPositiveButton("Accept",
                        (dialog, which) -> {
                            dialog.dismiss();
                        });
        builder.show();
    }
}
