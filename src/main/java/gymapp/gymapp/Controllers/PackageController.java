package gymapp.gymapp.Controllers;

import gymapp.gymapp.Models.Package;
import gymapp.gymapp.Services.PackageService;
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

@RequestMapping("/packages")
@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;

    @RequestMapping({"", "/"})
    public String viewPackagesPage(Model model){
        List<Package> listPackages = packageService.findAll();
        model.addAttribute("listPackages", listPackages);
        return "packages";
    }

    @RequestMapping("/new")
    public String showNewPackagePage(Model model) {
        Package a_package = new Package();
        model.addAttribute("a_package", a_package);
        return "new_package";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePackage(@ModelAttribute("a_package") Package a_package) {
        packageService.save(a_package);

        return "redirect:/packages";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPackagePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_package");
        Package a_package = packageService.findById(id);
        mav.addObject("a_package", a_package);

        return mav;
    }


    @RequestMapping("/delete/{id}")
    public String deletePackage(@PathVariable(name = "id") int id) {
        packageService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="new")
    public String newnew(Model model){
        Package a_package = new Package();
        model.addAttribute("a_package", a_package);

        return "new_package";
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="delete")
    public String deletedelete(HttpServletRequest request, Model model){
        try{
            if (request.getParameterValues("id") != null){
                for(String id : request.getParameterValues("id")){
                    packageService.deleteById(Integer.parseInt(id));
                }
            }
            return "redirect:/packages";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            List<Package> listPackages = packageService.findAll();
            model.addAttribute("listPackages", listPackages);
            return "packages";
        }
    }

    @RequestMapping(value="/processForm", method=RequestMethod.POST, params="edit")
    public ModelAndView editedit(HttpServletRequest request, Model model){
        ModelAndView mav = new ModelAndView("edit_package");
        if (request.getParameterValues("id") != null){
            for(String id : request.getParameterValues("id")){
                Package a_package = packageService.findById(Integer.parseInt(id));
                mav.addObject("a_package", a_package);
            }
            return mav;
        }
        mav = new ModelAndView("packages");
        List<Package> listPackages = packageService.findAll();
        //model.addAttribute("listPackages", listPackages);
        mav.addObject("listPackages", listPackages);
        return mav;
    }

}
