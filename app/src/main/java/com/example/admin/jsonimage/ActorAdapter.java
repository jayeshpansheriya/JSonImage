package com.example.admin.jsonimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/9/2016.
 */
public class ActorAdapter extends ArrayAdapter<Actor> {

    ArrayList<Actor> list;
    LayoutInflater vi;
    int Resources;
    ViewHolder holder;

    public ActorAdapter(Context context, int resource, List<Actor> objects) {
        super(context, resource, objects);
        vi= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resources=resource;
        list= (ArrayList<Actor>) objects;



    }


    static class ViewHolder
    {
        public ImageView imageview;
        public TextView tvName;
        public TextView tvDescription;
        public TextView tvDOB;
        public TextView tvCountry;
        public TextView tvHeight;
        public TextView tvSpouse;
        public TextView tvChildren;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if (v==null)
        {
            holder=new ViewHolder();
            v = vi.inflate(Resources, null);
            holder.imageview = (ImageView) v.findViewById(R.id.imageView);
            holder.tvName = (TextView) v.findViewById(R.id.textView);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);
            holder.tvDOB = (TextView) v.findViewById(R.id.tvDateOfBirth);
            holder.tvCountry = (TextView) v.findViewById(R.id.tvCountry);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);
            holder.tvSpouse = (TextView) v.findViewById(R.id.tvSpouse);
            holder.tvChildren = (TextView) v.findViewById(R.id.tvChildren);
            v.setTag(holder);

        }
        else {
            holder= (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.a);

            new DownloadImage(holder.imageview).execute(list.get(position).getImage());
            holder.tvName.setText(list.get(position).getName());
        holder.tvDescription.setText(list.get(position).getDescription());
        holder.tvDOB.setText("B'day: " + list.get(position).getDob());
        holder.tvCountry.setText(list.get(position).getCountry());
        holder.tvHeight.setText("Height: " + list.get(position).getHeight());
        holder.tvSpouse.setText("Spouse: " + list.get(position).getSpouse());
        holder.tvChildren.setText("Children: " + list.get(position).getChildren());
        return v;
    }
}


class DownloadImage extends AsyncTask<String,Void,Bitmap>
{
    ImageView iv1;

    public DownloadImage(ImageView iv1) {
        this.iv1 = iv1;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        String url=params[0];
        Bitmap micon=null;
        try {
            InputStream stream=new URL(url).openStream();
            micon= BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return micon;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

    iv1.setImageBitmap(bitmap);
    }
}