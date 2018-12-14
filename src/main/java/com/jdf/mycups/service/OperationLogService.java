package com.jdf.mycups.service;

import com.jdf.mycups.dao.po.OperationLog;

public interface OperationLogService {
    int addLog(OperationLog operationLog);
}
