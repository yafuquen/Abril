package com.yafuquen.abril.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.model.TopicMessage;
import com.yafuquen.abril.domain.exception.TopicMessagesException;
import com.yafuquen.abril.domain.model.User;
import com.yafuquen.abril.domain.repository.TopicMessagesRepository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Implementation for topic messages repository.
 *
 * @author yafuquen
 */
public class TopicMessagesRepositoryImpl implements TopicMessagesRepository {

    private ValueEventListener valueEventListener;

    @Inject
    public TopicMessagesRepositoryImpl() {
        // Constructor for injection
    }

    @Override
    public Observable<TopicMessage> getMessagesForTopic(final Topic topic) {
        return Observable.create(new ObservableOnSubscribe<TopicMessage>() {
            @Override
            public void subscribe(final ObservableEmitter<TopicMessage> emitter) throws Exception {
                valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            emitter.onNext(data.getValue(TopicMessage.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.tryOnError(new TopicMessagesException());
                    }
                };
                FirebaseDatabase.getInstance().getReference().child("messages").child(
                        topic.getName()).addValueEventListener(valueEventListener);
            }
        });

    }

    @Override
    public void stopTopicMessagesObservable() {
        FirebaseDatabase.getInstance().getReference().removeEventListener(valueEventListener);
    }

    @Override
    public Observable<Void> sendMessage(final Topic topic, final User user, final String message) {
        return Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(final ObservableEmitter<Void> emitter) throws Exception {
                Map<String, Object> values = new HashMap<>();
                values.put("message", message);
                values.put("username", user.getUsername());
                FirebaseDatabase.getInstance().getReference().child("messages").child(
                        topic.getName()).updateChildren(values,
                        new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError,
                                    DatabaseReference databaseReference) {
                                if (databaseError == null) {
                                    emitter.onNext(null);
                                    emitter.onComplete();
                                } else {
                                    emitter.tryOnError(new TopicMessagesException());
                                }
                            }
                        });
            }
        });
    }
}
