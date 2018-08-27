package org.remipassmoilesel.formvalidation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class FormController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/formvalidation")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/formvalidation")
    public String checkPersonInfo(
            @ModelAttribute("reservationForm") // used to modify name of attribute in model
            @Valid PersonForm personForm,

            BindingResult bindingResult) {

        /*

        Here a model attribute is automatically created
        with CLASSNAME of form: PersonForm -> personForm

        BUT name can be changed with @ModelAttribute

        */

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }
}