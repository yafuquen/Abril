package com.yafuquen.abril.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yafuquen.abril.R;
import com.yafuquen.abril.domain.model.TopicMessage;

public class TopicMessagesAdapter extends ArrayAdapter<TopicMessage> {

    public TopicMessagesAdapter(@NonNull Context context) {
        super(context, R.layout.layout_topic_message);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_topic_message,
                    parent, false);
        }
        TextView nameTextView = convertView.findViewById(R.id.topic_message_text);
        nameTextView.setText(getItem(position).getMessage());
        return convertView;
    }
}
