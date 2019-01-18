package com.framework.example.service;

import com.framework.core.context.ApplicationContextProvider;
import com.framework.service.BasServiceImpl;
import com.zhangsw.springbootdemo.entity.TblStaffInfo;
import com.framework.entity.BasUser;
import com.framework.example.dao.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
@Transactional(rollbackFor = Exception.class)
public class ExService extends BasServiceImpl {


	@Autowired
	private ExampleRepository exampleRepository;
	
	public List<TblStaffInfo> useJpa(String name) {
		List<TblStaffInfo> twTblStaffInfoList = null;
		
        try {
        	twTblStaffInfoList = exampleRepository.findbyClassName(name);
        	
        } catch (Exception e) {
        }
        return twTblStaffInfoList;
    }

	@Transactional
	public List<Map<String,Object>> useJdbcTemplate() {

        return this.jdbcTemplateDao.getJdbcTemplate().queryForList("select * from TblStaffInfo");
    }

	public List<BasUser> useJpaJdbc(){



		BasUser buJpa = new BasUser();
		buJpa.setUserName("useJpaHibernate");
		buJpa.setCreateTime(new Date());
		this.jpaBasDao.save(buJpa);

		this.jdbcTemplateDao.getJdbcTemplate().update("update BasUser set status = 1");

		Object obj = ApplicationContextProvider.getBean("sessionFactory");

		//if(true)throw new ZswException("99","抛出异常，测试事务回滚");
		/*BasUser bu = new BasUser();
		bu.setUserName("useHibernate");

		this.hibernateBasDao.save(bu);*/



		return null;
	}

	@Async("asyncServiceExecutor")
	public void doAsyncServiceExecutor(String param){
		System.out.println("do-async-service-executor");
	}

}
