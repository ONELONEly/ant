package com.gree.ant.util;

import com.gree.ant.vo.Tbuss011VO;
import com.gree.ant.vo.Tbuss012VO;
import com.gree.ant.vo.Tbuss013VO;
import com.gree.ant.vo.request.GoalVO;
import com.gree.ant.vo.request.OkrVO;
import com.gree.ant.vo.request.TaskVO;

import java.util.ArrayList;
import java.util.List;

public class OKRUtil {

    private OkrVO okrVO;
    private String msg;
    private Integer code;

    public OKRUtil(OkrVO okrVO) {
        if(okrVO != null) {
            this.okrVO = okrVO;
        }else{
            this.okrVO = new OkrVO();
        }
    }

    public static OkrVO convertToOkr(Tbuss011VO tbuss011VO){
        OkrVO okrVO = new OkrVO();
        if(tbuss011VO != null) {
            if (tbuss011VO.getTbuss012VOS() != null) {
                List<GoalVO> goalVOS = new ArrayList<>();
                for (Tbuss012VO tbuss012VO : tbuss011VO.getTbuss012VOS()) {
                    GoalVO goalVO = tbuss012VO.getGoalVO();
                    if (tbuss012VO.getTbuss013VOS() != null) {
                        List<TaskVO> taskVOS = new ArrayList<>();
                        for (Tbuss013VO tbuss013VO : tbuss012VO.getTbuss013VOS()) {
                            taskVOS.add(tbuss013VO.getTaskVO());
                        }
                        goalVO.setTasks(taskVOS);
                    }
                    goalVOS.add(goalVO);
                }
                okrVO.setGoals(goalVOS);
            }
        }
        return okrVO;
    }

    public Tbuss011VO getT11(){
        return this.okrVO.getOkrManager();
    }

    public List<Tbuss012VO> getT12(){
        List<GoalVO> goalVOS = this.okrVO.getGoals();
        List<Tbuss012VO> tbuss012VOS = new ArrayList<>();
        if(goalVOS != null){
            for(GoalVO goalVO:goalVOS){
                Tbuss012VO tbuss012VO = goalVO.getTbuss012VO();
                List<Tbuss013VO> tbuss013VOList = new ArrayList<>();
                List<TaskVO> tasks = goalVO.getTasks();
                if(tasks != null) {
                    for (TaskVO taskVO : tasks) {
                        Tbuss013VO tbuss013VO = taskVO.formatTask();
                        tbuss013VOList.add(tbuss013VO);
                    }
                }else{
                    this.msg = "OKR目标任务为空，请确认录入有效任务！";
                }
                tbuss012VO.setTbuss013VOS(tbuss013VOList);
                tbuss012VOS.add(tbuss012VO);
            }
        }else{
           this.msg = "OKR目标为空，请录入有效目标！";
        }
        return tbuss012VOS;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
