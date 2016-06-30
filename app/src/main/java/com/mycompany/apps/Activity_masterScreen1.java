package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by w7 on 1/27/2016.
 */
public class Activity_masterScreen1 extends Activity implements View.OnClickListener {
    ActionBar actionBar;
    Button mPlayRemoteVideoButton;

    int RESULT_LOAD_VIDEO_LOCAL = 2;
    int RESULT_LOAD_VIDEO_REMOTE = 4;


    Button mSelectLocalVideoButton;
    Button mSelectRemoteVideoButton;
    Button mPlayLocalVideoButton;

    Button mRemoteStopButton;
    TextView mLocalPathTextView;
    TextView mRemotePathTextView;
    Uri mLocalVideoUri;
    Uri mRemoteVideoUri;
    TextView newstre;
    TextView textadstream;
    ImageView sponcerlogo;
    Intent mLocalData;
    Intent mRemoteData;

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_screen1);

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        ImageButton imageButton=(ImageButton)findViewById(R.id.mastermanagement_button);
        ImageButton imageButton2=(ImageButton)findViewById(R.id.sales_button);
        ImageButton imageButton3=(ImageButton)findViewById(R.id.purchase_button);
        ImageButton imageButton4=(ImageButton)findViewById(R.id.report_button);
        ImageButton imageButton6=(ImageButton)findViewById(R.id.systemsupport_button);
       // ImageButton imageButton7=(ImageButton)findViewById(R.id.Internetorderbutton);
        ImageButton imageButton8=(ImageButton)findViewById(R.id.systemsetting_button);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        imageButton6.setOnClickListener(this);
       // imageButton7.setOnClickListener(this);
        imageButton8.setOnClickListener(this);



    }

//    @Override
//    public void onBackPressed() {
//        // nothing to do here
//        // … really
//    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mastermanagement_button:

                Intent intent = new Intent(this, Activity_masterScreen2.class);
                startActivity(intent);
                break;
            case R.id.sales_button:
                Intent intent1 = new Intent(this, ActivitySales.class);
                startActivity(intent1);
                break;
            case R.id.purchase_button:
                Intent intent2 = new Intent(this, Activitypurchase.class);
                startActivity(intent2);
                break;
            case R.id.report_button:
                Intent intent3 = new Intent(this, ActivityLoyalityCust.class);
                startActivity(intent3);
                break;
            case R.id.systemsupport_button:
                Intent intent4 = new Intent(this,ActivityMainMaintainence.class);
                startActivity(intent4);
                break;
//            case R.id.Internetorderbutton:
//                Intent intent5 = new Intent(this,BiilinternetActivity.class);
//                startActivity(intent5);
//                break;
            case R.id.systemsetting_button:
                Intent intent6 = new Intent(this,ActivityLoyality.class);
                startActivity(intent6);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_screen1, menu);
        return true;

    }

    @Override
    protected void onStart() {
        super.onStart();

        remoteMediaPlay();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            Uri videoUri = data.getData();
            String videoPath = getPathFromUri(videoUri);
            if (requestCode == RESULT_LOAD_VIDEO_LOCAL)
            {
                mPlayLocalVideoButton.setVisibility(View.VISIBLE);
                mPlayLocalVideoButton.setClickable(true);
                mLocalPathTextView.setText(videoPath);
                mLocalVideoUri = videoUri;
                mLocalData = data;
            }
            else if (requestCode == RESULT_LOAD_VIDEO_REMOTE)
            {
                mPlayRemoteVideoButton.setVisibility(View.VISIBLE);
                mPlayRemoteVideoButton.setClickable(true);

            }
        }
    }


    public void OnClickPlayRemoteVideo(View view)
    {
        Intent serviceIntent = new Intent(this, RemoteVideoService.class);
        serviceIntent.putExtra(RemoteVideoService.URI, mRemoteVideoUri);
        // serviceIntent.getParcelableExtra(RemoteVideoService.adnewstream);
        //serviceIntent.putExtra(RemoteVideoService.URI1,newstre);
        startService(serviceIntent);

    }


    public String getPathFromUri(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            // Close every kind of system dialog
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    }


    public void remoteMediaPlay(){


        Button btn_media=(Button)findViewById(R.id.buttonPlayRemoteVideo);

        btn_media.performClick();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
