package com.kimcheese.kimchidomainappserver.core.repository;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.util.List;

public interface BaseRepository<T> {
    public List<QueryDocumentSnapshot> findByField(String fieldName, Object value) throws Exception;
}
