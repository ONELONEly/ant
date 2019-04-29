package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.BaseDAO;
import com.gree.ant.exception.KellyException;
import com.gree.ant.vo.enumVO.ResultEnum;
import org.apache.log4j.Logger;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Mirror;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

/**
 * @param <T>
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description TODO
 */
@IocBean
public class BaseDAOImp<T> implements BaseDAO<T> {

    /**
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    @Inject("refer:daoFX")
    private Dao dao;

    /**
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Logger logger = Logger.getLogger(getClass());


    /**
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    private Mirror<T> mirror;

    /**
     * Instantiates a new Base dao imp.
     *
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     */
    public BaseDAOImp() {
        try {
            Class<T> entityClass = Mirror.getTypeParam(getClass(), 0);
            mirror = Mirror.me(entityClass);
            if (logger.isTraceEnabled())
                logger.debug("获得泛型的实际类型 :" + entityClass.getName());
        }catch (Throwable a){
            if(logger.isTraceEnabled())
                logger.error("无法获得泛型类型",a);
            throw new KellyException(ResultEnum.RUNTIME_ERROR);
        }
    }

    /**
     * Insert value object.
     *
     * @param vo @return 返回被封装的VO
     * @return TODO
     * @description 根据实体插入一条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public T insert(T vo) {
        return dao.insert(vo);
    }

    /**
     * Insert list.
     *
     * @param vo VO实体集合，必须为List<T>
     * @return 返回被封装的VO集合 list
     * @description 根据实体插入多条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:37.
     */
    @Override
    public List<T> insert(List<T> vo) {
        return dao.insert(vo);
    }

    /**
     * Insert relation value object.
     *
     * @param vo      VO实体
     * @param primary
     * @return VO实体
     * @description 根据实体插入一条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 09:09:52.
     */
    @Override
    public T insertRelation(T vo, String primary) {
        return dao.insertRelation(vo,primary);
    }

    /**
     * Fast insert value object.
     *
     * @param vo VO实体
     * @return 返回被封装的VO
     * @description 插入, 通过batch插入单条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public Class<T> fastInsert(Class<T> vo) {
        return dao.fastInsert(vo);
    }

    /**
     * Fast insert list.
     *
     * @param vo VO实体
     * @return 返回被封装的VO集合
     * @description 插入, 通过batch插入单条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:28.
     */
    @Override
    public List<T> fastInsert(List<T> vo) {
        return dao.fastInsert(vo);
    }

    /**
     * Insert with value object.
     *
     * @param vo   VO实体
     * @param name @Name型主键
     * @return 返回被封装的VO
     * @description 关联映射 ，同时修改本表关联表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public T insertWith(T vo, String name) {
        return dao.insertWith(vo,name);
    }

    /**
     * Insert links value object.
     *
     * @param vo   VO实体
     * @param name @Name型主键
     * @return 返回被封装的VO
     * @description 关联映射 ，仅修改本表的关联表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public Class<T> insertLinks(Class<T> vo, String name) {
        return dao.insertLinks(vo,name);
    }

    /**
     * Delete value object.
     *
     * @param vo VO实体
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除, 删除一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public Integer delete(T vo) {
        return dao.delete(vo);
    }

    /**
     * Delete by name integer.
     *
     * @param name @Name主键
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 通过VO(如new Cbase000VO, 无参构造即可) ，@Name型主键进行删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteByName(String name) {
        return dao.delete(getEntryClz(),name);
    }

    /**
     * Delete by id integer.
     *
     * @param id @Id主键
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除, VO(如new Cbase000VO, 无参构造即可) ，通过@ID型参数进行删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteByID( long id) {
        return dao.delete(getEntryClz(),id);
    }

    /**
     * Delete with integer.
     *
     * @param vo      VO实体
     * @param primary 关联的实体参数名
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除 ，同时删除关联的实体内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteWith(T vo, String primary) {
        return dao.deleteWith(vo,primary);
    }

    /**
     * Delete links integer.
     *
     * @param vo   VO实体
     * @param name 关联的实体参数名
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除 ，仅仅删除关联的实体.多对多时删除关联表和被关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteLinks(T vo, String name) {
        return dao.deleteLinks(vo,name);
    }

    /**
     * Update value object.
     *
     * @param vo VO实体
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 更新, 更新一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public Integer update(T vo) {
        return dao.update(vo);
    }

    /**
     * Update value object.
     *
     * @param vo VO实体
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 更新, 更新多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:31.
     */
    @Override
    public Integer update(List<T> vo) {
        return dao.update(vo);
    }

    /**
     * Fetch by name value object.
     *
     * @param name @Name主键
     * @return 返回被封装的VO
     * @description 获取, 根据@Name获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public T fetchByName(String name) {
        return dao.fetch(getEntryClz(),name);
    }

    /**
     * Fetch by id value object.
     *
     * @param id @ID主键
     * @return 返回被封装的VO
     * @description 获取, 根据@ID获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public T fetchByID(long id) {
        return dao.fetch(getEntryClz(),id);
    }

    /**
     * Fetch links value object.
     *
     * @param vo      VO实体
     * @param primary 被关联表的字段名
     * @param cnd
     * @return 返回被封装的VO
     * @description 查询被关联表的实体 （集合）,以当前实体的格式返回.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 10:09:17.
     */
    @Override
    public T fetchLinks(T vo, String primary,Condition cnd) {
        return dao.fetchLinks(vo,primary,cnd);
    }

    /**
     * Fetch trans by name value object.
     *
     * @param name    @Name关键词
     * @param primary 关联表的字段
     * @param cnd     过滤条件-过滤关联是调用
     * @return 被包装的VO
     * @description 获取, 根据 ( 如new Cbase000VO,无参构造即可),@Name,关联表字段名获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 02:09:33.
     */
    @Override
    public T fetchTransByNameCnd(String name, String primary,Condition cnd) {
        return dao.fetchLinks(fetchByName(name),primary,cnd);
    }

    /**
     * Fetch trans by id value object.
     *
     * @param id      @ID关键词
     * @param primary 关联表的字段
     * @param cnd     过滤条件-过滤关联是调用
     * @return 被包装的VO
     * @description 获取, 根据 ( 如new Cbase000VO,无参构造即可),@ID,关联表字段名获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 02:09:35.
     */
    @Override
    public T fetchTransByIdCnd(long id, String primary,Condition cnd) {
        return dao.fetchLinks(fetchByID(id),primary,cnd);
    }

    /**
     * Query by cnd pager list.
     *
     * @param cnd   过滤条件
     * @param pager 分页条件
     * @return 返回被Map封装的VO集合
     * @description 查询, 根据VO(如:new Cbase000VO(),无参构造即可),Cnd, Paper获取多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public List<T> queryByCndPager(Condition cnd, Pager pager) {
        return dao.query(getEntryClz(),cnd,pager);
    }


    /**
     * @param cnd
     * @return TODO
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    @Override
    public List<T> queryByCnd(Condition cnd) {
        return dao.query(getEntryClz(),cnd);
    }

    /**
     * Query by cnd pager list.
     *
     * @param cnd     过滤条件
     * @param pager   分页条件
     * @param primary 关联字段
     * @return 返回被Map封装的VO集合 list
     * @description 查询, 根据Cnd, Paper获取多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public List<T> queryByCndPager(Condition cnd, Pager pager, String primary) {
        return dao.query(getEntryClz(),cnd,pager,primary);
    }

    /**
     * Clear boolean.
     *
     * @return 返回数据是否删除成功 boolean
     * @description 清除, 删除表内的所有数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public Integer clear() {
        return dao.clear(getEntryClz());
    }

    /**
     * Clear links integer.
     *
     * @param vo      VO实体
     * @param primary 关联的实体参数名
     * @return 返回VO实体
     * @description 清除 ，和deleteLinks类似，但是多对多映射时，仅仅删除关联表的数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 09:09:26.
     */
    @Override
    public T clearLinks(T vo, String primary) {
        return dao.clearLinks(vo,primary);
    }

    /**
     * Delete by cnd integer.
     *
     * @param cnd 过滤条件
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除 ，根据cnd进行多条数据删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:40.
     */
    @Override
    public Integer clearByCnd(Condition cnd) {
        return dao.clear(getEntryClz(),cnd);
    }

    /**
     * Count by cnd integer.
     *
     * @param cnd 过滤字段
     * @return 表有多少数据
     * @description 根据VO实体(如new Cbase000VO, 无参构造即可)和cnd查询表数据的数量
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 11:09:07.
     */
    @Override
    public Integer countByCnd(Condition cnd) {
        return dao.count(getEntryClz(),cnd);
    }

    /**
     * Create boolean.
     *
     * @param vo vo实体
     * @return 返回表是否创建成功 boolean
     * @description 建表, 根据实体建表.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public Boolean create(Class<T> vo) {
        return null;
    }

    /**
     * Drop boolean.
     *
     * @param vo Vo实体
     * @return 返回表是否删除成功 boolean
     * @description 删表, 根据实体 /表名称进行删表.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public Boolean drop(Class<T> vo) {
        return null;
    }

    /**
     * Func integer.
     *
     * @return the integer
     * @description 聚合, 执行sum, count等操作.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public Integer func() {
        return null;
    }

    /**
     * Gets entry clz.
     *
     * @return TODO
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @date $date$
     */
    public Class<T> getEntryClz() {
        return mirror.getType();
    }

    /**
     * Gets dao.
     *
     * @return TODO
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @date $date$
     */
    public Dao getDao() {
        return dao;
    }

    /**
     * Sets dao.
     *
     * @param dao
     * @description TODO
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version * @date $date$
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
