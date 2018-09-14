package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Cbase013DAOImp;
import com.gree.ant.dao.daoImp.Tbuss003DAOImp_Ds;
import com.gree.ant.mo.basic.Cbase013BasicMO;
import com.gree.ant.vo.Cbase013VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import java.util.List;

@IocBean
public class Cbase013MO implements Cbase013BasicMO{
    
    @Inject("refer:cbase013DAOImp")
    private Cbase013DAOImp cbase013DAOImp;

    @Inject("refer:tbuss003DAOImp_Ds")
    private Tbuss003DAOImp_Ds tbuss003DAOImp_Ds;

    public  int tongbuDSSystem(){
        System.out.println("进入后台");
        List<Cbase013VO> systemList=tbuss003DAOImp_Ds.findAllSystemByDs();
        tbuss003DAOImp_Ds.delerefromCbase013();
        int code=0;
        for (int i = 0; i <systemList.size() ; i++) {
            Cbase013VO cbase013VO=systemList.get(i);
            cbase013DAOImp.insert(cbase013VO);
            code=1;
        }
        return code;
    }

    /**
     * Query all by cnd list.
     *
     * @param cnd   the cnd
     * @param pager the pager
     * @return the list
     * @description 查询所有系统信息.
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :09:07 10:09:58.
     */
    @Override
    public List<Cbase013VO> queryAllByCnd(Condition cnd, Pager pager) {
        return cbase013DAOImp.queryByCndPager(cnd,pager);
    }

    @Override
    public Integer countByCnd(Condition cnd) {
        return cbase013DAOImp.countByCnd(cnd);
    }

    @Override
    public Cbase013VO fetchBySyno(String syno) {
        return cbase013DAOImp.fetchByName(syno);
    }

    @Override
    public Integer updateByVO(Cbase013VO cbase013VO) {
        return cbase013DAOImp.update(cbase013VO);
    }

    public  Boolean insertCheck(String syno){
        Cnd cnd=Cnd.where("syno","=",syno);
        return cbase013DAOImp.insertCheck(cnd);
    }


    public Cbase013VO insert(Cbase013VO cbase013VO) {
        return cbase013DAOImp.insert(cbase013VO);
    }

    public Integer deleteBySyno(String syno) {
        return cbase013DAOImp.deleteByName(syno);
    }

}
