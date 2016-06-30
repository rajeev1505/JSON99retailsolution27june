package com.mycompany.apps;

/**
 * Created by rspl-rajeev on 5/5/16.
 */
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.view.Display;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class RemoteVideoService extends Service
{
    RemoteVideoPresentation presentation;
    private DisplayManager mDisplayManager;
    public static final String URI = "URI";

    public static final String URI1 = "URI1";

    public static final String URI2 = "URI2";

    public static final String ADNEWSSTREAM= "adnewstream";

    public static final String strcustomerid="check1";

    public static final String strimage="image";

    public static final String strimage12="image22";

    public static final String stweweer="sdfgvyus";

    public static final String BILL="sdfgvyusklxvv";


    @Override
    public IBinder onBind(Intent intent)
    {
        return new Binder();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mDisplayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
    }

    @Override
    public void onDestroy()
    {
        if (presentation != null){
            presentation.cancel();
        }
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        CharSequence text = getText(R.string.app_name);
        Intent startApp = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, startApp, 0);
        Notification.Builder bld = new Notification.Builder(this);
        Notification not = bld
                .setSmallIcon(R.drawable.hemas)
                .setContentIntent(pendingIntent)
                .setContentTitle(text)
                .build();

        startForeground(1, not);

        Uri video = (Uri)intent.getParcelableExtra(URI);

      //  Uri video1 = (Uri)intent.getParcelableExtra(URI1);

       // Uri video2 = (Uri)intent.getParcelableExtra(URI2);

        TextView newStream=(TextView)intent.getParcelableExtra(ADNEWSSTREAM);

        ImageView imageView1=(ImageView)intent.getParcelableExtra(strimage);

        HorizontalScrollView horizontalScrollView123=(HorizontalScrollView)intent.getParcelableExtra(stweweer);

        LinearLayout linearLayouter=(LinearLayout)intent.getParcelableExtra(strimage12);

        TextView customerid=(TextView)intent.getParcelableExtra(strcustomerid);

        ListView Bill=(ListView)intent.getParcelableExtra(BILL);

        //Select Display
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        for (Display display : displays)
        {
            //Set up Presentation class and show
            presentation = new RemoteVideoPresentation(this, display, video,newStream);
            presentation.show();
        }

        return START_NOT_STICKY;
    }
}

