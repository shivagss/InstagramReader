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

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sandeep on 9/14/2014.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    class Holder {
        ImageView profilePic;
        TextView username;
        TextView submittedTime;
        ImageView image;
        TextView caption;
        TextView likes;
    }

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, R.layout.item_photo, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(getCount() <= position) return convertView;

        InstagramPhoto photo = getItem(position);

        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            holder = new Holder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.caption = (TextView) convertView.findViewById(R.id.caption);
            holder.profilePic = (ImageView) convertView.findViewById(R.id.profilepic);
            holder.username = (TextView) convertView.findViewById(R.id.username);
            holder.submittedTime = (TextView) convertView.findViewById(R.id.submittedTime);
            holder.likes = (TextView) convertView.findViewById(R.id.likes);
            convertView.setTag(holder);
        }

        holder = (Holder) convertView.getTag();

        holder.username.setText(photo.username);
        Calendar submittedTimeInstance = Calendar.getInstance();
        submittedTimeInstance.setTimeInMillis(Long.parseLong(photo.getSubmittedTime()));
        Date submittedTime = submittedTimeInstance.getTime();
        long diffInMs = (new Date(System.currentTimeMillis()).getTime() / 1000) - submittedTime.getTime();

        long diffInHour = TimeUnit.SECONDS.toHours(diffInMs);

        holder.submittedTime.setText(diffInHour+"h");
        holder.profilePic.setImageResource(0);
        Picasso.with(getContext()).load(photo.getProfilePicURL()).into(holder.profilePic);

        holder.likes.setText(getContext().getString(R.string.likes, photo.likes));
        holder.caption.setText(Html.fromHtml("<b>"+photo.username+"</b>" + " " +photo.caption));
        holder.image.setImageResource(0);
        Picasso.with(getContext()).load(photo.getImageURL()).into(holder.image);

        return convertView;
    }
}
