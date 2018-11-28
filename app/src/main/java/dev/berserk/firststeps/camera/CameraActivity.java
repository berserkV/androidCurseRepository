package dev.berserk.firststeps.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.Util;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_PICTURE = 1;
    private static final int GALLERY_PIC = 2;

    private int PERMISSION_ALL = 1;

    @BindView(R.id.imageViewCamera)
    ImageView mImageViewCamera;

    @BindView(R.id.buttonCamera)
    Button mButtonCamera;

    @BindView(R.id.buttonMedia)
    Button mButtonMedia;

    String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonMedia) public void tryGetMediaImage() {
        if (!Util.hasPermission(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        else {
            getMediaImage();
        }
    }

    @OnClick(R.id.buttonCamera) public void tryGetCameraImage() {
        if (!Util.hasPermission(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        else {
            getCameraImage();
        }
    }

    private void getMediaImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_PIC);
    }

    private void getCameraImage() {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camIntent, CAMERA_PICTURE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //user gave permissions
        }
        else {
            //User not gave permissions
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Util.showLog(CameraActivity.class.getSimpleName(), "onActivityResult");
        switch (requestCode) {
            case CAMERA_PICTURE:
                Util.showLog(CameraActivity.class.getSimpleName(), "onCameraPicture");
                if (resultCode == Activity.RESULT_OK) {
                    Util.showLog(CameraActivity.class.getSimpleName(), "onCameraPictureResultOK");
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    mImageViewCamera.setImageBitmap(photo);
                }
                else {

                }
                break;
            case GALLERY_PIC:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri pickedImage = data.getData();

                        // Let's read picked image path using content resolver
                        String[] filePath = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null,
                                null);
                        cursor.moveToFirst();
                        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
                        Uri myUri = Uri.parse(imagePath);

                        mImageViewCamera.setImageBitmap(bitmap);

                        // Do something with the bitmap

                        // At the end remember to close the cursor or you will end with a RunTimeException
                        cursor.close();
                    }
                }
                break;
        }
    }
}
