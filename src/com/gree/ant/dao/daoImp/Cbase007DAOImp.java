package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase007DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Cbase007VO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase007DAOImp extends BaseDAOImp<Cbase007VO> implements Cbase007DAO{

    @Override
    public Boolean insertCheck(Condition cnd) {
        String sqlStr = "select * from cbase007 $condition";
        return DAOUtil.insertCheck(this.getDao(),sqlStr,cnd);
    }
}
