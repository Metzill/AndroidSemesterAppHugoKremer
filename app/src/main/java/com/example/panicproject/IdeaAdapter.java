package com.example.panicproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.ViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    IdeaAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public void swapCursor (Cursor newCursor){
        if(mCursor!= null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if(!mCursor.moveToPosition(position)) {
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME));
        String description = mCursor.getString(mCursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_DESCRIPTION));
        long id = mCursor.getLong(mCursor.getColumnIndex(ItemContract.ItemEntry._ID));

        viewHolder.name.setText(name);
        viewHolder.description.setText(description);
        viewHolder.itemView.setTag(id);
    }

    public int getItemCount() {
        return mCursor.getCount();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            description = itemView.findViewById(R.id.itemDescription);
        }
    }
}
