package dev.berserk.firststeps.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import dev.berserk.firststeps.R;

public class Util {

    public static void showLog( String TAG, String message ) {
        Log.e( TAG, "====> " + message + " <====");
    }

    public static void showLogInfo( String TAG, String message ) {
        Log.i( TAG, "====> " + message + " <====");
    }

    public static void showSnackBar(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void setUpImageViewLoad(String url, ImageView imageView, Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(context,R.color.colorAccent),
                PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .crossFade(2000)
                .into(imageView);
    }

    public static boolean isEmailAddressValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        String emailRegex = "((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";

        Pattern pat = Pattern.compile(emailRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }

    public static CircularProgressDrawable getCircularProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(context,R.color.colorAccent),
                PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();

        return circularProgressDrawable;
    }

    public static void changeActivityAndFinish(Activity activity, Class aClass,
            HashMap<String, String> extraData) {
        Intent intent = new Intent(activity, aClass);

        if (extraData != null && !extraData.isEmpty()) {
            /*
            extraData.entrySet().stream()
                    .forEach( e -> {
                        intent.putExtra(e.getKey(), e.getValue());
                    });
             */
            for(Map.Entry entry : extraData.entrySet()) {
                intent.putExtra(entry.getKey().toString(), entry.getValue().toString());
            }
        }

        activity.startActivity(intent);
        activity.finish();
    }
}
