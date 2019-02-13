package com.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TxDemo10ApplicationTests {

	/*@Autowired
	DataSource dataSource;*/

	@Autowired
	JdbcTemplate jdbcTemplate;


	@Test
	public void contextLoads() {
		List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * FROM tbl_employee");
		System.out.println(maps);
	}




}
