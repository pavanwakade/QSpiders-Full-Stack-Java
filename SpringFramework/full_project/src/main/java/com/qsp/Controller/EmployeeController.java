package com.qsp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qsp.dao.Employeees;
import com.qsp.dao.EmployeesDao;

@Controller
public class EmployeeController {

	@RequestMapping("/emp")
	public String getRequest() {
		return "emp.jsp"; // Direct JSP path
	}

	@Autowired
	private EmployeesDao edao;

	@GetMapping("/regemp")
	public ModelAndView registerEmployee(@ModelAttribute Employeees employee, ModelAndView mv) {

		edao.saveEmp(employee);

		mv.addObject("message", "REgister imployee with id :" + employee.getId());

		mv.setViewName("DisplayEMP.jsp");

		return mv;

	}

	@RequestMapping("/getbyid")
	public String getRequeid() {
		return "getbyid.jsp"; // Direct JSP path
	}

	@GetMapping("/displayid")

	public ModelAndView getEmployeeId(@RequestParam(name = "id") int id, ModelAndView mv) {

		Employeees emp = edao.findByID(id);

		mv.addObject("e", emp);

		mv.setViewName("displaybyid.jsp");

		return mv;

	}

	@GetMapping("/findallemp")
	public ModelAndView displayAllEMP(ModelAndView mv) {

		List<Employeees> list = edao.findEmployees();
		mv.addObject("employees", list);

		mv.setViewName("DisplayAll.jsp");
		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView deleteByID(@RequestParam(name = "id") int id, ModelAndView mv) {

		edao.deleteByID(id);

		mv.setViewName("findallemp");

		return mv;
	}

	@GetMapping("/edit") // for update emp
	public ModelAndView displayUpdatingData(@RequestParam(name = "id") int id, ModelAndView mv) {

		Employeees emp = edao.findByID(id);

		mv.addObject("emp", emp);

		mv.setViewName("edit.jsp");

		return mv;

	}

	@GetMapping("/update") // disply updated and all emp
	public ModelAndView upddateByID(@ModelAttribute Employeees emp, ModelAndView mv) {

		edao.updateEmployee(emp);

		mv.setViewName("findallemp");

		return mv;
	}

	@GetMapping("/login")
	public ModelAndView login(@RequestParam(name = "name") String name, @RequestParam(name = "phone") long phone,
			ModelAndView mv) {
		// Fetch employee using name and phone
		Employeees emp = edao.verifybynamephone(name, phone);

		// Check if the employee exists
		if (emp != null) {
			mv.addObject("emp", emp); // Add employee to the model
			mv.setViewName("findallemp"); // Set the view name

			return mv;
		} 
		else {

//			mv.setViewName("emp.jsp");
			mv.setViewName("error.jsp"); // Redirect to an error page or re-render login page
			return mv;
		}

	}

}
