package com.xiaobai.service;

import com.xiaobai.bean.Employee;
import com.xiaobai.bean.EmployeeExample;
import com.xiaobai.bean.EmployeeExample.Criteria;
import com.xiaobai.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    public List<Employee> getAll() {

        return employeeMapper.selectByExampleWithDept(null);
    }


    public void saveEmp(Employee employee) {
        // 有选择的插入
        employeeMapper.insertSelective(employee);
    }


    public boolean checkUser(String empName) {

        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        // 记录数
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }


    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
    * @Classname EmployeeService
    * @Description 类方法说明：员工更新
    */
    public void updateEmp(Employee employee) {

        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
    * @Classname EmployeeService
    * @Description 类方法说明：员工删除
    */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
    * @Classname EmployeeService
    * @Description 类方法说明：批量删除
    */
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
