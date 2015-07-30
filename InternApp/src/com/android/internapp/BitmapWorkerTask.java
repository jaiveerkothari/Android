//package com.android.internapp;
//
//import java.lang.ref.WeakReference;
//
//import android.graphics.Bitmap;
//import android.os.AsyncTask;
//import android.widget.ImageView;
//
//class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
//    private final WeakReference<ImageView> imageViewReference;
//    private int data = 0;
//
//    public BitmapWorkerTask(ImageView imageView) {
//        // Use a WeakReference to ensure the ImageView can bea garbage collected
//        imageViewReference = new WeakReference<ImageView>(imageView);
//    }
//
//    // Decode image in background.
//    @Override
//    protected Bitmap doInBackground(Integer... params) {
//        data = params[0];
//        return decodeSampledBitmapFromResource(getResources(), data, 100, 100));
//    }
//
//    // Once complete, see if ImageView is still around and set bitmap.
//    @Override
//    protected void onPostExecute(Bitmap bitmap) {
//        if (imageViewReference != null && bitmap != null) {
//            final ImageView imageView = imageViewReference.get();
//            if (imageView != null) {
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }
//}
