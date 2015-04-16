package com.example.kitten.blog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by kitten on 4/15/15.
 */
public class PostAdapter extends ArrayAdapter<Post> {

    Context context;
    int layoutResourceId;
    Post[] posts;

    public PostAdapter(Context context, int layoutResourceId, Post[] posts) {
        super(context, layoutResourceId, posts);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.posts = posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TextView subject;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            subject = (TextView) row.findViewById(R.id.subjectTextView);

            Post post = posts[position];
            subject.setText(post.subject);
        }

        return row;
    }
}
