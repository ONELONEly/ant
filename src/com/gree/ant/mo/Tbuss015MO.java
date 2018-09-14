package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss015DAOImp;
import com.gree.ant.mo.basic.Tbuss015BasicMO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Tbuss015VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Tbuss015MO implements Tbuss015BasicMO{




    @Inject("refer:tbuss015DAOImp")
    private Tbuss015DAOImp tbuss015DAOImp;

    /**
     * Query all by cnd pager list.
     *
     * @param cnd   过滤字段
     * @param pager 分页
     * @return the list
     * @description 根据过滤条件和分页查询所有的记录
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 09:09:08.
     */
    @Override
    public List<Tbuss015VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return tbuss015DAOImp.queryByCndPager(cnd,pager);
    }

    /**
     * Isnert cbase 015 vo.
     *
     * @param tbuss015VO the tbuss 015 vo
     * @return the cbase 015 vo
     * @description 插入一条附件信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 04:09:05.
     */
    @Override
    public Tbuss015VO insert(Tbuss015VO tbuss015VO) {
        return tbuss015DAOImp.insert(tbuss015VO);
    }

    @Override
    public Integer deleteByDuta(String duta,String fileName) {
        FileUtil.deleteFileByDuta(duta,fileName);
        return tbuss015DAOImp.deleteByName(duta);
    }

    /**
     * Delete by doid integer.
     *
     * @param doid the doid
     * @return the integer
     * @description 通过文档编号删除多个文档
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:21 03:09:19.
     */
    @Override
    public Integer deleteByDoid(Long doid) {
        Integer code = 0;
        List<Tbuss015VO> tbuss015VOS = queryAllByCndPager(Cnd.where("doid","=",doid),null);
        for(Tbuss015VO tbuss015VO:tbuss015VOS){
            code = deleteByDuta(tbuss015VO.getDuta(),tbuss015VO.getFfil());
        }
        return code;
    }
}
