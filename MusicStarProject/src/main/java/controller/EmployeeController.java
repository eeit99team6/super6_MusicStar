package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import model.bean.EmployeeBean;
import model.dao.EmployeeDAO;
import model.service.EmployeeService;

@Controller
public class EmployeeController
{
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "/backEndLogin", method = RequestMethod.POST)
	public String employeeLogin(HttpSession session,@RequestParam(value = "employee_name", required = false) String employee_name, 
			EmployeeBean bean,BindingResult bindingResult, Model model)
	{

		Map<String, String> errors = new HashMap<>();
		model.addAttribute("errors", errors);

		if (bindingResult != null && bindingResult.hasErrors())
		{
			if (bindingResult.getFieldErrorCount("employee_id") != 0
					|| bindingResult.getFieldErrorCount("employee_password") != 0)
			{
				errors.put("login_errors", "帳號密碼有誤");
			}
		}

		if (!errors.isEmpty() && errors != null)
		{
			return "f.employeeLogin.notOK";
		}

		EmployeeBean result = employeeService.login(bean.getEmployee_id(), bean.getEmployee_password());
		if (result != null)
		{   session.setAttribute("backEndloginOK", result);
			model.addAttribute("backEndloginOKResult", result);
			return "r.employeeLogin.ok";
		} else
		{
			errors.put("login_errors", "帳號密碼有誤");
			return "f.employeeLogin.notOK";
		}
	}

    
	@RequestMapping(value="/backEndLogOut")
	public String empoyeeLogout(HttpSession session) {
		session.removeAttribute("backEndloginOK");
		return "f.employeeLogin.notOK";
	}

}
