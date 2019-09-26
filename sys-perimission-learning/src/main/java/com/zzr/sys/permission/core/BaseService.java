/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.zzr.sys.permission.core;

import com.zzr.sys.permission.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzh on 2014/12/11.
 */
public abstract class BaseService<T  extends BaseEntity> implements IService<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    protected MyMapper<T> myMapper;

    public Mapper<T> getMyMapper() {
        return myMapper;
    }

    @Override
    public T selectByKey(Object key) {
        return myMapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        entity.setDeleted(0);
        return myMapper.insert(entity);
    }

    @Override
    public int saveList(List<T> list) {
        for (T t : list){
            t.setDeleted(0);
            myMapper.insert(t);
        }
        return 0;
    }

    @Override
    public int saveNotNull(T entity) {
        entity.setDeleted(0);
        return myMapper.insertSelective(entity);
    }

    @Override
    public int delete(Integer id) {
        T baseEntity = this.selectByKey(id);
        baseEntity.setDeleted(1);
        return myMapper.updateByPrimaryKeySelective(baseEntity);
    }

    @Override
    public void deleteByIdList(List<Integer> list) {
        for (Integer id : list){
            T baseEntity = this.selectByKey(id);
            baseEntity.setDeleted(1);
            myMapper.updateByPrimaryKeySelective(baseEntity);
        }
    }

    @Override
    public int update(T entity) {
        return myMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return myMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return myMapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll() {
        return myMapper.selectAll();
    }

    /**
     * 为高并发环境出现的更新和删除操作，验证更新数据库记录条数
     *
     * @param updateRows
     * @param rows
     * @param message
     */
    protected void verifyRows(int updateRows, int rows, String message) {
        if (updateRows != rows) {
            DaoException e = new DaoException(message);
            logger.error("need update is {}, but real update rows is {}.", rows, updateRows, e);
            throw e;
        }
    }

    protected void initParamsFromPagerResult(Map<String,Object> params, PagerResult pagerResult){
        params.put("offset",pagerResult.getOffset());
        params.put("pageSize",pagerResult.getPageSize());
    }
}
