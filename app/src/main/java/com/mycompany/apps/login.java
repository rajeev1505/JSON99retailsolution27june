
package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.godbtech.sync.GBrowserNotifyMessageEvt;
import com.godbtech.sync.GNativeSync;
import com.godbtech.sync.GSyncServerConfig;
import com.godbtech.sync.GSyncStatusEvt;
import com.godbtech.sync.GSyncable;
import com.godbtech.sync.GUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jimmy on 2/4/2016.
 */
public class login extends Activity {


    DBhelper db =   new DBhelper(this);

Button login;
    EditText user;
    EditText password;
    ActionBar actionBar;
    public static final String GCM_PROJECT_ID = "407176891585";//only if you need GCM notifications
    Bundle syncDataBundle = null;
    private boolean syncInProgress = false;
    private boolean didSyncSucceed = false;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
//    private GSyncStatusHandler gSyncStatusHandler = new GSyncStatusHandler();
//    private GBrowserNotifyHandler gBrowserNotifyHandler = new GBrowserNotifyHandler();
//    private GSyncResultHandler gSyncResultNotifyHandler = new GSyncResultHandler();
//    private GSyncItemStatusHandler gSyncItemStatusHandler = new GSyncItemStatusHandler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_login);


       /* startService(new Intent(this, SyncService.class));*/

        actionBar=getActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
        login=(Button)findViewById(R.id.loginbutton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);

                user=(EditText)findViewById(R.id.username);
                password=(EditText)findViewById(R.id.userpassword);

                String password1 = password.getText().toString();
                String username = user.getText().toString();
                final Long Value = System.currentTimeMillis();
                final String resval = Long.toString(Value);
                db.getData();
                db.activestore();
//                db.SavePdfDetailForInstallation(resval);
                if (db.validateUser(username,password1))
                {

                    //Intent i =new Intent(getApplicationContext().this,test.class);
                     //
                    Intent i=new Intent(getApplicationContext(),Activity_masterScreen1.class);
                    startActivity(i);
//                    loadSyncLibrary();
//                    doSync(syncDataBundle);




                   password.setText("");
                    user.setText("");
               }
                else
                {
                    Toast.makeText(getBaseContext(), "Wrong username/password", Toast.LENGTH_LONG).show();
                }
            }
                });


//        Button Cancel = (Button) findViewById(R.id.Cancelbutton);
//        Cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), GMainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus) {
            // Close every kind of system dialog
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (blockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


//    protected void onStart()
//    {
//        syncDataBundle = getSyncBudle();//get sync parameters as bundle
//        //these can be obtained from a form
//        //setSyncParamView(syncDataBundle);//display the parameters
//        super.onStart();
//    }
//
//
//
//
//    protected  Bundle getSyncBudle()
//    {
//        Bundle b = new Bundle();
//
//        //db name, this will be available in that package
//        //do not change this, this is the only directory where this activity can write to
//        //specifying another directory will cause the sync to fail
//
//        String dbName = "/data/data/" + GMainActivity.class.getPackage().getName() + "/sync.bdb.db";
//
//      /*
//      //if you are using encrypted SQLITE Sync agent use code below
//      //Note using this code on an unencrypted SQlite Sync agent will not work
//      //For obtaining SQLITE encrypted Sync Agent contact GoDB support
//      //start
//      String key     = "mykey";
//      //NOTE: Important, For demonstration purposes key is being hardcoded here,
//      //In production this key should not be available stored on the device
//      //or hardcoded into the application. How to handle key is outside the scope
//      //of this demo app.
//      //start SQLITE ENCRYPTION
//      String algorithm = "rc4";//RC4,AES128-OFB,AES258-OFB are currently Supported algorithms
//      boolean shouldKey = true;
//      if(Util.doesFileExist(dbName))
//      {
//         if(Util.isSqliteEncrypted(dbName))//if sqlite is already encrypted, lets rekey it
//         {
//            shouldKey = false;
//            dbName = dbName + ";rekey="+algorithm+":"+key;
//         }
//      }
//      if(shouldKey)
//         dbName = dbName + ";key="+algorithm+":"+key;//if sqlite is not yet created, or is not encrypted, lets key it
//      //end SQLITE ENCRYPTION
//       */
//        DBhelper mydb = new DBhelper(login.this);
//        ArrayList<String> value = mydb.getStoreid();
//        String filterstoreid = value.toString().replace("[", "").replace("]", "");
//        b.putString("dbName", dbName);
//
//        b.putString("syncServerUsername",  filterstoreid);//sync user name
//        //b.putString("syncServerPassword", "8776F108E247AB1E2B323042C049C266407C81FBAD41BDE1E8DFC1BB66FD267E");//SHA-256 sync server password
//        b.putString("syncServerPassword", GUtils.getSHADigest("SHA-256", "Admin@123"));//SHA-256 sync server password
//        //b.putString("syncServerPassword",    GUtils.getMD5Hash("mypassword"));//MD5 server password
//        b.putString("syncServerUserID",    "3");//if needed
//
//        b.putInt("syncMode",            GSyncServerConfig.SYNC_DELTA);//delta sync
//        b.putBoolean("logEnabled", true);//logs "http.log", "sync.log", "syncstat.log" will be available in
//        //folder /data/data/getClass().getPackage().getName()
//        //after synchronization
//        b.putString("syncServerIP", "52.76.28.14");//sync server ip/domain
//        //b.putString("syncServerIP",     "https://www.myserver.com");//sync server ip/domain if https is supported
//        //if https is supported please make sure its "https://" + servername
//
//        b.putInt("syncServerPort", 8080);//sync server http port
//        //b.putInt("syncServerPort",      443);//sync server https port if SSL is enabled
//
//        b.putString("syncServerBasePath", "/godbss/");//sync server basepath ex: http://www.yourdomain.com/godbss/, here godbss is the basepath
//
//        //socket parameters
//        b.putInt("sockConnectTimeoutMillis", 5000);//five seconds
//        b.putInt("sockSendTimeoutMillis",  30000);//30 seconds
//        b.putInt("sockRecvTimeoutMillis", 30000);//30 seconds
//
//        //proxy parameters if needed
//        b.putBoolean("proxyEnabled", false);
//        b.putString("httpProxy", "192.168.0.134");//only valid if proxyEnabled is set to true
//        b.putInt("httpProxyPort",        3128);//only valid if proxyEnabled is set to true
//
//        b.putString("d4s",                 "RS_Store");//D4S
//
//        //b.putString("chunkedTableList",  "meap_order_item");//name of the table which you want to download in chunks
//        //b.putInt("maxRecChunkSize",     10);//chunk size in number of records
//
//        b.putString("gcmProjectID",       GCM_PROJECT_ID);//only if you need GCM notifications
//
//        return b;
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        switch(requestCode)
//        {
//            case GoSyncActivity.INTENT_CODE:
//                if(resultCode == RESULT_OK)
//                {
//                    boolean b = data.getBooleanExtra("syncstat", false);
//                    if(b)
//                    {
//                        Toast.makeText(this, "Sync Succeded", Toast.LENGTH_LONG).show();
//                    }
//                    else
//                        Toast.makeText(this, "Sync Failed", Toast.LENGTH_LONG).show();
//                }
//                break;
//        }
//    }
//
//
//    protected GSyncServerConfig getSyncConfigFromSyncIntent(Bundle syncDataBundle)
//    {
//        GSyncServerConfig gCfg = new GSyncServerConfig();
//        gCfg.setSyncServerAddress(syncDataBundle.getString("syncServerIP"));// set SyncServer Address, can be IP
//        gCfg.setSyncServerPort(syncDataBundle.getInt("syncServerPort", 80));// set SyncServer Port
//        gCfg.setSyncServerBasePath(syncDataBundle.getString("syncServerBasePath"));// set Base path
//        gCfg.setSyncServerUsername(syncDataBundle.getString("syncServerUsername"));
//        gCfg.setSyncServerPassword(syncDataBundle.getString("syncServerPassword"));
//        gCfg.setSyncServerUserID(syncDataBundle.getString("syncServerUserID"));
//        gCfg.setLogEnabled(true);//enable logging
//        gCfg.setDBName(syncDataBundle.getString("dbName"));//db name
//        gCfg.setSyncMode(syncDataBundle.getInt("syncMode", 1));// set Synchronization to full sync if missing
//        gCfg.setUseProxy(syncDataBundle.getBoolean("proxyEnabled", false));
//        gCfg.setHttpProxy(syncDataBundle.getString("httpProxy"));
//        gCfg.setHttpProxyPort(syncDataBundle.getInt("httpProxyPort", 8080));
//        gCfg.setSockConnectTimeoutMillis(syncDataBundle.getInt("sockConnectTimeoutMillis", 0));
//        gCfg.setSockSendTimeoutMillis(syncDataBundle.getInt("sockSendTimeoutMillis", 0));
//        gCfg.setSockRecvTimeoutMillis(syncDataBundle.getInt("sockRecvTimeoutMillis", 0));
//        gCfg.setD4S(syncDataBundle.getString("d4s"));
//        gCfg.setChunkedTableList(syncDataBundle.getString("chunkedTableList"));
//        gCfg.setMaxRecChunkSize(syncDataBundle.getInt("maxRecChunkSize", 0));
//        gCfg.setWithDSEnabled(syncDataBundle.getBoolean("withDsEnabled", false));//withds=1
//        //Log.d("GSync", "Syncing with " + gCfg.toString());
//        return gCfg;
//    }
//
//
//    public void doSync(Bundle syncDataBundle)
//    {
//        GNativeSync gNSync = GNativeSync.getNativeSyncSingleton();
//
//        gNSync.addSyncListener(this);// important register sync listener to recieve notifications
//        GSyncServerConfig gCfg = getSyncConfigFromSyncIntent(syncDataBundle);
//        String rootPath = syncDataBundle.getString("dbName");
//        if(rootPath==null || rootPath.lastIndexOf("/")<=0) return;
//        rootPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
//        extractPEMFile(getAssets(), rootPath);//extracts on every launch, you can change it to extract only if file isnt present.
//        //clearViews();
//        initDB(gCfg.getDBName(), gNSync._hasCodec());
//        gNSync.startSync(gCfg);
//    }
//
//// private void clearViews()
//// {
////    statusEditText.setText("");
//// }
//
//    @Override
//    public void syncStatusEvent(GSyncStatusEvt statEvt)
//    {
//        String msg = statEvt.getMsg();
//        if (msg == null)
//            msg = "";
//        Bundle b = new Bundle();
//        b.putString("syncstatusmsg",   msg);
//        b.putInt("status1",          statEvt.getTStatus1());
//        b.putInt("status2max",           statEvt.getStatus2Max());
//        b.putInt("status2",          statEvt.getStatus2());
//        b.putInt("status3max",           statEvt.getStatus3Max());
//        b.putInt("status3",          statEvt.getStatus3());
//        Message m = Message.obtain();
//        m.setData(b);
//
//        if(statEvt.getTStatus1() == 100)//100 sync failed 200 sync succeeded
//        {
//            syncInProgress = false;
//            didSyncSucceed = false;
//            //  Toast.makeText(this, "Sync failed", Toast.LENGTH_LONG).show();
//            Log.i("GSync", "Sync failed");
//            m.setTarget(gSyncResultNotifyHandler);
//        }
//        else
//        if(statEvt.getTStatus1() == 200)//sync succeeded
//        {
//            didSyncSucceed = true;
//            syncInProgress = false;
//          //  Toast.makeText(this, "Sync Success", Toast.LENGTH_LONG).show();
//            Log.i("GSync", "Sync Success");
//            m.setTarget(gSyncResultNotifyHandler);
//        }
//        else
//            m.setTarget(gSyncStatusHandler);
//        m.sendToTarget();
//        Log.i("GSync :: syncStatus", statEvt.toString());
//    }
//
//    @Override
//    public void browserNotifyEvent(GBrowserNotifyMessageEvt gNMsgEvt)
//    {
//        String msg = null;
//        Bundle b = new Bundle();
//        Message m = Message.obtain();
//        msg = gNMsgEvt.getNotifySrc() + ":" + gNMsgEvt.getMsgType();
//        m.setTarget(gBrowserNotifyHandler);
//        b.putString("browsernotifymsg", msg);
//        m.setData(b);
//        m.sendToTarget();
//        Log.i("GSync", gNMsgEvt.toString());
//    }
//
//    @Override
//    public void itemStatusEvent(String ItemName, String Msg)
//    {
//        Bundle b = new Bundle();
//        Message m = Message.obtain();
//        m.setTarget(gSyncItemStatusHandler);
//        b.putString("itemname", ItemName);
//        b.putString("msg",        Msg);
//        m.setData(b);
//        m.sendToTarget();
//        Log.i("GSync", "onItemStatus " + ItemName + " : " + Msg);
//    }
//
//
//    private void updateStatus(Message msg)
//    {
//        //if(status==null) return;
//        Bundle bundle = msg.getData();
//        int s1        = bundle.getInt("status1");
//        int s2        = bundle.getInt("status2");
//        int s2max  = bundle.getInt("status2max");
//        int s3        = bundle.getInt("status3");
//        int s3max  = bundle.getInt("status3max");
////
////    spb1.setMax(5);//currently fixed at 5
////    spb1.setProgress(s1);
////
////    //Log.i("GSync", "status1 " + s1);
////
////    spb2.setMax(s2max);
////    spb2.setProgress(s2);
////
////    spb3.setMax(s3max);
////    spb3.setProgress(s3);
//
//        System.out.println("status2max " + bundle.getInt("status2max"));
//        Log.i("GSync", "status2max " + bundle.getInt("status2max"));
//    }
//
//    private void updateNotify(String notification)
//    {
//        if(notification==null) return;
//        //currently not used
//    }
//
//    private void updateResult(String notification)
//    {
//        if(notification==null) return;
//        //currently not used
//       // Toast.makeText(this, "Sync Succeded", Toast.LENGTH_LONG).show();
//    }
//
//    private void updateItemStatus(String itemName, String msg)
//    {
//        if(itemName==null) itemName="";
//        if(msg==null) msg="";
////    String t = statusEditText.getText().toString();
////    if(t==null)t="";
////    statusEditText.append(msg + " " + itemName + "\n");
////    scrollView.post(new Runnable()
////    {
////       public void run()
////       {
////          scrollView.smoothScrollTo(0, statusEditText.getBottom());
////       }
////    });
//        Log.i("GSync", "->" + itemName + " " + msg + "\n");
//    }
//
//
//    class GSyncStatusHandler extends Handler
//    {
//        @Override
//        public void handleMessage(Message msg)
//        {
//            login.this.updateStatus(msg);
//        }
//    };
//
//    class GBrowserNotifyHandler extends Handler
//    {
//
//        @Override
//        public void handleMessage(Message msg)
//        {
//            login.this.updateNotify(msg.getData().getString("browsernotifymsg"));
//        }
//    };
//
//    class GSyncResultHandler extends Handler
//    {
//
//        @Override
//        public void handleMessage(Message msg)
//        {
//            login.this.updateResult(msg.getData().getString("syncstatusmsg"));
//        }
//    };
//
//    class GSyncItemStatusHandler extends Handler
//    {
//        @Override
//        public void handleMessage(Message msg)
//        {
//            login.this.updateItemStatus(msg.getData().getString("itemname"), msg.getData().getString("msg"));
//        }
//    };
//
//    @Override
//    public void onBackPressed()
//    {
//        if(syncInProgress)
//        {
//          //  Toast.makeText(this, "Sync in process.. Please wait for it to complete.", Toast.LENGTH_LONG).show();
//            return;
//        }
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("syncstat", didSyncSucceed);
//        setResult(RESULT_OK,   returnIntent);
//        finish();
//    }
//
//
//
//    /**
//     * extract ROOT.PEM to /data/data/yourpackage/ if it exists
//     * this is to support https based synchronization
//     */
//    void extractPEMFile(AssetManager mgr, String rootPath)
//    {
//        try
//        {
//            String[] filelist = mgr.list("");
//            for(String asset: filelist)
//            {
//                if(asset.equalsIgnoreCase("ROOT.pem"))
//                {
//                    File f = new File(rootPath, "ROOT.pem");
//                    FileOutputStream os = new FileOutputStream(f);
//                    byte[] buf = new byte[200 * 1024];
//                    int n;
//                    InputStream is = mgr.open(asset);
//                    while ((n = is.read(buf)) > 0)
//                        os.write(buf, 0, n);
//                    os.close();
//                    is.close();
//                }
//            }
//        }
//        catch(Exception e)
//        {
//        }
//    }
//    //this is just to create a blank database if doesnt exist
//    static void initDB(String dbName, int hasCodec)
//    {
//        String keyStr="";
//        com.godbtech.sql.database.sqlite.SQLiteDatabase db = null;
//        if(hasCodec==1)
//        {
//            if(dbName.indexOf(";")>0&&dbName.indexOf("=")>0)
//            {
//                keyStr="key='"+dbName.substring(dbName.indexOf("=")+1)+"'";
//                dbName = dbName.substring(0, dbName.indexOf(";"));
//            }
//            db = com.godbtech.sql.database.sqlite.SQLiteDatabase.openOrCreateDatabase(dbName, null);
//            if(keyStr.length()>0)
//            {
//                String pragma="PRAGMA "+keyStr;
//                db.execSQL(pragma);//set the key
//                Log.i("GSync", "testEncryptedDB:::pragma:::" + pragma);
//            }
//        }
//        else
//        {
//            if(dbName.indexOf(";")>0)
//                dbName=dbName.substring(0, dbName.indexOf(";"));
//            db = com.godbtech.sql.database.sqlite.SQLiteDatabase.openOrCreateDatabase(dbName, null);
//        }
//        com.godbtech.sql.database.sqlite.SQLiteStatement st = null;;
//        String res = null;
//        try
//        {
//            st = db.compileStatement("SELECT sqlite_version()");
//            res = st.simpleQueryForString();
//            //st.close();
//            //db.execSQL("CREATE TABLE IF NOT EXISTS t1(x, y)");
//            //db.execSQL("delete from t1");
//            //db.execSQL("INSERT INTO t1 VALUES (1, 2), (3, 4)");
//            //st = db.compileStatement("SELECT sum(x+y) FROM t1");
//            //res = st.simpleQueryForString();
//            Log.i("GSync", "sqlite_version():::" + res);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if(st!=null)
//                st.close();//dont forget to close the db
//            if(db!=null)
//                db.close();//dont forget to close the db
//        }
//    }
//
//    /**
//     * Load sync library.
//     */
//    private void loadSyncLibrary()
//    {
//        try
//        {
//            String trgLib = "gSyncDLL";
//            System.loadLibrary(trgLib);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            Log.e("GSync", e.getMessage());
//        }
//    }



}