package controller;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IProvinceService;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;

    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        Province province = provinceService.findById(id).get();
        modelAndView.addObject("pro", province);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Province province) {
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        provinceService.save(province);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
            ModelAndView modelAndView = new ModelAndView("/province/delete");
            Province province = provinceService.findById(id).get();
            modelAndView.addObject("pro", province);
            return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProvince(@ModelAttribute("pro")  Province province) {
        provinceService.remove(province.getId());
        return "redirect:/provinces";
    }
}