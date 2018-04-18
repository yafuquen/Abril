package com.yafuquen.abril.data.repository;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.yafuquen.abril.domain.exception.TopicMessagesException;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.TopicMessagesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implementation for topic messages repository.
 *
 * @author yafuquen
 */
public class TopicMessagesRepositoryImpl implements TopicMessagesRepository {

    private ChildEventListener childEventListener;

    @Inject
    public TopicMessagesRepositoryImpl() {
        // Constructor for injection
    }

    @Override
    public Observable<TopicMessage> loadTopicMessages(final Topic topic) {
        if (childEventListener != null) {
            stopObservingMessages(topic);
        }
        return Observable.create(emitter -> {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    emitter.onNext(dataSnapshot.getValue(TopicMessage.class));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            FirebaseDatabase.getInstance().getReference().child("messages").child(
                    topic.getName()).addChildEventListener(childEventListener);
            emitter.setCancellable(() -> stopObservingMessages(topic));
        });

    }

    @Override
    public Observable<Void> sendMessage(final Topic topic, final User user, final String message) {
        return Observable.create(emitter -> {
            TopicMessage topicMessage = new TopicMessage();
            topicMessage.setMessage(message);
            topicMessage.setUsername(user.getUsername());
            FirebaseDatabase.getInstance().getReference().child("messages").child(
                    topic.getName()).push().setValue(topicMessage,
                    (databaseError, databaseReference) -> {
                        if (databaseError == null) {
                            emitter.onNext(null);
                            emitter.onComplete();
                        } else {
                            emitter.tryOnError(new TopicMessagesException());
                        }
                    });
        });
    }

    private void stopObservingMessages(Topic topic) {
        FirebaseDatabase.getInstance().getReference().child("messages").child(
                topic.getName()).removeEventListener(childEventListener);
    }
}
