package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss013DAOImp;
import com.gree.ant.mo.basic.Tbuss013BasicMO;
import com.gree.ant.vo.Tbuss013VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class Tbuss013MO implements Tbuss013BasicMO {

    @Inject
    private Tbuss013DAOImp tbuss013DAOImp;

    @Override
    public List<Tbuss013VO> insertTask(List<Tbuss013VO> tbuss013VOS) {

        return tbuss013DAOImp.insert(tbuss013VOS);
    }
}
