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

    private final String username;

    public TopicMessagesAdapter(@NonNull Context context, String username) {
        super(context, R.layout.view_topic_message);
        this.username = username;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_topic_message,
                    parent, false);
        }
        TextView messageTextView = convertView.findViewById(R.id.topic_message_text);
        messageTextView.setText(getItem(position).getMessage());
        TextView usernameTextView = convertView.findViewById(R.id.topic_message_username);
        String username = getItem(position).getUsername();
        if(this.username.equals(username)) {
            username = getContext().getString(R.string.username_you);
        }
        usernameTextView.setText(username);
        return convertView;
    }
}
