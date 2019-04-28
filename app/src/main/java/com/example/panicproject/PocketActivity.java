package com.example.panicproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PocketActivity extends AppCompatActivity {

    private RecyclerView mIdeas;
    private IdeaAdapter mIdeaAdapter;
    private Button mButton;
    private SQLiteDatabase mDatabase;
    private EditText mEdtName;
    private EditText mEdtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pocket_main);

        mEdtName = findViewById(R.id.edt_name);
        mEdtDesc = findViewById(R.id.edt_desc);
        mButton = findViewById(R.id.btnAdmit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to the next activity we go
                Intent FireBaseIntent = new Intent(getBaseContext(), FireBaseActivity.class);
                startActivity(FireBaseIntent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDB();
            }
        });

        ItemDBHelper dbHelper = new ItemDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mIdeas = findViewById(R.id.rv);
        mIdeas.hasFixedSize();
        mIdeas.setLayoutManager(new LinearLayoutManager(this));
        mIdeaAdapter = new IdeaAdapter(this, getAllItems());
        mIdeas.setAdapter(mIdeaAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped( RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(mIdeas);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sqlite, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        if (item.getItemId() == R.id.delete_sqlite) {
            mDatabase.delete(ItemContract.ItemEntry.TABLE_NAME, null, null);
            mIdeaAdapter.swapCursor(getAllItems());
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeItem (long id) {
        mDatabase.delete(ItemContract.ItemEntry.TABLE_NAME,
                ItemContract.ItemEntry._ID + "=" + id,
                null);
        mIdeaAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                ItemContract.ItemEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private void addToDB() {
        if(mEdtName.getText().toString().trim().length() == 0 || mEdtDesc.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(),"You left one of the field empty D:<",Toast.LENGTH_SHORT).show();
            return;
        }

        String name = mEdtName.getText().toString();
        String description = mEdtDesc.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(ItemContract.ItemEntry.COLUMN_NAME, name);
        cv.put(ItemContract.ItemEntry.COLUMN_DESCRIPTION, description);

        mDatabase.insert(ItemContract.ItemEntry.TABLE_NAME, null, cv);
        mIdeaAdapter.swapCursor(getAllItems());

        mEdtName.getText().clear();
        mEdtDesc.getText().clear();
    }
}
