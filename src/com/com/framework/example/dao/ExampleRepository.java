package com.framework.example.dao;

import java.util.List;

import com.zhangsw.springbootdemo.entity.TblStaffInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * spring-data-jpa持久化方法
 * 
 */
@Repository
public interface ExampleRepository extends JpaRepository<TblStaffInfo, Integer>{

	@Query(value="select tsi.id from TblStaffInfo tsi  where tsi.name = :name ")
	public List<TblStaffInfo> findbyClassName(@Param("name") String name);
	
	@Query(value="from TblStaffInfo tsi  where tsi.groupStaffCode = :groupStaffCode ")
	public List<TblStaffInfo> doFindbyStaffCode(@Param("groupStaffCode") String groupStaffCode);
}
