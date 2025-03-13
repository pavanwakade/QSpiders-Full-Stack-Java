package springMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping("/emp")
    public String getRequest() {
        return "emp.jsp"; // Direct JSP path
    }

    @GetMapping("/reg")
    public ModelAndView registerEmployee(@ModelAttribute Employee e, ModelAndView mv) {
        mv.addObject("id", e.getId());
        mv.addObject("name", e.getName());
        mv.addObject("phone", e.getPhone());
        mv.setViewName("DisplayEMP.jsp");
        return mv;
    }
}
