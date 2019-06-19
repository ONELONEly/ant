package com.gree.ant.vo.util;

import java.util.List;

public class ContractInforVO {

    private List<ContractVO> contractVOList;

    private List<ContractPayPlanVO> contractPayPlanVOList;

    public List<ContractVO> getContractVOList() {
        return contractVOList;
    }

    public void setContractVOList(List<ContractVO> contractVOList) {
        this.contractVOList = contractVOList;
    }

    public List<ContractPayPlanVO> getContractPayPlanVOList() {
        return contractPayPlanVOList;
    }

    public void setContractPayPlanVOList(List<ContractPayPlanVO> contractPayPlanVOList) {
        this.contractPayPlanVOList = contractPayPlanVOList;
    }
}
