package com.example.artests.l20listactivity;

import android.app.ListActivity;
import android.os.Bundle;
/*import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;*/
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener{
    private ArrayList<String> mCatNamesArrayList;
    private ArrayList<String> mMonthArray;
    private ArrayList<String> mDayOfWeekArray;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> mAdapterMonths;
    private ArrayAdapter<String> mAdapterDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.content_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //mCatNamesArrayList = createArrayNames("catNamesArray");
        mMonthArray = createArrayNames("monthArray");
        mDayOfWeekArray = createArrayNames("dayOfWeekArray");
        //mAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mCatNamesArrayList);
        mAdapterMonths=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mMonthArray);
        mAdapterDays=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDayOfWeekArray);
        setListAdapter(mAdapterMonths);
        getListView().setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //Toast.makeText(getApplicationContext(),getString(R.string.textChoose)+" "+l.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
        if (getListAdapter()==mAdapterMonths){
            setListAdapter(mAdapterDays);
            mAdapterDays.notifyDataSetChanged();
        }
        else{
            setListAdapter(mAdapterMonths);
            mAdapterMonths.notifyDataSetChanged();
        }
    }

    private ArrayList<String> createArrayNames(String nameArray){
        int res;
        switch (nameArray){
            case("catNamesArray"):res=R.array.catNamesArray;
                break;
            case("monthArray"):res=R.array.monthArray;
                break;
            case("dayOfWeekArray"):res=R.array.dayOfWeekArray;
                break;
            default:res=R.array.catNamesArray;
        }
        return new ArrayList<>(Arrays.asList(getResources().getStringArray(res)));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        if(getListAdapter()==mAdapterMonths) {
            mAdapterMonths.remove(selectedItem);
            mAdapterMonths.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), selectedItem + " deleted.", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
