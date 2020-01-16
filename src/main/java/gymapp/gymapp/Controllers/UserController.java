package gymapp.gymapp.Controllers;

import gymapp.gymapp.Models.User;
import gymapp.gymapp.Services.UserService;
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

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping({"", "/"})
    public String viewUsersPage(Model model){
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @RequestMapping("/new")
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/users";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.findById(id);
        mav.addObject("user", user);

        return mav;
    }


    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="new")
    public String newnew(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="delete")
    public String deletedelete(HttpServletRequest request, Model model){
        try{
            if (request.getParameterValues("id") != null){
                for(String id : request.getParameterValues("id")){
                    userService.deleteById(Integer.parseInt(id));
                }
            }
            return "redirect:/users";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            List<User> listUsers = userService.findAll();
            model.addAttribute("listUsers", listUsers);
            return "users";
        }
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="edit")
    public ModelAndView editedit(HttpServletRequest request, Model model){
        ModelAndView mav = new ModelAndView("edit_user");
        if (request.getParameterValues("id") != null){
            for(String id : request.getParameterValues("id")){
                User user = userService.findById(Integer.parseInt(id));
                mav.addObject("user", user);
            }
            return mav;
        }
        mav = new ModelAndView("users");
        List<User> listUsers = userService.findAll();
        //model.addAttribute("listUsers", listUsers);
        mav.addObject("listUsers", listUsers);
        return mav;
    }

}
