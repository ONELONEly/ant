package com.gree.ant.dao;

import com.gree.ant.vo.util.ContractPayPlanVO;

import java.util.List;

public interface ContractPayPlanDAO extends BaseDAO<ContractPayPlanVO> {

    public List<ContractPayPlanVO> queryContractPayPlanInfor(String coid);
}
