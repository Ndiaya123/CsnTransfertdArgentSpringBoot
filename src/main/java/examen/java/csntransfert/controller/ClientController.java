package examen.java.csntransfert.controller;


import examen.java.csntransfert.dao.*;
import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.model.Client;
import examen.java.csntransfert.model.Transfert;
//import examen.java.csntransfert.services.AdminService;
import examen.java.csntransfert.services.CaissierService;
import examen.java.csntransfert.services.ClientService;
import examen.java.csntransfert.services.TransfertService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private CaissieRepository caissieRepository;
//    @Autowired
//    private AdminRepository adminRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransfertRepository transfertRepository;
//    @Autowired
//    private AdminService adminService;
    @Autowired
    private CaissierService caissierService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransfertService transfertService;

    @GetMapping({"/CaissierClient"})
    public String caissierCleintPage(Model model)
    {

        Client client = new Client();
//        caissier1.setDate_creation(format.format(date));
//       System.out.println(format.format(date));

//        transfert.setCaissier(new Caissier());
        model.addAttribute("clients", clientRepository.findAll());
//        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("client",new Client());
        return "caissier/client";
    }

    @GetMapping({"/client/add"})
    public String clienadd(Model model)
    {
        Client client = new Client();

        model.addAttribute("client",new Client());

        return "caissier/ajouterclient";

    }
    @RequestMapping("/client/add")
    public String add(@ModelAttribute("client") Client client, HttpSession session) throws Exception
    {

        byte[] bytes = null;
        Path path = null;
        if(client.getParts()[0].getName().equals(""))
        {
            client.setPhoto("noimg.png");
        }
        else
        {
            MultipartFile part = client.getParts()[0];
            bytes = part.getBytes();
            path = Paths.get("C://uploads//" + part.getOriginalFilename() );
            client.setPhoto(part.getOriginalFilename());
        }



        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        client.setDate_creation(format.format(date));
//        System.out.println(cai.getAdmin());

        clientRepository.save(client);
        if(bytes.length != 0)
        {
            Files.write(path, bytes);
        }
        session.removeAttribute("cniErreur");
        return "redirect:/CaissierClient";

    }


    @GetMapping("client/detail/{id}")
    public String show(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Client> client = clientRepository.findById(Long.parseLong(id));
            if (client.isPresent())
            {
                String uploadsUrl = "file:///C:\\uploads\\";
                model.addAttribute("client", client.get());
                model.addAttribute("uploads", uploadsUrl);
            }
            return "caissier/detailclient";
        }
        catch (Exception e)
        {
            return "redirect:/CaissierClient";
        }
    }

    @GetMapping("client/editer/{id}")
    public String edite(Model model, @PathVariable String id)
    {

        try
        {
            Optional<Client> client = clientRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (client.isPresent())
            {
                model.addAttribute("client", client.get());
            }
            return "caissier/editerclient";
        }
        catch (Exception e)
        {
            return "redirect:/CaissierClient";
        }
    }

    @RequestMapping("/client/editer")
    public String editer(@ModelAttribute("client") Client client) throws Exception
    {
        byte[] bytes = null;
        Path path = null;
boolean tmp = false;
    System.out.println(client.getPhoto());

               if(client.getParts()[0].getName().equals(""))
               {
                   System.out.println("image non selectionner");
               }
               else
               {

                   MultipartFile part = client.getParts()[0];

                   if(part.getOriginalFilename().equals(""))
                   {
                    System.out.println("bon");
                    tmp = true;
                   }else if(part.getOriginalFilename() != "")
                   {
                       tmp = false;
                       System.out.println(part.getOriginalFilename());
                       bytes = part.getBytes();
                       path = Paths.get("C://uploads//" + part.getOriginalFilename() );
                       client.setPhoto(part.getOriginalFilename());

                   }
               }

        System.out.println(client.getPhoto());
        clientRepository.save(client);
           if(tmp == false)
        {
            if(bytes.length != 0)
            {
                Files.write(path, bytes);
            }
        }

        return "redirect:/CaissierClient";
    }


}
