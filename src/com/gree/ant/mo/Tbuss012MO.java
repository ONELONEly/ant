package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss012DAOImp;
import com.gree.ant.mo.basic.Tbuss012BasicMO;
import com.gree.ant.vo.Tbuss012VO;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.List;
import java.util.Map;

@IocBean
public class Tbuss012MO implements Tbuss012BasicMO {

    @Inject
    private Tbuss012DAOImp tbuss012DAOImp;

    @Inject
    private Tbuss013MO tbuss013MO;


    @Override
    public Tbuss012VO insertGoal(Tbuss012VO tbuss012VO) {
        return tbuss012DAOImp.insertWith(tbuss012VO,"tbuss013VOS");
    }

    @Override
    public Tbuss012VO fetchByID(Integer goal_id) {
        return tbuss012DAOImp.fetchByID(goal_id);
    }

    @Override
    public Tbuss012VO fetchTransByID(Integer goal_id) {
        return tbuss012DAOImp.fetchTransByIdCnd(goal_id,"tbuss013VOS",null);
    }

    @Override
    public Tbuss012VO fetchTransByVO(Tbuss012VO tbuss012VO) {
        return tbuss012DAOImp.fetchLinks(tbuss012VO,"tbuss013VOS",null);
    }

    @Override
    public void updateOkr(final Integer okid, final List<Tbuss012VO> tbuss012VOS) {
        final List<Tbuss012VO> tbuss012VOList = tbuss012DAOImp.queryByCnd(Cnd.where("okid","=",okid));
        Trans.exec(new Atom() {
            @Override
            public void run() {
                for (Tbuss012VO tbuss012VO:tbuss012VOList){
                    tbuss012VO = tbuss012DAOImp.fetchLinks(tbuss012VO,"tbuss013VOS",null);
                    tbuss012DAOImp.deleteLinks(tbuss012VO,"tbuss013VOS");
                    tbuss012DAOImp.delete(tbuss012VO);
                }
                for(Tbuss012VO tbuss012VO:tbuss012VOS){
                    tbuss012VO.setOkid(okid);
                    tbuss012DAOImp.insertWith(tbuss012VO,"tbuss013VOS");
                }
            }
        });
    }

    @Override
    public Integer markGoal(final List<Map<String, Float>> scores) {
        Trans.exec(new Atom() {
            @Override
            public void run() {
                for(Map<String,Float> score:scores){
                    Integer goal_id = score.get("id").intValue();
                    Float grade = score.get("value");
                    tbuss012DAOImp.markGoal(goal_id,grade);
                }
            }
        });
        return null;
    }
}
