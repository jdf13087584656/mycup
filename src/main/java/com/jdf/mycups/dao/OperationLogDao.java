package com.jdf.mycups.dao;

import com.jdf.mycups.dao.po.OperationLog;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationLogDao {

   int addLog(OperationLog operationLog);

}
