package com.codepath.shivagss.instapop;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sandeep on 9/14/2014.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    class Holder {
        ImageView image;
        TextView caption;
    }

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, R.layout.item_photo, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);

        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            holder = new Holder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.caption = (TextView) convertView.findViewById(R.id.caption);
            convertView.setTag(holder);
        }

        holder = (Holder) convertView.getTag();

        holder.caption.setText(Html.fromHtml("<b>"+photo.username+"</b>" + " " +photo.caption));
        holder.image.setImageResource(0);
        holder.image.getLayoutParams().height = photo.imageHeight;
        Picasso.with(getContext()).load(photo.getImageURL()).into(holder.image);
        return convertView;
    }
}
