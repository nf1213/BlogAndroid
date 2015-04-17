package com.example.kitten.blog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kitten on 4/15/15.
 */
public class Post implements Parcelable{
    int id;
    String subject;
    String content;
    String blogImageUrl;
    String instagramLink;

    public Post(int id, String subject, String content, String blogImageUrl, String instagramLink) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.blogImageUrl = blogImageUrl;
        this.instagramLink = instagramLink;
    }

    protected Post(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        content = in.readString();
        blogImageUrl = in.readString();
        instagramLink = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeString(content);
        dest.writeString(blogImageUrl);
        dest.writeString(instagramLink);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
