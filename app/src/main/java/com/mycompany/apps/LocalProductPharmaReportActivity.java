package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import Adapter.ReportLocalProductPharmaListAdapter;
import Adapter.ReportLocalProductPharmaSearchAdapter;
import Pojo.ReportLocalProductPharmaModel;


public class LocalProductPharmaReportActivity extends Activity {

    ListView listview;
    AutoCompleteTextView autoCompleteTextView;
    private TextWatcher mTextWatcher;
    ReportLocalProductPharmaListAdapter localproductListAdapter;
    ArrayList<ReportLocalProductPharmaModel> searchlocalProductList;
    ArrayList<ReportLocalProductPharmaModel> arraylocalProductList;
    ReportLocalProductPharmaSearchAdapter searchlocalProductAdapter;
    ArrayList<ReportLocalProductPharmaModel>GetAllPharmaLocalProduct;
    ActionBar actionBar;
    String ActiveType[];
    Spinner Active;
    String SpinValue;
    ArrayAdapter<String> adapterActiveType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localprod_pharma_report);
        final DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        listview = (ListView) findViewById(R.id.lv_LocalProdReport);
        Log.e("***********Lt1*******", listview.toString());
        autoCompleteTextView = (CustomAuto) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        Active=(Spinner)findViewById(R.id.activeLocalProdPharma);
        GetAllPharmaLocalProduct=helper.getLocalProductPharmaForReport();
        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
        localproductListAdapter = new ReportLocalProductPharmaListAdapter( GetAllPharmaLocalProduct ,LocalProductPharmaReportActivity.this);
        listview.setAdapter(localproductListAdapter);

        ActiveType = getResources().getStringArray(R.array.active_Status_for_Report);
        adapterActiveType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ActiveType);
        adapterActiveType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Active.setAdapter(adapterActiveType);
        Active.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinValue = Active.getSelectedItem().toString();
                if (SpinValue.equals("Y"))
                {//rahul
                    GetAllPharmaLocalProduct=new ArrayList<ReportLocalProductPharmaModel>();
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    localproductListAdapter = new ReportLocalProductPharmaListAdapter( GetAllPharmaLocalProduct ,LocalProductPharmaReportActivity.this);
                    listview.setAdapter(localproductListAdapter);


                    arraylocalProductList = helper.getLocalProductPharmaReportforActive(SpinValue);
                    if (localproductListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        localproductListAdapter = new ReportLocalProductPharmaListAdapter( arraylocalProductList , LocalProductPharmaReportActivity.this);
                        listview.setAdapter(localproductListAdapter);
                    }
                   /* distributorListAdapter.addProductToList(arrayDistributorList.get(0));
                    Log.i("&&&&&&&&", "Adding " + arrayDistributorList.get(0) + " to Product List..");*/
                    localproductListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }else if(SpinValue.equals("N"))
                {//rahul
                    GetAllPharmaLocalProduct=new ArrayList<ReportLocalProductPharmaModel>();
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    localproductListAdapter = new ReportLocalProductPharmaListAdapter( GetAllPharmaLocalProduct ,LocalProductPharmaReportActivity.this);
                    listview.setAdapter(localproductListAdapter);


                    arraylocalProductList = helper.getLocalProductPharmaReportforActive(SpinValue);
                    if (localproductListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        localproductListAdapter = new ReportLocalProductPharmaListAdapter( arraylocalProductList , LocalProductPharmaReportActivity.this);
                        listview.setAdapter(localproductListAdapter);
                    }
                    for (ReportLocalProductPharmaModel prod:arraylocalProductList){
                        localproductListAdapter.addProductToList(prod);
                    }
                    localproductListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }
                else
                {   arraylocalProductList = new ArrayList<ReportLocalProductPharmaModel>();
                    if (localproductListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        localproductListAdapter = new ReportLocalProductPharmaListAdapter( arraylocalProductList , LocalProductPharmaReportActivity.this);
                        listview.setAdapter(localproductListAdapter);
                    }

                    // distributorListAdapter.addProductToList(arrayDistributorList.get(0));
                    // Log.i("&&&&&&&&", "Adding " + arrayDistributorList.get(0) + " to Product List..");
                    localproductListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /******************************** vendor name selected from here********************************************************************************************/
        autoCompleteTextView.setThreshold(1);
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Debuging", "After text changed called ");
                if (autoCompleteTextView.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String userTypedString = autoCompleteTextView.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }
                searchlocalProductList = helper.getlocalProductPharmaName(userTypedString);
                if (searchlocalProductList != null) {
                    if (searchlocalProductAdapter == null) {
                        searchlocalProductAdapter = new ReportLocalProductPharmaSearchAdapter(LocalProductPharmaReportActivity.this, android.R.layout.simple_dropdown_item_1line, searchlocalProductList);
                        searchlocalProductAdapter.setList(searchlocalProductList);

                        autoCompleteTextView.setAdapter(searchlocalProductAdapter);
                        autoCompleteTextView.setThreshold(1);
                    } else {
                        searchlocalProductAdapter.setList(searchlocalProductList);
                        searchlocalProductAdapter.notifyDataSetChanged();
                    }

                }
            }
        };

        autoCompleteTextView.addTextChangedListener(mTextWatcher);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
                GetAllPharmaLocalProduct=new ArrayList<ReportLocalProductPharmaModel>();
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                localproductListAdapter = new ReportLocalProductPharmaListAdapter( GetAllPharmaLocalProduct ,LocalProductPharmaReportActivity.this);
                listview.setAdapter(localproductListAdapter);
                arraylocalProductList = helper.getLocalProdPharmaReport(Value);
                if (localproductListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    localproductListAdapter = new ReportLocalProductPharmaListAdapter(new ArrayList<ReportLocalProductPharmaModel>(), LocalProductPharmaReportActivity.this);
                    localproductListAdapter.setList(arraylocalProductList);
                    listview.setAdapter(localproductListAdapter);
                }
                else {
                    localproductListAdapter.setList(arraylocalProductList);
                    localproductListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_screen1, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i=new Intent(LocalProductPharmaReportActivity.this,MasterDataReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
