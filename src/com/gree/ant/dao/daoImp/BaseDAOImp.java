package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.BaseDAO;
import com.gree.ant.vo.ValueObject;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class BaseDAOImp extends BaseDAO{

    @Inject("refer:daoFX")
    private Dao dao;

    /**
     * Insert value object.
     *
     * @param vo@return 返回被封装的VO
     * @description 根据实体插入一条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public ValueObject insert(ValueObject vo) {
        return dao.insert(vo);
    }

    /**
     * Insert list.
     *
     * @param vo VO实体集合，必须为List<ValueObject>
     * @return 返回被封装的VO集合 list
     * @description 根据实体插入多条数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:37.
     */
    @Override
    public List<ValueObject> insert(List<ValueObject> vo) {
        return dao.insert(vo);
    }

    /**
     * Insert relation value object.
     *
     * @param vo   VO实体
     * @param primary
     * @return VO实体
     * @description 根据实体插入一条数据
     * @description 多对多时使用，仅仅插入关联映射表，关联表与被关联表数据不变
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 09:09:52.
     */
    @Override
    public ValueObject insertRelation(ValueObject vo, String primary) {
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
    public ValueObject fastInsert(ValueObject vo) {
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
    public List<ValueObject> fastInsert(List<ValueObject> vo) {
        return dao.fastInsert(vo);
    }

    /**
     * Insert with value object.
     *
     * @param vo   VO实体
     * @param name @Name型主键
     * @return 返回被封装的VO
     * @description 关联映射，同时修改本表关联表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public ValueObject insertWith(ValueObject vo, String name) {
        return dao.insertWith(vo,name);
    }

    /**
     * Insert links value object.
     *
     * @param vo   VO实体
     * @param name @Name型主键
     * @return 返回被封装的VO
     * @description 关联映射，仅修改本表的关联表
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public ValueObject insertLinks(ValueObject vo, String name) {
        return dao.insertLinks(vo,name);
    }

    /**
     * Delete value object.
     *
     * @param vo VO实体
     * @return 返回删除的结果.1:成功;0:失败
     * @description 删除, 删除一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public Integer delete(ValueObject vo) {
        return dao.delete(vo);
    }

    /**
     * Delete by name integer.
     *
     * @param vo   VO实体
     * @param name @Name主键
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 通过VO( 如new Cbase000VO,无参构造即可)，@Name型主键进行删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteByName(ValueObject vo, String name) {
        return dao.delete(vo.getClass(),name);
    }

    /**
     * Delete by id integer.
     *
     * @param vo VO实体
     * @param id @Id主键
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除,VO( 如new Cbase000VO,无参构造即可)，通过@ID型参数进行删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteByID(ValueObject vo, long id) {
        return dao.delete(vo.getClass(),id);
    }

    /**
     * Delete with integer.
     *
     * @param vo   VO实体
     * @param primary 关联的实体参数名
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除，同时删除关联的实体内容
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteWith(ValueObject vo, String primary) {
        return dao.deleteWith(vo,primary);
    }

    /**
     * Delete links integer.
     *
     * @param vo   VO实体
     * @param name 关联的实体参数名
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除，仅仅删除关联的实体.多对多时删除关联表和被关联的实体
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:28.
     */
    @Override
    public Integer deleteLinks(ValueObject vo, String name) {
        return dao.deleteLinks(vo,name);
    }

    /**
     * Update value object.
     *
     * @param vo VO实体
     * @return 返回删除的结果.1:成功;0:失败
     * @description 更新, 更新一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:08.
     */
    @Override
    public Integer update(ValueObject vo) {
        return dao.update(vo);
    }

    /**
     * Update value object.
     *
     * @param vo VO实体
     * @return 返回删除的结果.1:成功;0:失败
     * @description 更新, 更新多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:31.
     */
    @Override
    public Integer update(List<ValueObject> vo) {
        return dao.update(vo);
    }

    /**
     * Fetch by name value object.
     *
     * @param vo   VO实体
     * @param name @Name主键
     * @return 返回被封装的VO
     * @description 获取, 根据@Name获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public ValueObject fetchByName(ValueObject vo, String name) {
        return dao.fetch(vo.getClass(),name);
    }

    /**
     * Fetch by id value object.
     *
     * @param vo VO实体
     * @param id @ID主键
     * @return 返回被封装的VO
     * @description 获取, 根据@ID获取一条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public ValueObject fetchByID(ValueObject vo,long id) {
        return dao.fetch(vo.getClass(),id);
    }

    /**
     * Fetch links value object.
     *
     * @param vo   VO实体
     * @param primary 被关联表的字段名
     * @return 返回被封装的VO
     * @description 查询被关联表的实体（集合）,以当前实体的格式返回.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 10:09:17.
     */
    @Override
    public ValueObject fetchLinks(ValueObject vo, String primary,Condition cnd) {
        return dao.fetchLinks(vo,primary,cnd);
    }

    /**
     * Fetch trans by name value object.
     *
     * @param vo      VO实体
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
    public ValueObject fetchTransByNameCnd(ValueObject vo, String name, String primary,Condition cnd) {
        return dao.fetchLinks(fetchByName(vo,name),primary,cnd);
    }

    /**
     * Fetch trans by id value object.
     *
     * @param vo      VO实体
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
    public ValueObject fetchTransByIdCnd(ValueObject vo, long id, String primary,Condition cnd) {
        return dao.fetchLinks(fetchByID(vo,id),primary,cnd);
    }

    /**
     * Query by cnd pager list.
     *
     * @param vo    VO实体
     * @param cnd   过滤条件
     * @param pager 分页条件
     * @return 返回被Map封装的VO集合
     * @description 查询, 根据VO(如:new Cbase000VO(),无参构造即可),Cnd, Paper获取多条记录.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public List<ValueObject> queryByCndPager(ValueObject vo, Condition cnd, Pager pager) {
        Iterator iterator = dao.query(vo.getClass(),cnd,pager).iterator();
        List<ValueObject> voS = new ArrayList<>();
        while (iterator.hasNext()) {
            ValueObject po = (ValueObject) iterator.next();
            voS.add(po);
        }
        return voS;
    }

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
    @Override
    public List<ValueObject> queryByCndPager(ValueObject vo, Condition cnd, Pager pager, String primary) {
        Iterator iterator = dao.query(vo.getClass(),cnd,pager,primary).iterator();
        List<ValueObject> voS = new ArrayList<>();
        while (iterator.hasNext()) {
            ValueObject po = (ValueObject) iterator.next();
            voS.add(po);
        }
        return voS;
    }

    /**
     * Clear boolean.
     *
     * @param vo VO实体
     * @return 返回数据是否删除成功 boolean
     * @description 清除, 删除表内的所有数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 02:09:09.
     */
    @Override
    public Integer clear(ValueObject vo) {
        return dao.clear(vo.getClass());
    }

    /**
     * Clear links integer.
     *
     * @param vo   VO实体
     * @param primary 关联的实体参数名
     * @return 返回VO实体
     * @description 清除，和deleteLinks类似，但是多对多映射时，仅仅删除关联表的数据
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 09:09:26.
     */
    @Override
    public ValueObject clearLinks(ValueObject vo, String primary) {
        return dao.clearLinks(vo,primary);
    }

    /**
     * Delete by cnd integer.
     *
     * @param vo  VO实体
     * @param cnd 过滤条件
     * @return 返回删除的结果.1 :成功;0:失败
     * @description 删除 ，根据cnd进行多条数据删除
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:01 05:09:40.
     */
    @Override
    public Integer clearByCnd(ValueObject vo, Condition cnd) {
        return dao.clear(vo.getClass(),cnd);
    }

    /**
     * Count by cnd integer.
     *
     * @param vo  VO实体
     * @param cnd 过滤字段
     * @return 表有多少数据
     * @description 根据VO实体(如new Cbase000VO, 无参构造即可)和cnd查询表数据的数量
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:02 11:09:07.
     */
    @Override
    public Integer countByCnd(ValueObject vo, Condition cnd) {
        return dao.count(vo.getClass(),cnd);
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
    public Boolean create(ValueObject vo) {
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
    public Boolean drop(ValueObject vo) {
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
}
