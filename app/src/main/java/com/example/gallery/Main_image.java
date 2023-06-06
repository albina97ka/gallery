package com.example.gallery;

import android.os.Parcel;
import android.os.Parcelable;
public class Main_image implements Parcelable {
    private final String title;
    private final String path;

    public Main_image(String title, String path) {
        this.title = title;
        this.path = path;
    }

    protected Main_image(Parcel in) {
        title = in.readString();
        path = in.readString();
    }

    public static final Creator<Main_image> CREATOR = new Creator<Main_image>() {
        @Override
        public Main_image createFromParcel(Parcel in) {
            return new Main_image(in);
        }

        @Override
        public Main_image[] newArray(int size) {
            return new Main_image[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(path);
    }
}