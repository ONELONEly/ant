package com.gree.ant.mo;

import com.gree.ant.dao.daoImp.ConferenceDAOImp;
import com.gree.ant.mo.basic.BasicMO;
import com.gree.ant.mo.basic.ConferenceBasicMO;
import com.gree.ant.util.DateUtil;
import com.gree.ant.util.FileUtil;
import com.gree.ant.util.StringUtil;
import com.gree.ant.util.TableUtil;
import com.gree.ant.vo.Conference;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;

@IocBean
public class ConferenceMO implements BasicMO<Conference>, ConferenceBasicMO {

    @Inject
    private ConferenceDAOImp conferenceDAOImp;

    @Inject
    private BaseMoFactory baseMoFactory;

    @Override
    public Conference insertByVO(Conference vo) {
        return conferenceDAOImp.insert(vo);
    }

    @Override
    public List<Conference> insertByVOS(List<Conference> vos) {
        return conferenceDAOImp.insert(vos);
    }

    @Override
    public Integer deleteByVO(Conference vo) {
        return conferenceDAOImp.delete(vo);
    }

    @Override
    public Integer deleteByName(String name) {
        return conferenceDAOImp.deleteByName(name);
    }

    @Override
    public Integer updateByVO(Conference vo) {
        return conferenceDAOImp.update(vo);
    }

    @Override
    public Integer updateByVOS(List<Conference> vos) {
        return conferenceDAOImp.update(vos);
    }

    @Override
    public QueryResult loadTableData(String usid, String month, Integer week,Integer pageNumber, Integer pageSize) {
        Cnd cnd = Cnd.NEW();
        cnd.and("creator","=",usid);
        if (StringUtil.checkString(month)) {
            cnd.and("month", "=", month);
        }
        if (week != null) {
            cnd.and("week", "=", week);
        }
        Integer recordCount = conferenceDAOImp.countByCnd(cnd);
        Pager pager = TableUtil.formatPager(pageSize, pageNumber, recordCount);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(conferenceDAOImp.queryTableByCndPager(cnd, pager));
        queryResult.setPager(pager);
        return queryResult;
    }

    @Override
    public List<Conference> loadShowData(String acco) {
        Cnd cnd = Cnd.NEW();
        cnd.and("conf.week","=", DateUtil.dayNumber(DateUtil.DateTypeEnum.week_of_year));
        if (StringUtil.checkString(acco) && baseMoFactory.getCbase017MO().fetchByAcco(acco) != null) {
            cnd.and("conf.creator", "in", "select usid from cbase000 where acco = '" + acco+"'");
            return conferenceDAOImp.queryShowByCnd(cnd);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Conference fetchData(String conferenceGuid) {
        Conference conferenceVO = conferenceDAOImp.fetchByName(conferenceGuid);
        conferenceVO.setStartDateTxt(DateUtil.formDateToString(conferenceVO.getStartDate()));
        conferenceVO.setScheduleDateTxt(DateUtil.formDateToString(conferenceVO.getScheduleDate()));
        FileUtil fileUtil = FileUtil.createFileUtil();
        conferenceVO.setPreWeekDoneTxt(fileUtil.convertClob(conferenceVO.getPreWeekDone()));
        conferenceVO.setNowWeekScheduleTxt(fileUtil.convertClob(conferenceVO.getNowWeekSchedule()));
        conferenceVO.setOthersTxt(fileUtil.convertClob(conferenceVO.getOthers()));
        return conferenceVO;
    }

    @Override
    public Boolean checkByWeekProjectId(Integer week, String projectId) {
        return conferenceDAOImp.queryByCnd(Cnd.where("week","=",week).and("project_guid","=",projectId)).size() == 0;
    }
}
