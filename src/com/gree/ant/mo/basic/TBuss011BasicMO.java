package com.gree.ant.mo.basic;

import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;
import com.gree.ant.vo.request.OkrVO;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description OKR业务逻辑接口
 */
public interface TBuss011BasicMO {

    /**
     * @param tbuss011VO
     * @param tbuss012VOS
     * @return 返回插入后的OKR完整数据
     * @description 插入单挑OKR数据
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    Tbuss011VO insert(Tbuss011VO tbuss011VO,List<Tbuss012VO> tbuss012VOS);

    Integer update(Tbuss011VO tbuss011VO,List<Tbuss012VO> tbuss012VOS);

    Integer delete(Integer[] okids);

    Tbuss011VO fetchByOkid(Integer okid);

    Tbuss011VO fetchTransByOkid(Integer okid);

    /**
     * @param pager 分页组件
     * @param usid   过滤信息
     * @return 返回查询的OKR结果集
     * @description 通过过滤信息和分页组件对OKR进行查询
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    List<Tbuss011VO> mQueryAllByMsgPager(Pager pager, String usid);

    /**
     * @param pager 分页组件
     * @param usid   过滤信息
     * @return 返回查询的OKR结果集
     * @description 通过过滤信息和分页组件对OKR进行查询
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    List<Tbuss011VO> uQueryAllByMsgPager(Pager pager, String usid);

    /**
     * @param msg 过滤信息
     * @return OKR结果集的条数
     * @description 查询结果共有多少条
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @version 1.0
     */
    Integer countByMsg(String msg);
}
