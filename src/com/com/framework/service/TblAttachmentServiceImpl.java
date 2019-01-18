package com.framework.service;

import com.framework.entity.TblAttachment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;

@Service
@Transactional(rollbackFor = Exception.class)
public class TblAttachmentServiceImpl extends BasServiceImpl{

    public TblAttachment get(Integer id){
        return (TblAttachment) jpaBasDao.get(TblAttachment.class,id);
    }
}
