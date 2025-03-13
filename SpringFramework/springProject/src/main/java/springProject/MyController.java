package springProject;

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

	
	
	  @RequestMapping("/tr")
	    public String getRequest() {
	        return "trainer.jsp"; 
	    }
	  
	@GetMapping("/regg")
	 public ModelAndView registerEmployee(@ModelAttribute Trainerr trainer, ModelAndView mv) {
    	
	EntityManager em = Persistence.createEntityManagerFactory("mvc").createEntityManager();
	
    	EntityTransaction et=em.getTransaction();
    	
    	et.begin();
    	
    	em.persist(trainer);
    	
    	et.commit();
    	
    	mv.addObject("message","Trainer register sucessfull with id: "+trainer.getId());
        mv.setViewName("DisplayTrainer.jsp");
        return mv;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
