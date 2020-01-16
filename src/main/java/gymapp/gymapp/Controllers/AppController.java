package gymapp.gymapp.Controllers;

import gymapp.gymapp.Models.Employee;
import gymapp.gymapp.Models.Package;
import gymapp.gymapp.Models.User;
import gymapp.gymapp.Repositories.UserRepository;
import gymapp.gymapp.Services.EmployeeService;
import gymapp.gymapp.Services.PackageService;
import gymapp.gymapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;


@Controller
public class AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/listPageable", method = RequestMethod.GET)
    Page<User> employeesPageable(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    @RequestMapping({"", "/", "/home_admin"})
    public String showAdminPage(Model model) {
        return "home_admin";
    }

    @RequestMapping("/dashboard")
    public String viewDashboardPage(Model model){
        List<User> listUsers = userService.findAll();
        while (listUsers.size()>5){
            listUsers.remove(0);
        }
        List<Employee> listEmployees = employeeService.findAll();
        while (listEmployees.size()>5){
            listEmployees.remove(0);
        }
        List<Package> listPackages = packageService.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listPackages", listPackages);
        return "dashboard";
    }

    @RequestMapping("/contact")
    public String showContactPage(Model model) {
        return "contact";
    }

}
