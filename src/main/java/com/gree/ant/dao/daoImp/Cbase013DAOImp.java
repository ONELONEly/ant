package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase013DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Cbase013VO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class Cbase013DAOImp extends BaseDAOImp<Cbase013VO> implements Cbase013DAO {


    @Override
    public Boolean insertCheck(Condition cnd) {
        String sqlStr = "select * from cbase013 $condition";
        return DAOUtil.insertCheck(this.getDao(),sqlStr,cnd);
    }
}
