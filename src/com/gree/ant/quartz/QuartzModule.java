package com.gree.ant.quartz;

import com.gree.ant.quartz.job.ButterFlyUserJob;
import org.nutz.integration.quartz.QuartzJob;
import org.nutz.integration.quartz.QuartzManager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.quartz.*;

@IocBean
public class QuartzModule {

    @Inject
    private QuartzManager quartzManager;

    public void add(){
        JobKey jobKey = new JobKey("butterFlyUserKey","feiYun");
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("butterFlyUserTrigger","feiYun").withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?")).startNow().build();
        JobDetail jobDetail = JobBuilder.newJob(ButterFlyUserJob.class).withIdentity("butterFlyUserJob","feiYun").build();
        QuartzJob quartzJob = new QuartzJob(jobKey,trigger,jobDetail);
        quartzManager.add(quartzJob);
    }
}
