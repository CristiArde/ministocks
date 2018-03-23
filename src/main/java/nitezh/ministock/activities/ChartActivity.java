package nitezh.ministock.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import nitezh.ministock.R;

/**
 * Created by nicholasfong on 2018-02-21.
 */

public class ChartActivity extends Activity {
    // Public variables
    public static int mAppWidgetId = 0;
    MyData myData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        URL url = null;
        Bitmap bmImg = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bonobo_chart_layout);
        Spanned html = Html.fromHtml("Sample Chart View <br /><br />");
        TextView text = (TextView) findViewById(R.id.chart_text);
        text.setText(html);
        new ImageSnatcher((ImageView) findViewById(R.id.chart_img)).execute("https://www.codeproject.com/KB/graphics/zedgraph/example_1.png");
    }

    @Override
    public void onStop() {
        super.onStop();
        finish();
    }
}

class ImageSnatcher extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public ImageSnatcher(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
