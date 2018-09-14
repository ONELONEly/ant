package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase006DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Cbase006VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class Cbase006DAOImp extends BaseDAOImp<Cbase006VO> implements Cbase006DAO{

    @Override
    public Boolean insertCheck(Condition cnd) {
        String sqlStr = "select * from cbase006 $condition";
        return DAOUtil.insertCheck(this.getDao(),sqlStr,cnd);
    }

    @Override
    public List<ResultVO> queryAllDD() {
        String sqlStr = "select dept,dsca from cbase006 order by dsca asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }
}
