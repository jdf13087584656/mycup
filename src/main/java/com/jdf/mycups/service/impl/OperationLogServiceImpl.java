package com.jdf.mycups.service.impl;

import com.jdf.mycups.dao.OperationLogDao;
import com.jdf.mycups.dao.po.OperationLog;
import com.jdf.mycups.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    OperationLogDao logDao;
    @Override
    public int addLog(OperationLog operationLog) {
        int i = logDao.addLog(operationLog);
        return i;
    }
}
