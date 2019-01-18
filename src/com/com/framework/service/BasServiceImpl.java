package com.framework.service;

import com.framework.dao.JdbcTemplateDaoImpl;
import com.framework.dao.JpaDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class BasServiceImpl {

    @Autowired
    public JpaDaoImpl jpaBasDao;



    @Autowired
    public JdbcTemplateDaoImpl jdbcTemplateDao;
}
