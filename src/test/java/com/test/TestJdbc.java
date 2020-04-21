package com.test;

import com.App;
import com.github.pagehelper.PageHelper;
import com.run.mapper.UserMapper;
import com.run.model.Department;
import com.run.model.User;
import com.winter.mapper.EmployeeMapper;
import com.winter.model.Employee;
import com.yxr.service.EmployeeService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestJdbc {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
    //
    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    EmployeeService service;

    @Autowired
    //若是不声明具体的子类，那么默认使用@Primary标记的，
    // 那么就会出现找不到反射类的异常
    @Qualifier("mySpringbootSqlSessionTemplate")
    SqlSessionTemplate mySpringbootSqlSessionTemplate;


    @Test
    public void TestTranscation() {
        Employee employee = new Employee();
        employee.setdId(123);
        employee.setLastName("雪瑞");
        int i = 0;
//        try {
        i = service.addEmployee(employee);
//        } catch (Exception e) {
//            System.out.println("---->返回失败。");
//        }

        System.out.println("插入：" + i);
    }


    //    @Test
//    public void contextLoads() {
//        System.out.println("------------->"+jdbcTemplate);
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from tbl_employee");
//        System.out.println(maps);
//    }
//
//    @Test
//    public void testMybatis() {
//        int deleteByPrimaryKey = employeeMapper.deleteByPrimaryKey(1);
//        System.out.println(deleteByPrimaryKey);
//    }
//
    @Test
    public void testPageHelper() {
        //将参数封装Page对象，放入ThreadLocal中
        PageHelper.startPage(1, 3);
        //在sql运行的过程中，执行Interceptor拦截器类，
        // 拦截StatementHandler，重写sql
        List<Employee> employees = employeeMapper.getEmployees();

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println("--->" + employee);
        }
    }

    @Test
    public void testUserMapper() {
        User user = new User();
        Department department = new Department();
        department.setDeptId(1);
        user.setUserName("abc");
        user.setAge(12);
        user.setGender(0);
        user.setDept(department);
        int i = userMapper.batchInsertUserInfo(user);
        System.out.println("影响的行数 ： " + i);
    }

    @Test
    public void testBatchUser() {
        long startTime = System.currentTimeMillis();
        SqlSession sqlSession = mySpringbootSqlSessionTemplate.getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            Department department = new Department();
            department.setDeptId(1);
            user.setUserName(UUID.randomUUID().toString().substring(0,4));
            user.setAge(12);
            user.setGender(0);
            user.setDept(department);
            mapper.batchInsertUserInfo(user);
        }
        sqlSession.commit();
    }

}
