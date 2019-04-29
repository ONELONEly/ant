package com.gree.ant.dao;

import com.gree.ant.vo.util.ContractVO;

import java.util.List;

public interface ContractDAO extends BaseDAO<ContractVO>{
    public List<ContractVO> queryContractInfor(String coid);
}
