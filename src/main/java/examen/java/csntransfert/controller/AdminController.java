package examen.java.csntransfert.controller;

//import examen.java.csntransfert.dao.AdminRepository;
import examen.java.csntransfert.config.MySimpleUrlAuthenticationSuccessHandler;
import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.dao.ClientRepository;
import examen.java.csntransfert.dao.CompteRepository;
import examen.java.csntransfert.dao.TransfertRepository;
import examen.java.csntransfert.model.*;
//import examen.java.csntransfert.services.AdminService;
import examen.java.csntransfert.services.CaissierService;
import examen.java.csntransfert.services.ClientService;
import examen.java.csntransfert.services.CustumUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;
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
import java.util.Optional;

@Controller
public class AdminController {

//    @Autowired
//    private Admi;
//    @Autowired
//    private ServiceRepository serviceRepository;

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
//
//    @Autowired
//    private AdminService adminService;
    @Autowired
    private CaissierService caissierService;

    @Autowired
    private ClientService clientService;

    @Autowired
    BCryptPasswordEncoder encoder;
//    @Autowired
@Autowired
private CustumUserDetailsService userDetailsService;



//    @GetMapping({"/AdminAcceuil"})
//    public String index(ModelMap modelMap)
//    {
//        modelMap.put("admin", new Admin());
//        return "admin/acceuil";
//    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping({"/AdminAcceuil"})
    public String acceuilPage( HttpSession session)
    {
        int nbreCaissier=0;
        int nbreClient=0;
        int nbreTransfert =0;

        String username = userDetailsService.tmpUssernameMotPasse().getUsername();
        String password = userDetailsService.tmpUssernameMotPasse().getMotpasse();
        System.out.println(username);
        System.out.println(password);

        for(Client cl : clientRepository.findAll())
        {
            nbreClient += 1;
        }
        for(Transfert tr : transfertRepository.findAll())
        {
            nbreTransfert += 1;
        }
        for (Caissier ad : caissieRepository.findAll())
        {

            nbreCaissier += 1;
            if(ad.getUsername().equals(username) && ad.getMotpasse().equals(password))
            {
                tmplogin = true;
                session.setAttribute("emailAdmin",ad.getEmail());
                session.setAttribute("idAdmin",ad.getId());
                session.setAttribute("prenomAdmin",ad.getPrenom());
                session.setAttribute("nomAdmin",ad.getNom());
                session.setAttribute("photoAdmin",ad.getPhoto());
            }
        }
        session.setAttribute("nbreCaissier",nbreCaissier);
        session.setAttribute("nbreClient",nbreClient);
        session.setAttribute("nbreTransfert",nbreTransfert);
        return "admin/acceuil";
    }

//    @GetMapping({"/AdminLogin"})
//    public String adminPage(Model model)
//    {
////        Caissier caissier = new Caissier();
////        model.addAttribute("admin",new Admin());
//        return "admin/login";
//    }
    boolean tmplogin = false;
//    @RequestMapping({"/identification"})
//    public String login(@ModelAttribute("admin") Admin admin, ModelMap modelMap, HttpSession session, Model model) {
//        String email = admin.getEmail();
//        String mot_passe = admin.getMotpasse();
//        Long idAdmin;
//        String prenomAdmin;
//        String nomAdmin;
//        String photoAdmin;
////        if(adminService.findByEmail(admin.getEmail()) != null && adminService.findByMotpasse(admin.getMotpasse()) != null)
////        {
////              session.setAttribute("email",admin.getEmail());
////            session.setAttribute("id",admin.getId());
////            return  "admin/acceuil";
////        }else
////        {
////            modelMap.put("error","Email ou mot de passe incorecte.");
////          return "admin/login";
////        }
//        for (Admin ad : adminRepository.findAll())
//        {
//
//            if(ad.getEmail().equals(email) && ad.getMotpasse().equals(mot_passe))
//            {
//                tmplogin = true;
//                session.setAttribute("emailAdmin",admin.getEmail());
//                session.setAttribute("idAdmin",ad.getId());
//                session.setAttribute("prenomAdmin",ad.getPrenom());
//                session.setAttribute("nomAdmin",ad.getNom());
//                session.setAttribute("photoAdmin",ad.getPhoto());
//            }
//        }
//        if(tmplogin == true)
//        { System.out.println("debut");
//
//            tmplogin = false;
//
//            return "redirect:/AdminAcceuil";
//        }
//
//            modelMap.put("error","Email ou mot de passe incorecte.");
//
//          return "admin/login";
//
//    }


    @GetMapping({"/logout"})
    public String logout(HttpSession session,Model model)
    {
        session.removeAttribute("emailAdmin");
        session.removeAttribute("idAdmin");
        session.removeAttribute("prenomAdmin");
        session.removeAttribute("nomAdmin");
        session.removeAttribute("photoAdmin");
//        model.addAttribute("admin",new Admin());
        return "redirect:login";
    }
//
//    @GetMapping({"/AdminLogin"})
//    public String adminPage(Model model)
//    {
////        Caissier caissier = new Caissier();
//
//        model.addAttribute("admin",new Admin());
//        return "admin/login";
//    }

    //    boolean tmplogin = false;
//    @RequestMapping("/identification")
//    public String loginAdmin(@ModelAttribute("admin") Admin admin, Model model)
//    {
//        String email = admin.getEmail();
//        String mot_passe = admin.getMotpasse();
//
//
////        if(email.equals("pp@gmail.com") && mot_passe.equals("pp"))
////        {
////            tmplogin = true;
////        }
////        else
////        {
////            tmplogin = false;
////        }
//        for (Admin ad : adminRepository.findAll())
//        {
//            String email2 = ad.getEmail();
//            String mot_passe2 = ad.getMotpasse();
//            if(email.equals(email2) && mot_passe.equals(mot_passe2))
//            {
//                System.out.println(ad.getEmail());
//
//                tmplogin = true;
//            }
//        }
//        if(tmplogin == true)
//        { System.out.println("debut");
////            System.out.println(tmplogin);
////            System.out.println("fin");
//            return "redirect:/AdminAcceuil";
//        }
//        else
//        {
////            System.out.println("erreur");
////            System.out.println(tmplogin);
//
//            return "redirect:/AdminLogin";
//        }
//
//    }


    @GetMapping({"/AdminCaissier"})
    public String caissierlPage(Model model)
    {
        Caissier caissier = new Caissier();
//        caissier1.setDate_creation(format.format(date));
//       System.out.println(format.format(date));

        String uploadsUrl = "file:///C:\\uploads\\";
//        caissier.setAdmin(new Admin());
        model.addAttribute("caissiers", caissieRepository.findAll());
//        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("caissier",new Caissier());
        model.addAttribute("uploads", uploadsUrl);
        return "admin/caissier";
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @GetMapping("/image/{imagename}")
    public @ResponseBody byte[] image(@PathVariable(value = "imagename") String imagename) throws Exception
    {
        try
        {
            File file = new File("C://uploads//"+imagename);
            String var = "C://uploads//"+imagename;

            System.out.println("----------------------------------------");
            System.out.println(var);
            System.out.println("----------------------------------------");
            return Files.readAllBytes(file.toPath());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/caissier/add")
    public String add(@ModelAttribute("caissier") Caissier caissier) throws Exception
    {

        byte[] bytes = null;
        Path path = null;
        if(caissier.getParts()[0].getName().equals(""))
        {
            caissier.setPhoto("noimg.jpg");
        }
        else
        {
            MultipartFile part = caissier.getParts()[0];
            bytes = part.getBytes();
            path = Paths.get("C://uploads//" + part.getOriginalFilename() );
            caissier.setPhoto(part.getOriginalFilename());
        }
        int n = 5;
        String tmp4 = this.getAlphaNumericString(2);
        String tmp5 = this.getAlphaNumericString(2);


        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        DateFormat format1 = new SimpleDateFormat("dd");
        Date date1 = new Date();
//        System.out.println(format.format(date));
        String tmp = caissier.getDate_naissance();
        String tmp1 = tmp.substring(0,4);
        String tmp2 = tmp.substring(5,7);
        String tmp3 = tmp.substring(8,10);
        System.out.println(tmp);
        System.out.println(tmp1);
        System.out.println(tmp2);
        System.out.println(tmp3);
        String matricule = tmp1+tmp2+tmp3+format1.format(date1);
        System.out.println(matricule);
        int mat = Integer.parseInt(matricule);
        System.out.println(mat);
        caissier.setMatricule(mat);
        caissier.setDate_creation(format.format(date));
//        System.out.println(caissier.getAdmin());
        String mot =tmp4+tmp3+tmp5;
        System.out.println(mot);
        caissier.setLogin(mot);
        caissier.setMotpasse(encoder.encode(mot));
        caissieRepository.save(caissier);
        if(bytes.length != 0)
        {
            Files.write(path, bytes);
        }
        return "redirect:/AdminCaissier";

    }

    @GetMapping({"/caissier/add"})
    public String caissierlAdd(Model model)
    {
        Caissier caissier = new Caissier();
//        caissier1.setDate_creation(format.format(date));
//       System.out.println(format.format(date));
//        caissier.setAdmin(new Admin());
        model.addAttribute("caissiers", caissierService.findAll());
//        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("caissier",new Caissier());

        return "admin/ajouter";

    }

    @GetMapping("admin/detail/{id}")
    public String show(Model model,HttpSession session, @PathVariable String id)
    {
        try
        {
            Optional<Caissier> caissier = caissieRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (caissier.isPresent())
            {
                String uploadsUrl = "file:///C:\\uploads\\";
                model.addAttribute("caissier", caissier.get());
                model.addAttribute("uploads", uploadsUrl);
                System.out.println("ooo");
            }
            return "admin/detail";
        }
        catch (Exception e)
        {
            return "redirect:/AdminCaissier";
        }
    }

    @GetMapping("admin/editer/{id}")
    public String editer(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Caissier> caissier = caissieRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (caissier.isPresent())
            {
                model.addAttribute("caissier", caissier.get());
            }
            return "admin/editer";
        }
        catch (Exception e)
        {
            return "redirect:/AdminCaissier";
        }
    }

    @RequestMapping("/admin/editer")
    public String editer(@ModelAttribute("caissier") Caissier caissier) throws Exception
    {

//        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(format.format(date));
//        caissier.setDate_creation(format.format(date));


        byte[] bytes = null;
        Path path = null;
        boolean tmp = false;
        System.out.println(caissier.getPhoto());

        if(caissier.getParts()[0].getName().equals(""))
        {
            System.out.println("image non selectionner");
        }
        else
        {

            MultipartFile part = caissier.getParts()[0];

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
                caissier.setPhoto(part.getOriginalFilename());

            }
        }

        caissieRepository.save(caissier);
        if(tmp == false)
        {
            if(bytes.length != 0)
            {
                Files.write(path, bytes);
            }
        }
        return "redirect:/AdminCaissier";
    }




    @GetMapping("admin/remove/{id}")
    public String remove(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Caissier> caissier = caissieRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (caissier.isPresent())
            {
                model.addAttribute("caissier", caissier.get());
            }
            return "admin/remove";
        }
        catch (Exception e)
        {
            return "redirect:/AdminCaissier";
        }
    }

    @RequestMapping("/admin/remove")
    public String remove(@ModelAttribute("caissier") Caissier caissier)
    {
        caissieRepository.delete(caissier);
        return "redirect:/AdminCaissier";
    }


    @GetMapping({"/AdminCompte"})
    public String comptelPage(Model model)
    {
        Compte compte = new Compte();
//        caissier1.setDate_creation(format.format(date));
//       System.out.println(format.format(date));

        model.addAttribute("comptes", compteRepository.findAll());
//        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("compte",new Compte());
        return "admin/compte";
    }


    @GetMapping("admin/detailcompte/{id}")
    public String showcompte(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Compte> compte = compteRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (compte.isPresent())
            {
                model.addAttribute("compte", compte.get());
                System.out.println("ooo");
            }
            return "admin/detailcompte";
        }
        catch (Exception e)
        {
            return "redirect:/AdminCompte";
        }
    }


    @GetMapping({"/AdminCompte1"})
    public String AdminCompte1()
    {

        return "redirect:AdminCompte";
    }
    @GetMapping({"/AdminTransfert1"})
    public String AdminTransfert1()
    {

        return "redirect:AdminTransfert";
    }
    @GetMapping({"/AdminCaissier1"})
    public String AdminCaissier1()
    {

        return "redirect:AdminCaissier";
    }
    @GetMapping({"/AdminClient1"})
    public String AdminClient1()
    {

        return "redirect:AdminClient";
    }



    @GetMapping({"/AdminClient"})
    public String adminCleintPage(Model model)
    {

        Client client = new Client();
//        caissier1.setDate_creation(format.format(date));
//       System.out.println(format.format(date));

//        transfert.setCaissier(new Caissier());
        model.addAttribute("clients", clientRepository.findAll());
//        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("client",new Client());
        return "admin/client";
    }


    @GetMapping({"/client/add1"})
    public String clientadd(Model model)
    {
        Client client = new Client();

        model.addAttribute("client",new Client());

        return "admin/ajouterclient";

    }
    @RequestMapping("/client/add1")
    public String add(@ModelAttribute("client") Client client) throws Exception
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
        return "redirect:/AdminClient";

    }


    @GetMapping("client/detail1/{id}")
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
            return "admin/detailclient";
        }
        catch (Exception e)
        {
            return "redirect:/AdminClient";
        }
    }

    @GetMapping("client/editer1/{id}")
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
            return "admin/editerclient";
        }
        catch (Exception e)
        {
            return "redirect:/AdminClient";
        }
    }

    @RequestMapping("/client/editer1")
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

        return "redirect:/AdminClient";
    }


    @GetMapping("admin/removeclient/{id}")
    public String removeclient(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Client> client = clientRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (client.isPresent())
            {
                model.addAttribute("client", client.get());
            }
            return "admin/removeclient";
        }
        catch (Exception e)
        {
            return "redirect:/AdminClient";
        }
    }

    @RequestMapping("/admin/removeclient")
    public String removeclient(@ModelAttribute("client") Client client)
    {
        clientRepository.delete(client);
        return "redirect:/AdminClient";
    }


    @GetMapping("admin/editercompte/{id}")
    public String editercompte(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Caissier> caissier = caissieRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (caissier.isPresent())
            {
                model.addAttribute("caissier", caissier.get());
            }
            return "admin/caissiereditercompte";
        }
        catch (Exception e)
        {
            return "redirect:/AdminCaissier";
        }
    }

    @RequestMapping("admin/editercompte")
    public String editercompte(@ModelAttribute("caissier") Caissier caissier) throws Exception
    {

//        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(format.format(date));
//        caissier.setDate_creation(format.format(date));


        byte[] bytes = null;
        Path path = null;
        boolean tmp = false;
        System.out.println(caissier.getPhoto());

        if(caissier.getParts()[0].getName().equals(""))
        {
            System.out.println("image non selectionner");
        }
        else
        {

            MultipartFile part = caissier.getParts()[0];

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
                caissier.setPhoto(part.getOriginalFilename());

            }
        }

        System.out.println(caissier.getPrenom());
        System.out.println(caissier);
        caissieRepository.save(caissier);
        if(tmp == false)
        {
            if(bytes.length != 0)
            {
                Files.write(path, bytes);
            }
        }
        return "redirect:/AdminCaissier";
    }


}
