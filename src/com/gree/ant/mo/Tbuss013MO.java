package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.Tbuss013DAOImp;
import com.gree.ant.mo.basic.Tbuss013BasicMO;
import com.gree.ant.vo.Tbuss012VO;
import com.gree.ant.vo.Tbuss013VO;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.util.List;
import java.util.Map;

@IocBean
public class Tbuss013MO implements Tbuss013BasicMO {

    @Inject
    private Tbuss013DAOImp tbuss013DAOImp;

    @Override
    public List<Tbuss013VO> insertTask(List<Tbuss013VO> tbuss013VOS) {
        return tbuss013DAOImp.insert(tbuss013VOS);
    }

    @Override
    public Integer markTask(final List<Map<String, Object>> scores) {
        Trans.exec(new Atom() {
            @Override
            public void run() {
                for(Map<String,Object> score:scores){
                    Integer task_id = Integer.parseInt(score.get("id").toString());
                    Float grade = Float.valueOf(score.get("value").toString());
                    tbuss013DAOImp.markTask(task_id,grade);
                }
            }
        });
        return null;
    }
}
