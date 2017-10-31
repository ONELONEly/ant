package com.gree.ant.dao;

import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * The type Base dao.
 *
 * @author create by jinyuk@foxmail.com.
 * @version V1.0
 * @description 所有实体的基本操作全在本个DAO接口中调用.
 * @title BaseDAO
 * @createTime 2017 :09:01 02:09:08.
 */
public abstract class BaseDAO {
    /**
     * Insert value object.
     *
     * @param vo VO实体
     * @return VO 实体
     * @description 根据实体插入一条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    public abstract ValueObject insert(ValueObject  vo);

    /**
     * Insert list.
     *
     * @param vo VO实体
     * @return 返回被封装的VO集合 list
     * @description 根据实体插入多条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:37.
     */
    public abstract List<ValueObject> insert(List<ValueObject> vo);

    /**
     * Insert relation value object.
     *
     * @param vo      VO实体
     * @param primary the primary
     * @return VO实体 value object
     * @description 根据实体插入一条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 09:09:52.
     */
    public abstract ValueObject insertRelation(ValueObject vo,String primary);

    /**
     * Fast insert value object.
     *
     * @param vo VO实体
     * @return 返回被封装的VO value object
     * @description 插入, 通过batch插入单条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    public abstract ValueObject fastInsert(ValueObject vo);

    /**
     * Fast insert list.
     *
     * @param vo VO实体
     * @return 返回被封装的VO集合 list
     * @description 插入, 通过batch插入单条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:28.
     */
    public abstract List<ValueObject> fastInsert(List<ValueObject>  vo);

    /**
     * Insert with value object.
     *
     * @param vo   VO实体
     * @param name @Name型主键
     * @return 返回被封装的VO value object
     * @description 关联映射 ，同时修改本表关联表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    public abstract ValueObject insertWith(ValueObject vo,String name);

    /**
     * Insert links value object.
     *
     * @param vo   VO实体
     * @param name @Name型主键
     * @return 返回被封装的VO value object
     * @description 关联映射 ，仅修改本表的关联表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    public abstract ValueObject insertLinks(ValueObject vo,String name);

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
    public abstract Integer delete(ValueObject vo);


    /**
     * Delete by name integer.
     *
     * @param vo   VO实体
     * @param name @Name主键
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 通过VO(如new Cbase000VO, 无参构造即可) ，@Name型主键进行删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    public abstract Integer deleteByName(ValueObject vo,String name);

    /**
     * Delete by id integer.
     *
     * @param vo VO实体
     * @param id @Id主键
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除, VO(如new Cbase000VO, 无参构造即可) ，通过@ID型参数进行删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    public abstract Integer deleteByID(ValueObject vo,long id);

    /**
     * Delete with integer.
     *
     * @param vo   VO实体
     * @param primary 关联的实体参数名
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除 ，同时删除关联的实体内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    public abstract Integer deleteWith(ValueObject vo,String primary);

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
    public abstract Integer deleteLinks(ValueObject vo,String name);

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
    public abstract Integer update(ValueObject  vo);

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
    public abstract Integer update(List<ValueObject>  vo);

    /**
     * Fetch by name value object.
     *
     * @param vo   VO实体
     * @param name @Name主键
     * @return 返回被封装的VO value object
     * @description 获取, 根据 ( 如new Cbase000VO,无参构造即可),@Name获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract ValueObject fetchByName(ValueObject vo, String name);

    /**
     * Fetch by id value object.
     *
     * @param vo VO实体
     * @param id @ID主键
     * @return 返回被封装的VO value object
     * @description 获取, 根据 ( 如new Cbase000VO,无参构造即可),@ID获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract ValueObject fetchByID(ValueObject vo,long id);

    /**
     * Fetch links value object.
     *
     * @param vo      VO实体
     * @param primary the primary
     * @return 返回被封装的VO value object
     * @description 查询被关联表的实体 （集合）,以当前实体的格式返回.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 10:09:17.
     */
    public abstract ValueObject fetchLinks(ValueObject vo,String primary,Condition cnd);

    /**
     * Fetch trans by name value object.
     *
     * @param vo      VO实体
     * @param name    @Name关键词
     * @param primary 关联表的字段
     * @param cnd     过滤条件-过滤关联是调用
     * @return 被包装的VO value object
     * @description 获取, 根据 ( 如new Cbase000VO,无参构造即可),@Name,关联表字段名获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 02:09:33.
     */
    public abstract ValueObject fetchTransByNameCnd(ValueObject vo,String name,String primary,Condition cnd);

    /**
     * Fetch trans by id value object.
     *
     * @param vo      VO实体
     * @param id      @ID关键词
     * @param primary 关联表的字段
     * @param cnd     过滤条件-过滤关联是调用
     * @return 被包装的VO value object
     * @description 获取, 根据 ( 如new Cbase000VO,无参构造即可),@ID,关联表字段名获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 02:09:35.
     */
    public abstract ValueObject fetchTransByIdCnd(ValueObject vo,long id,String primary,Condition cnd);

    /**
     * Query by cnd pager list.
     *
     * @param vo    VO实体
     * @param cnd   过滤条件
     * @param pager 分页条件
     * @return 返回被Map封装的VO集合 list
     * @description 查询, 根据Cnd, Paper获取多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract List<ValueObject> queryByCndPager(ValueObject  vo, Condition cnd, Pager pager);

    /**
     * Query by cnd pager list.
     *
     * @param vo      VO实体
     * @param cnd     过滤条件
     * @param pager   分页条件
     * @param primary 关联字段
     * @return 返回被Map封装的VO集合 list
     * @description 查询, 根据Cnd, Paper获取多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract List<ValueObject>  queryByCndPager(ValueObject  vo, Condition cnd, Pager pager,String primary);

    /**
     * Clear boolean.
     *
     * @param vo VO实体
     * @return 返回数据是否删除成功 boolean
     * @description 清除, (如new Cbase000VO, 无参构造即可), 删除表内的所有数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract Integer clear(ValueObject vo);


    /**
     * Clear links integer.
     *
     * @param vo      VO实体
     * @param primary 关联的实体参数名
     * @return 返回VO实体 value object
     * @description 清除 ，和deleteLinks类似，但是多对多映射时，仅仅删除关联表的数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 09:09:26.
     */
    public abstract ValueObject clearLinks(ValueObject vo,String primary);


    /**
     * Delete by cnd integer.
     *
     * @param vo  VO实体
     * @param cnd 过滤条件
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除 ，根据VO( 如new Cbase000VO,无参构造即可),cnd进行多条数据删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:40.
     */
    public abstract Integer clearByCnd(ValueObject vo,Condition cnd);

    /**
     * Count by cnd integer.
     *
     * @param vo  VO实体
     * @param cnd 过滤字段
     * @return 表有多少数据 integer
     * @description 根据VO实体(如new Cbase000VO, 无参构造即可)和cnd查询表数据的数量
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 11:09:07.
     */
    public abstract Integer countByCnd(ValueObject vo,Condition cnd);


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
    public abstract Boolean create(ValueObject vo);

    /**
     * Drop boolean.
     *
     * @param vo the vo
     * @return 返回表是否删除成功 boolean
     * @description 删表, 根据实体 /表名称进行删表.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract Boolean drop(ValueObject vo);

    /**
     * Func integer.
     *
     * @return the integer
     * @description 聚合, 执行sum, count等操作.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    public abstract Integer func();
}
