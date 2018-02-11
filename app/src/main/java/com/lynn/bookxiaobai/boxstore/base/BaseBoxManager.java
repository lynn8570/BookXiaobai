package com.lynn.bookxiaobai.boxstore.base;

import com.lynn.bookxiaobai.boxstore.BoxConfig;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

/**
 * Created by lynn on 2018/2/10.
 * @param <T> database entity
 *
 */
public abstract class BaseBoxManager<T> {
    protected Box<T> mBox;
    Class<T> tClass;

    public BaseBoxManager(Class<T> entityClazz){
        this.tClass=entityClazz;
        mBox= BoxConfig.getBoxStore().boxFor(tClass);
    }

    protected final void closeDatabase(){
        mBox.closeThreadResources();
    }
    /**
     * 插入一条记录
     *
     * @return The ID of the object within its box.
     */
    public long insert(T entity) {
        if (entity == null) return -1;

        return mBox.put(entity);


    }


    /**
     * 插入多条记录
     */
    public void insert(List<T> entities) {
        if (entities != null)

            mBox.put(entities);

    }


    /**
     * 删除所有数据
     */
    public void deleteAll() {
        mBox.removeAll();
    }


    /**
     * 根据条件删除数据库中的数据
     */
    public void delete(T object) {
        mBox.remove(object);
    }

    /**
     * 删除多条数据
     */
    public void deleteList(List<T> objects) {
        mBox.remove(objects);
    }


    /**
     * 更新一条记录
     */
    public long update(T object) {

        return mBox.put(object);
    }

    /**
     * 更新一条记录
     */
    public void update(List<T> objects) {
        mBox.put(objects);
    }

    /**
     * @return Returns a builder to create queries for Object matching supplied criteria.
     */
    public QueryBuilder<T> getQueryBuilder() {
        return mBox.query();
    }

    /**
     * 查询并返回所有对象的集合
     */
    public List<T> queryAll() {
        return getQueryBuilder().build().find();
    }

    /**
     * 查询并返回 第一个对象
     */
    public T QueryFirst() {
        return getQueryBuilder().build().findFirst();
    }


    /**
     * 获取对应的表名
     */
    public abstract String getTableName();
}
