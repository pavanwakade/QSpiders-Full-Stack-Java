package spring.MVC;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

//    @RequestMapping("/emp")
//    public String getRequest() {
//        return "emp.jsp"; // Direct JSP path
//    }
//
//    @GetMapping("/reg")
//    public ModelAndView registerEmployee(@ModelAttribute Employee e, ModelAndView mv) {
//        mv.addObject("id", e.getId());
//        mv.addObject("name", e.getName());
//        mv.addObject("phone", e.getPhone());
//        mv.setViewName("DisplayEMP.jsp");
//        return mv;
//    }
//	
	
	
	
	 @RequestMapping("/emp")
	    public String getRequest() {
	        return "emp.jsp"; // Direct JSP path
	    }
	  
	@GetMapping("/reg")
 public ModelAndView registerEmployee(@ModelAttribute Employee emp, ModelAndView mv) {
 	
	EntityManager em = Persistence.createEntityManagerFactory("mvc").createEntityManager();
	
	  mv.addObject("id", emp.getId());
    mv.addObject("name", emp.getName());
    mv.addObject("phone", emp.getPhone());
	
	
 	EntityTransaction et=em.getTransaction();
 	
 	et.begin();
 	
 	em.persist(emp);
 	
 	et.commit();
 	
 	mv.addObject("message","Trainer register sucessfull with id: "+emp.getId());
     mv.setViewName("DisplayEMP.jsp");
     return mv;
 }
	
	
	
	
	
}
