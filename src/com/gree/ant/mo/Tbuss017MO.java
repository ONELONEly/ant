package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.TBuss017DAOImp;
import com.gree.ant.mo.basic.BasicMO;
import com.gree.ant.mo.basic.TBuss017BasicMO;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.TBuss017VO;
import com.gree.ant.vo.util.ExportGradeOkrVO;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class Tbuss017MO implements BasicMO<TBuss017VO>, TBuss017BasicMO {

    @Inject
    private TBuss017DAOImp tBuss017DAOImp;

    @Inject
    private BaseMoFactory baseMoFactory;

    @Override
    public TBuss017VO insertByVO(TBuss017VO vo) {
        return tBuss017DAOImp.insert(vo);
    }

    @Override
    public List<TBuss017VO> insertByVOS(List<TBuss017VO> vos) {
        return tBuss017DAOImp.insert(vos);
    }

    @Override
    public Integer deleteByVO(TBuss017VO vo) {
        return tBuss017DAOImp.delete(vo);
    }

    @Override
    public Integer updateByVO(TBuss017VO vo) {
        return tBuss017DAOImp.update(vo);
    }

    @Override
    public Integer updateByVOS(List<TBuss017VO> vos) {
        return tBuss017DAOImp.update(vos);
    }

    @Override
    public Integer countByPdatAcco(String pdat, String acco) {
        Cnd cnd = Cnd.NEW();
        cnd = cnd.and("pdat","=",pdat).and("acco","=",acco);
        return tBuss017DAOImp.countByCnd(cnd);
    }

    @Override
    public List<ExportGradeOkrVO> queryAllByPdatAcco(String pdat, String acco) {
        Cnd cnd = Cnd.NEW();
        Cbase000MO cbase000MO = baseMoFactory.getCbase000MO();
        cnd = cnd.and("pdat","=",pdat).and("acco","=",acco);
        List<TBuss017VO> tBuss017VOS = tBuss017DAOImp.queryByCnd(cnd.desc("to_number(score)"));
        List<ExportGradeOkrVO> gradeVOS = new ArrayList<>();
        for (TBuss017VO tBuss017VO:tBuss017VOS){
            Cbase000VO cbase000VO = cbase000MO.ftechUserDC(tBuss017VO.getCsid());
            gradeVOS.add(new ExportGradeOkrVO(cbase000VO.getCPID(),cbase000VO.getDSCA(), Double.parseDouble(tBuss017VO.getScore()),tBuss017VO.getStage()));
        }
        return gradeVOS;
    }

    @Override
    public TBuss017VO fetchByUsidPdat(String usid, String pdat) {

        Cnd cnd = Cnd.NEW();
        cnd = cnd.and("pdat","=",pdat).and("csid","=",usid);
        List<TBuss017VO> tBuss017VOS = tBuss017DAOImp.queryByCnd(cnd);
        TBuss017VO tBuss017VO = new TBuss017VO();
        if (tBuss017VOS.size() > 0){
            tBuss017VO = tBuss017VOS.get(0);
        }
        return tBuss017VO;
    }
}
