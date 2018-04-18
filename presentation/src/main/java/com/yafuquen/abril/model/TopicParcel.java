package com.yafuquen.abril.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.yafuquen.abril.domain.model.Topic;

/**
 * Topic parcelable.
 *
 * @author yafuquen
 */
public class TopicParcel implements Parcelable {

    private String name;

    public TopicParcel(Topic topic) {
        this.name = topic.getName();
    }

    private TopicParcel(Parcel in) {
        this.name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Creator<TopicParcel> CREATOR = new Creator<TopicParcel>() {
        @Override
        public TopicParcel createFromParcel(Parcel in) {
            return new TopicParcel(in);
        }

        @Override
        public TopicParcel[] newArray(int size) {
            return new TopicParcel[size];
        }
    };
}
