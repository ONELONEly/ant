package com.gree.ant.mo;

import com.gree.ant.dao.Tbuss014DAO;
import com.gree.ant.mo.basic.Tbuss014BasicMO;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.MailUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Tbuss014VO;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

@IocBean
public class Tbuss014MO implements Tbuss014BasicMO{

    @Inject
    private Tbuss014DAO tbuss014DAO;

    @Override
    public Tbuss014VO insertByVO(Tbuss014VO tbuss014VO) {
        return tbuss014DAO.insert(tbuss014VO);
    }

    @Override
    public Tbuss014VO fetchByRaid(String raid) {
        return tbuss014DAO.fetchByName(raid);
    }

    @Override
    public void copyByRaids(final String[] raids, final String usid) {
        Trans.exec(Connection.TRANSACTION_READ_COMMITTED,new Atom() {
            @Override
            public void run() {
                if(raids != null){
                    for(String raid:raids){
                        Tbuss014VO tbuss014VO = fetchByRaid(raid);
                        tbuss014VO.setRaid("JK"+ FileUtil.createFileUtil().getRandomName());
                        tbuss014VO.setStat(0);
                        tbuss014VO.setCdat(new Date());
                        tbuss014VO.setUsid(usid);
                        tbuss014DAO.insert(tbuss014VO);
                    }
                }
            }
        });

    }

    @Override
    public void backByRaids(final String[] raids) {
        Trans.exec(Connection.TRANSACTION_READ_COMMITTED,new Atom() {
            @Override
            public void run() {
                if(raids != null){
                    for(String raid:raids){
                        Tbuss014VO tbuss014VO = fetchByRaid(raid);
                        tbuss014VO.setStat(2);
                        tbuss014DAO.update(tbuss014VO);
                        MailUtil.sendRequireBackmail(tbuss014VO,tbuss014VO.getUsid());
                    }
                }
            }
        });
    }

    @Override
    public Integer deleteByRaids(final String[] raids) {
        final int[] code = {0};
        Trans.exec(new Atom() {
            @Override
            public void run() {
                if (raids != null) {
                    for (String raid : raids) {
                        code[0] = tbuss014DAO.deleteByName(raid);

                    }
                }
            }
        });
        return code[0];
    }

    @Override
    public Integer deleteByRaid(String raid) {
        return tbuss014DAO.deleteByName(raid);
    }

    @Override
    public Integer updateByVO(Tbuss014VO tbuss014VO) {
        return tbuss014DAO.update(tbuss014VO);
    }

    @Override
    public QueryResult queryAllByMsg(Integer pageNumber, Integer pageSize, String msg,Integer stat,String usid,String csid) {
        Cnd cnd = Cnd.NEW();
        if(StringUtil.checkString(msg)){
            cnd.and("synonam","like","%"+msg+"%").and("unam","like","%"+msg+"%")
                    .and("cnam","like","%"+msg+"%").and("titl","like","%"+msg+"%");
        }
        if(StringUtil.checkString(usid)){
            cnd.and("usid","=",usid);
        }
        if(StringUtil.checkString(csid)){
            cnd.and("csid","=",csid);
            cnd.and("stat","!=",1);
        }
        if(stat != null){
            cnd.and("stat","=",stat);
        }
        int count = tbuss014DAO.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize,pageNumber,count);
        List<Tbuss014VO> tbuss014VOList = tbuss014DAO.queryAllByCndPager(cnd,pager);
        return TableUtil.formatQueryResult(pager,tbuss014VOList);
    }
}
