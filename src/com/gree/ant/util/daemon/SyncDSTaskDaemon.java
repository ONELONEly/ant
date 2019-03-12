package com.gree.ant.util.daemon;


import com.gree.MainSetup;
import com.gree.ant.exception.KellyException;
import com.gree.ant.mo.Tbuss003MO_Ds;
import com.gree.ant.vo.Cbase000VO;
import com.gree.ant.vo.Tbuss003VO;
import com.gree.ant.vo.daemonVO.SyncDSTaskVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class SyncDSTaskDaemon extends TimerTask {
    private Logger logger = LoggerFactory.getLogger(SyncDSTaskDaemon.class);

    public static final List<SyncDSTaskVO> taskList = new ArrayList<>();

    @Override
    public void run() {
        synchronized (taskList) {
            logger.error("长度为{}",taskList.size());
            if (taskList.size() > 0) {
                int okay = taskList.size();
                for (int i = okay - 1;i >= 0;i--){
                    SyncDsTask(taskList.get(i));
                    taskList.remove(i);
                }
            }
        }
    }

    private void SyncDsTask(SyncDSTaskVO syncDSTaskVO){
        Tbuss003VO tbuss003VO = syncDSTaskVO.getTbuss003VO();
        Cbase000VO cbase000VO = syncDSTaskVO.getCbase000VO();
        Tbuss003MO_Ds tbuss003MO_ds = MainSetup.tbuss003MO_ds;
        try {
            tbuss003MO_ds.insertBugJieKou(tbuss003VO,cbase000VO);   //TODO 之后继续研究是否失败之后继续完成提交
        } catch (Exception e) {
            Integer code;
            String errorMsg;
            if(e instanceof KellyException){
                KellyException exception = (KellyException)e;
                code = exception.getCode();
                errorMsg = exception.getMessage();
            }else{
                code = 500;
                errorMsg = "服务器异常";
            }
            try {
                logger.error("同步DS失败，该用户：{}，所属任务ID为：{},错误码为：{},错误信息为：{}，" +
                        "转交直接操作数据库进行处理，【注意对响应任务进行监督】",cbase000VO.getUSID(),tbuss003VO.getTaid(),code,errorMsg);
                tbuss003MO_ds.insertBug(tbuss003VO,cbase000VO);
            } catch (Exception kelly) {
                logger.error("同步DS失败，【执行直接对数据库进行处理】，该用户：{}，所属任务ID为：{}，错误码为：{},错误信息为：{}",cbase000VO.getUSID(),tbuss003VO.getTaid(),code,errorMsg);
            }
        }
    }
}
