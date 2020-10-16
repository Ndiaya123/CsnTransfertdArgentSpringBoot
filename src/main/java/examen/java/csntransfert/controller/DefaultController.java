package examen.java.csntransfert.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @PreAuthorize("hasAuthority('ROLE_USER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping({"/403"})
    public String notFound()
    {
            return  "403";
    }
//    @GetMapping({"/AdminAcceuil"})
//    public String acceuilPage(Model model)
//    {
//        return "admin/acceuil";
//    }
}
