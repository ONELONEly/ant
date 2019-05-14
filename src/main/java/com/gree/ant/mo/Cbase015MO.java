package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase015DAOImp;
import com.gree.ant.mo.basic.Cbase015BasicMO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.vo.Cbase015VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase015MO implements Cbase015BasicMO{


    @Inject("refer:cbase015DAOImp")
    private Cbase015DAOImp cbase015DAOImp;


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
    public List<Cbase015VO> queryAllByCndPager(Condition cnd, Pager pager) {
        return cbase015DAOImp.queryByCndPager(cnd,pager);
    }

    /**
     * Isnert cbase 015 vo.
     *
     * @param cbase015VO the cbase 015 vo
     * @return the cbase 015 vo
     * @description 插入一条附件信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 04:09:05.
     */
    @Override
    public Cbase015VO insert(Cbase015VO cbase015VO) {
        return cbase015DAOImp.insert(cbase015VO);
    }

    /**
     * Delete by taid integer.
     *
     * @param taid the tadid
     * @return the integer
     * @description 通过任务ID删除单条记录和他所属的文件
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:09 08:09:31.
     */
    @Override
    public Integer deleteByTaid(String taid) {
        Integer code = 1;
        Condition cnd = Cnd.where("djid","=",taid);
        for (Cbase015VO cbase015VO : queryAllByCndPager(cnd, null)) {
            code = cbase015DAOImp.deleteByName(cbase015VO.getDuta());
            FileUtil.createFileUtil().deleteFileByDuta(cbase015VO.getDuta(),cbase015VO.getFfil());
        }
        return code;
    }

    @Override
    public Integer deleteByName(String duta,String fileName) {
        FileUtil.createFileUtil().deleteFileByDuta(duta,fileName);
        return cbase015DAOImp.deleteByName(duta);
    }
}
