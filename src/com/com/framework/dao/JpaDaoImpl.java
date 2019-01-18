package com.framework.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaDaoImpl<T> {

    @PersistenceContext
    private EntityManager em;

    public void save(Object entity){
        em.persist(entity);
    }

    public void update(Object entity){
        em.merge(entity);
    }

    public void remove(Object entity){
        em.remove(entity);
    }

    public <T> T get(Class<T> tClass,Object id){
        return em.find(tClass,id);
    }

    public List find(String sql,Object... params){
        Query query=em.createQuery(sql);
        for(int i = 1;i<=params.length;i++){
            query.setParameter(i,params[i]);
        }
        return query.getResultList();
    }


}
