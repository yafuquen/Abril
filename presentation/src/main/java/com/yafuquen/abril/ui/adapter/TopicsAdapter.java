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
import com.yafuquen.abril.domain.model.Topic;

/**
 * Adapter for showing the topics.
 *
 * @author yafuquen
 */
public class TopicsAdapter extends ArrayAdapter<Topic> {

    public TopicsAdapter(@NonNull Context context) {
        super(context, R.layout.layout_topic);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_topic, parent,
                    false);
        }
        TextView nameTextView = convertView.findViewById(R.id.topic_name);
        nameTextView.setText(getItem(position).getName());
        return convertView;
    }
}
