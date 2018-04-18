package com.yafuquen.abril.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yafuquen.abril.domain.exception.TopicsException;
import com.yafuquen.abril.domain.model.Topic;
import com.yafuquen.abril.domain.repository.TopicsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Implementation for topic repository.
 *
 * @author yafuquen
 */
class TopicsRepositoryImpl implements TopicsRepository {

    @Inject
    public TopicsRepositoryImpl() {
        // Constructor for injection
    }

    @Override
    public Observable<Topic> getAvailableTopics() {
        return Observable.create(emitter -> FirebaseDatabase.getInstance()
                .getReference()
                .child("topics")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            emitter.onNext(data.getValue(Topic.class));
                        }
                        emitter.onComplete();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.tryOnError(new TopicsException());
                    }
                }));
    }
}
