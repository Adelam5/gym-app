package gymapp.gymapp.Controllers;

import gymapp.gymapp.Models.Employee;
import gymapp.gymapp.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/employees")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping({"", "/"})
    public String viewEmployeesPage(Model model){
        List<Employee> listEmployees = employeeService.findAll();
        model.addAttribute("listEmployees", listEmployees);
        return "employees";
    }

    @RequestMapping("/new")
    public String showNewEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_employee");
        Employee employee = employeeService.findById(id);
        mav.addObject("employee", employee);

        return mav;
    }


    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="new")
    public String newnew(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "new_employee";
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="delete")
    public String deletedelete(HttpServletRequest request, Model model){
        try{
            if (request.getParameterValues("id") != null){
                for(String id : request.getParameterValues("id")){
                    employeeService.deleteById(Integer.parseInt(id));
                }
            }
            return "redirect:/employees";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            List<Employee> listEmployees = employeeService.findAll();
            model.addAttribute("listEmployees", listEmployees);
            return "employees";
        }
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="edit")
    public ModelAndView editedit(HttpServletRequest request, Model model){
        ModelAndView mav = new ModelAndView("edit_employee");
        if (request.getParameterValues("id") != null){
            for(String id : request.getParameterValues("id")){
                Employee employee = employeeService.findById(Integer.parseInt(id));
                mav.addObject("employee", employee);
            }
            return mav;
        }
        mav = new ModelAndView("employees");
        List<Employee> listEmployees = employeeService.findAll();
        mav.addObject("listEmployees", listEmployees);
        return mav;
    }

}
