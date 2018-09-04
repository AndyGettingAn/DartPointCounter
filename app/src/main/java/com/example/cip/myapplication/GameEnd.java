package com.example.cip.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;

import com.facebook.GraphRequest;
import com.facebook.login.widget.LoginButton;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.facebook.FacebookSdk;


public class GameEnd extends AppCompatActivity {


    JSONObject response, profile_pic_data, profile_pic_url;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ShareDialog shareDialog;


    CallbackManager callbackManager;
    ProgressDialog progressDialog;
    LoginButton loginButton;
    TextView textView;
    ShareButton shareButton;
    Button b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.win);
        mp.start();



        shareButton = (ShareButton) findViewById(R.id.share_button);
        b = (Button) findViewById(R.id.button);


       shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareLinkContent fbShare = new ShareLinkContent.Builder()
                        .setContentTitle("Test: Share Button")
                        .setContentDescription("Test: Beschreibung")
                        .setContentUrl(Uri.parse("http://www.uni-regensburg.de/"))
                        .build();
                ShareDialog.show(GameEnd.this, fbShare);
                shareButton.setText("Du hast button gedrückt");
                b.setText("Du hast button gedrückt");
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareLinkContent fbShare = new ShareLinkContent.Builder()
                        .setContentTitle("Test: Share Content Button")
                        .setContentDescription("Test: Beschreibung")
                        .setContentUrl(Uri.parse("http://www.uni-regensburg.de/"))
                        .build();
                ShareDialog.show(GameEnd.this, fbShare);
                shareButton.setText("Du hast Share Content gedrückt");
                b.setText("Du hast Share Content gedrückt");
            }
        });



        /*Button button = (Button) findViewById(R.id.button);
        shareDialog = new ShareDialog(this);  // initialize facebook shareDialog.
        Button imageShare = (Button)  findViewById(R.id.imageShare);
        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageToShareFacebook();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Android Facebook Integration and Login Tutorial")
                            .setImageUrl(Uri.parse("https://www.studytutorial.in/wp-content/uploads/2017/02/FacebookLoginButton-min-300x136.png")).setContentDescription("This tutorial explains how to integrate Facebook and Loginthrough Android Application") .setContentUrl(Uri.parse("https://www.studytutorial.in/android-facebook-integration-and-login-tutorial")).build();


                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }
            }
        });*/

    }





























    /*protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void selectImageToShareFacebook() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(GameEnd.this);
        builder.setTitle("Select profile Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String selectedImagePath = cursor.getString(column_index);
        Bitmap thumbnail;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        thumbnail = BitmapFactory.decodeFile(selectedImagePath, options);
        ShareDialog(thumbnail);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShareDialog(thumbnail);
    }

    public void ShareDialog(Bitmap imagePath){
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(imagePath)
                .setCaption("StudyTutorial")
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(content);
    }*/

}