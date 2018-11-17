package com.gree.ant.mo;

import com.gree.MyNutTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Tasks;

@RunWith(MyNutTestRunner.class)
@IocBean
public class timeTest {

    @Inject
    private Cbase015MO cbase015MO;

    @Inject("refer:cbase013MO")
    private Cbase013MO cbase013MO;

    @Test
    public void isnert() throws Exception {
    }

    @Test
    public void deleteByTaid() throws Exception {


            Tasks.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    int code = cbase013MO.tongbuDSSystem();
                }
            },10);
            Lang.quiteSleep(60*1000);
        }


}