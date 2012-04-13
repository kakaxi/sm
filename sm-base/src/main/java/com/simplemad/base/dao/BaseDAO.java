package com.simplemad.base.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;
import com.mongodb.DBCollection;
import com.mongodb.DBRef;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.simplemad.base.domain.BaseEntity;

@Repository
@SuppressWarnings("unchecked")
public final class BaseDAO {

	protected DatastoreImpl ds;
	
	/**
	 * Converts from a List<Key> to their id values
	 * 
	 * @param keys
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected <T> List<?> keysToIds(List<Key<T>> keys) {
		ArrayList ids = new ArrayList(keys.size() * 2);
		for (Key<T> key : keys)
			ids.add(key.getId());
		return ids;
	}
	
	/** The underlying collection for this DAO */
	public <T> DBCollection getCollection(Class<T> entityClazz) {
		return ds.getCollection(entityClazz);
	}
	
	public <T> Iterable<Key<T>> save(Iterable<T> entities) {
		return ds.save(entities);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#save(T)
	 */
	public <T> Key<T> save(T entity) {
		return ds.save(entity);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#save(T, com.mongodb.WriteConcern)
	 */
	public <T> Key<T> save(T entity, WriteConcern wc) {
		return ds.save(entity, wc);
	}
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#updateFirst(com.google.code.morphia.query.Query, com.google.code.morphia.query.UpdateOperations)
	 */
	public <T> UpdateResults<T> updateFirst(Query<T> q, UpdateOperations<T> ops) {
		return ds.updateFirst(q, ops);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#update(com.google.code.morphia.query.Query, com.google.code.morphia.query.UpdateOperations)
	 */
	public <T> UpdateResults<T> update(Query<T> q, UpdateOperations<T> ops) {
		return ds.update(q, ops);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#delete(T)
	 */
	public <T> WriteResult delete(T entity) {
		return ds.delete(entity);
	}

	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#delete(T, com.mongodb.WriteConcern)
	 */
	public <T> WriteResult delete(T entity, WriteConcern wc) {
		return ds.delete(entity, wc);
	}

	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#deleteById(K)
	 */
	public <T, K> WriteResult deleteById(Class<T> entityClazz,K id) {
		return ds.delete(entityClazz, id);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#deleteByQuery(com.google.code.morphia.query.Query)
	 */
	@SuppressWarnings("rawtypes")
	public WriteResult deleteByQuery(Query q) {
		return ds.delete(q);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#get(K)
	 */
	public <T, K> T get(Class<T> entityClazz, K id) {
		return ds.get(entityClazz, id);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#findIds(java.lang.String, java.lang.Object)
	 */
	public <T> List<T> findIds(Class<T> entityClazz, String key, Object value) {
		return (List<T>) keysToIds(ds.find(entityClazz, key, value).asKeyList());
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#findIds()
	 */
	public <T> List<Key<T>> findIds(Class<T> entityClazz) {
		return (List<Key<T>>) keysToIds(ds.find(entityClazz).asKeyList());
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#findIds(com.google.code.morphia.query.Query)
	 */
	public <T> List<Key<T>> findIds(Query<T> q) {
		return (List<Key<T>>) keysToIds(q.asKeyList());
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#exists(java.lang.String, java.lang.Object)
	 */
	public <T> boolean exists(Class<T> entityClazz, String key, Object value) {
		return exists(ds.find(entityClazz, key, value));
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#exists(com.google.code.morphia.query.Query)
	 */
	public <T> boolean exists(Query<T> q) {
		return ds.getCount(q) > 0;
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#count()
	 */
	public <T> long count(Class<T> entityClazz) {
		return ds.getCount(entityClazz);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#count(java.lang.String, java.lang.Object)
	 */
	public <T> long count(Class<T> entityClazz, String key, Object value) {
		return count(ds.find(entityClazz, key, value));
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#count(com.google.code.morphia.query.Query)
	 */
	public <T> long count(Query<T> q) {
		return ds.getCount(q);
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#findOne(java.lang.String, java.lang.Object)
	 */
	public <T> T findOne(Class<T> entityClazz, String key, Object value) {
		return ds.find(entityClazz, key, value).get();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#findOne(com.google.code.morphia.query.Query)
	 */
	public <T> T findOne(Query<T> q) {
		return q.get();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.morphia.DAO#find(com.google.code.morphia.query.Query)
	 */
	public <T> QueryResults<T> find(Query<T> q) {
		return q;
	}
	
	public <T> void ensureIndexes(Class<T> entityClazz) {
		ds.ensureIndexes(entityClazz);
	}
	
	@Autowired
	protected BaseDAO(Datastore ds) {
		this.ds = (DatastoreImpl) ds;
	}
	
	public <T> List<T> findAll(Class<T> target) {
		return ds.find(target).asList();
	}
	
	public <T> List<T> findAll(Class<T> target, int offset, int length) {
		return ds.find(target).offset(offset).limit(length).asList();
	}
	
	public <T> List<T> findAll(Class<T> target, Map<String, Object> conditionMap) {
		Query<T> query = ds.find(target);
		Iterator<Entry<String, Object>> it = conditionMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Object> entry = it.next();
			query = query.filter(entry.getKey(), entry.getValue());
		}
		return query.asList();
	}
	
	public <T> List<T> findAll(Class<T> target, Map<String, Object> conditionMap, int offset, int size) {
		Query<T> query = ds.find(target);
		Iterator<Entry<String, Object>> it = conditionMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Object> entry = it.next();
			query.filter(entry.getKey(), entry.getValue());
		}
		query.offset(offset).limit(size);
		return query.asList();
	}
	
	public <T> List<T> findAll(Class<T> target, String property, Object value) {
		return ds.find(target, property, value).asList();
	}
	
	public <T> List<T> findAll(Class<T> target,String property, Object value, int offset, int size) {
		return ds.find(target, property, value).offset(offset).limit(size).asList();
	}
	
	public <T, V> void delete(Class<T> target, Iterable<V> ids) {
		ds.delete(target, ids);
	}
	
	public <T extends BaseEntity> DBRef createDBRef(T entity) {
		return ds.createRef(entity);
	}
	
	public <T extends BaseEntity> List<DBRef> createDBRefList(List<T> entities) {
		List<DBRef> refs = new ArrayList<DBRef>();
		for(T entity : entities) {
			refs.add(ds.createRef(entity));
		}
		return refs;
	}
	
	public <T extends BaseEntity> DBRef[] createDBRefArray(List<T> entities) {
		return createDBRefList(entities).toArray(new DBRef[entities.size()]);
	}
	
	public <T extends BaseEntity> Query<T> createQuery(Class<T> clazz) {
		return ds.find(clazz);
	}
	
	

}
