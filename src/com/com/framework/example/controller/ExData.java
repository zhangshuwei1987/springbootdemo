package com.framework.example.controller;

import com.framework.entity.BasUser;
import com.framework.example.service.ExService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/ex-data",method = {RequestMethod.POST,RequestMethod.GET})

public class ExData {
	
	//protected CustomHibernateDaoImpl jsonTagDao;
	@Autowired
	private ExService exService;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/find-by-id")	
	public BasUser dofindById(@RequestParam(value = "id")Integer id){
		return null;
		
		//List<BasUser> buList = jsonTagDao.find("from BasUser where id = ? ", id);
		//return buList.get(0);
	}
	
	@RequestMapping(value="/jdbc-find-all")
	public List<Map<String,Object>> jdbcFindAll(){
		logger.info("做了啥？do-jdbc-find-all");
		return exService.useJdbcTemplate();
	}
	
	/*@RequestMapping(value="/jpa-test")
	public List<TblStaffInfo> jpaTest(){
		
		return exampleService.useJpa();
	}
	*/
	@RequestMapping(value="/jpa-jdbc-test")
	public List<BasUser> jpaJdbcTest(){
		
		return exService.useJpaJdbc();
	}
}
