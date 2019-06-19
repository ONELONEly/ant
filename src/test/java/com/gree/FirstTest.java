package com.gree;
import com.gree.ant.dao.daoImp.ButterFlyDAOImp;
import com.gree.ant.util.DoubleUtil;
import com.gree.ant.util.SnowFlake;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(MyNutTestRunner.class)
@IocBean
public class FirstTest extends Assert{

    @Inject("refer:daoFX")
    private Dao dao;

    @Inject
    private ButterFlyDAOImp butterFlyDAOImp;

    @Test
    public void firstTest() throws Exception {
        long avg = 0;
        for (int k = 0; k < 10; k++){
            List<Callable<Long>> partitions = new ArrayList<>();
            final SnowFlake flake = SnowFlake.get();
            for (int i = 0; i < 1400000; i++) {
                partitions.add(flake::nextId);
            }
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            try {
                long s = System.currentTimeMillis();
                executor.invokeAll(partitions, 10000, TimeUnit.SECONDS);
                long s_avg = System.currentTimeMillis() - s;
                avg += s_avg;
                System.out.println("需要：" + s_avg / 1.0e3 + "s");
                executor.shutdown();
            } catch (Exception e) {
              e.printStackTrace();
            }

            System.out.println("平均完成时间需要：" + avg / 10 / 1.0e3 + "秒");
        }
    }
}
