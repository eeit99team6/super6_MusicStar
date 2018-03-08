package model.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.EmployeeBean;
import model.dao.EmployeeDAO;

@Service
@Transactional
public class EmployeeService
{
	@Autowired
	EmployeeDAO employeeDao; 
	
	// login 
	public EmployeeBean login(String id, String password) {
		EmployeeBean bean = employeeDao.select(id);
		if(bean!=null && bean.getEmployee_password().equals(password) ) {
			return bean;
		}
		return null;
	}
	
}
