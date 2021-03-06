package com.gree.ant.quartz.job;

import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.mo.Tbuss003MO_Ds;
import com.gree.ant.util.DateUtil;
import com.gree.ant.util.SyncButterFlyData;
import org.apache.log4j.Logger;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

@IocBean
public class ButterFlyUserJob implements Job {

    @Inject("refer:daoFX")
    private Dao dao;

    @Inject
    private ButterFlyDAOImp butterFlyDAOImp;

    @Inject
    private Tbuss003MO_Ds tbuss003MO_Ds;

    private Logger logger = Logger.getLogger(ButterFlyUserJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("执行同步金蝶用户开始，时间为" +  DateUtil.formatYMDHMSDate(new Date()));
//        SyncButterFlyData syncButterFlyData = SyncButterFlyData.createSyncDataUtil();
//        syncButterFlyData.carrySync(dao,butterFlyDAOImp);
        tbuss003MO_Ds.synchronizationDSSystem();
        logger.info("执行同步金蝶用户结束，时间为 " + DateUtil.formatYMDHMSDate(new Date()));
    }
}
