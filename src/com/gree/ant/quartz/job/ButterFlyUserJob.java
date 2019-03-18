package com.gree.ant.quartz.job;

import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.util.SyncButterFlyData;
import com.sun.star.bridge.oleautomation.Date;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@IocBean
public class ButterFlyUserJob implements Job {

    @Inject("refer:daoFX")
    private Dao dao;

    @Inject
    private ButterFlyDAOImp butterFlyDAOImp;

    private Logger logger = LoggerFactory.getLogger(ButterFlyUserJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("执行同步金蝶用户开始，时间为{}", new Date());
        SyncButterFlyData syncButterFlyData = SyncButterFlyData.createSyncDataUtil();
        syncButterFlyData.carrySync(dao,butterFlyDAOImp);
        logger.info("执行同步金蝶用户结束，时间为{}",new Date());
    }
}
