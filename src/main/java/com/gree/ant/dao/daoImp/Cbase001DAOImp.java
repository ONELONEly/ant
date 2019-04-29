package com.gree.ant.dao.daoImp;

import com.gree.ant.dao.Cbase001DAO;
import com.gree.ant.dao.daoImp.util.DAOUtil;
import com.gree.ant.vo.Cbase001VO;
import com.gree.ant.vo.util.ResultVO;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class Cbase001DAOImp extends BaseDAOImp<Cbase001VO> implements Cbase001DAO{

    @Override
    public List<ResultVO> queryAllCD() {
        String sqlStr = "select comp,dsca from cbase001 order by comp asc";
        return DAOUtil.getResultVO(sqlStr,this.getDao());
    }
}
