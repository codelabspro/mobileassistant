package com.codesdk.mobileassistant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Helper class for displaying images retrieved asynchronously from Internet locations.
 */
class ImageUrlViewBinder implements SimpleAdapter.ViewBinder {
  Set<Integer> imageViewIds;

  /**
   * @constructor
   *
   * @param imageViewIds set of resource ids of ImageViews that will have images downloaded from
   *        Internet
   */
  ImageUrlViewBinder(Set<Integer> imageViewIds) {
    this.imageViewIds = imageViewIds;
  }

  /**
   * @constructor
   *
   * @param imageViewId the resource id of ImageView that will have images downloaded from Internet
   */
  ImageUrlViewBinder(int imageViewId) {
    imageViewIds = new HashSet<Integer>();
    imageViewIds.add(imageViewId);
  }


  /**
   * If the view has been configured to display images downloaded from Internet, the method
   * interprets the data argument as an Url, downloads the image from that Url asynchronously and
   * binds it to the specified view.
   */
  @Override
  public boolean setViewValue(View view, Object data, String textRepresentation) {
    if (!imageViewIds.contains(view.getId())) {
      return false;
    }

    // TODO(ggogol): Implement local caching on the device
    String uri = (String) data;
    ImageView imageView = (ImageView) view;
    // new DownloadImageAsyncTask(imageView, R.drawable.ic_launcher).execute(uri);
    return true;
  }
}

