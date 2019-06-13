package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);//where state = 2 order by createtime desc
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);//where state != 0 order by createtime desc
}
