package com.example.cip.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class ShareScreenshot {
    private File imagePath;

    public ShareScreenshot() {
    }

    public Intent  getShareIntent(View rootView, Context context){
        Bitmap bitmap = takeScreenshot(rootView);
        saveBitmap(bitmap);
        return shareIt(context);
    }

    private Bitmap takeScreenshot(View rootView) {
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    private void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory()+File.separator + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private Intent shareIt(Context context) {
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider",imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        sharingIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        return sharingIntent;
    }

    public String getShareMesage() {
        return "Teilen über";
    }
}
