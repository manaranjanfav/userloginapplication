package com.loginandregistration.loginandregistration.Controller;

import com.loginandregistration.loginandregistration.Dto.registrationdto;
import com.loginandregistration.loginandregistration.services.Userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registre")
public class UserController  {
    @Autowired
    private Userservice userservice;
    
    @PostMapping
    public String registreUserAccount(@ModelAttribute("user") registrationdto userdto)
    {
        userservice.save(userdto);
        return "redirect:/registre?success";
    }
    @GetMapping
    public String showForm(Model m)
    {
        m.addAttribute("user",new registrationdto()); 
        return "registre";
    }
    
}
