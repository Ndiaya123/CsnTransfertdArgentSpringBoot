package examen.java.csntransfert.controller;

import examen.java.csntransfert.dao.CaissieRepository;
//import examen.java.csntransfert.model.Admin;
import examen.java.csntransfert.dao.TransfertRepository;
import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.model.Client;
//import examen.java.csntransfert.services.AdminService;
import examen.java.csntransfert.model.Transfert;
import examen.java.csntransfert.services.CaissierService;
import examen.java.csntransfert.services.CustumUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CaissierController {

    @Autowired
    private CaissieRepository caissieRepository;


    @Autowired
    private CaissierService caissierService;

    @Autowired
    private CustumUserDetailsService userDetailsService;

    @Autowired
    private TransfertRepository transfertRepository;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping({"/CaissierAcceuil"})
    public String acceuilPage(Model model,HttpSession session )
    {
        String username = userDetailsService.tmpUssernameMotPasse().getUsername();
        String password = userDetailsService.tmpUssernameMotPasse().getMotpasse();
        System.out.println(username);
        System.out.println(password);
        long tmp=0;

        for (Caissier cais : caissieRepository.findAll())
        {

            if(cais.getUsername().equals(username) && cais.getMotpasse().equals(password))
            {
                tmplogin = true;
                tmp = cais.getId();
                session.setAttribute("emailCaissier",cais.getEmail());
                session.setAttribute("idCaissier",cais.getId());
                session.setAttribute("prenomCaissier",cais.getPrenom());
                session.setAttribute("nomCaissier",cais.getNom());
                session.setAttribute("photoCaissier",cais.getPhoto());
            }
        }


        int nbreTransfertCaissier = 0;
        int nbreTransfertRetire = 0;
        int nbreTransfertNonRetire = 0;

        for(Transfert tr : transfertRepository.findByCaissier_Id(tmp))
        {
            nbreTransfertCaissier += 1;
        }
        for(Transfert tr : transfertRepository.findByEtat("retire"))
        {
           if (tr.getCaissier().getId().equals(tmp))
           {
               nbreTransfertRetire += 1;
           }
        }
        for(Transfert tr : transfertRepository.findByEtat("non retire"))
        {
            if (tr.getCaissier().getId().equals(tmp))
            {
                nbreTransfertNonRetire += 1;
            }
        }
        session.setAttribute("nbreTransfertCaissier",nbreTransfertCaissier);
        session.setAttribute("nbreTransfertRetire",nbreTransfertRetire);
        session.setAttribute("nbreTransfertNonRetire",nbreTransfertNonRetire);
        return "caissier/acceuil";
    }
//
//    @GetMapping({"/CaissierLogin"})
//    public String caissierPage(Model model)
//    {
//        model.addAttribute("caissier",new Caissier());
//        return "caissier/login";
//    }

    boolean tmplogin = false;
    @RequestMapping({"/identification1"})
    public String login(@ModelAttribute("caissier") Caissier caissier, ModelMap modelMap, HttpSession session, Model model) {
        String email = caissier.getEmail();
        String mot_passe = caissier.getMotpasse();


        for (Caissier cais : caissieRepository.findAll())
        {

            if(cais.getEmail().equals(email) && cais.getMotpasse().equals(mot_passe))
            {
                tmplogin = true;
                session.setAttribute("emailCaissier",caissier.getEmail());
                session.setAttribute("idCaissier",cais.getId());
                session.setAttribute("prenomCaissier",cais.getPrenom());
                session.setAttribute("nomCaissier",cais.getNom());
                session.setAttribute("photoCaissier",cais.getPhoto());
            }
        }
        if(tmplogin == true)
        { System.out.println("debut");

            tmplogin = false;

            return "redirect:/CaissierAcceuil";
        }

        modelMap.put("error","Email ou mot de passe incorecte.");

        return "caissier/login";

    }


    @GetMapping({"/logout1"})
    public String logout(HttpSession session,Model model)
    {
        session.removeAttribute("emailCaissier");
        session.removeAttribute("idCaissier");
        session.removeAttribute("prenomCaissier");
        session.removeAttribute("nomCaissier");
        session.removeAttribute("photoCaissier");
        model.addAttribute("caissier",new Caissier());
        return "redirect:login";
    }

    @GetMapping({"/CaissierAcceuil1"})
    public String CaissierAcceuil1()
    {

        return "redirect:CaissierAcceuil";
    }
    @GetMapping({"/CaissierClient1"})
    public String CaissierClient1()
    {

        return "redirect:CaissierClient";
    }

    @GetMapping({"/CaissierTransfert1"})
    public String CaissierTransfert1(Model model)
    {
        return "redirect:CaissierTransfert";
    }


    @GetMapping({"/CaissierTransfert2"})
    public String CaissierTransfert2(HttpSession session)
    {
        session.removeAttribute("idClientTrans");
        session.removeAttribute("cniClientTrans");
        session.removeAttribute("prenomClientTrans");
        session.removeAttribute("nomClientTrans");
        session.removeAttribute("adresseClientTrans");
        session.removeAttribute("telClientTrans");
        session.removeAttribute("code_transfertTrans");
        return "redirect:CaissierTransfert";
    }


}
