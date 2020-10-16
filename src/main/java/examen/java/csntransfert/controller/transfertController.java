package examen.java.csntransfert.controller;

import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.dao.ClientRepository;
import examen.java.csntransfert.dao.CompteRepository;
import examen.java.csntransfert.dao.TransfertRepository;
import examen.java.csntransfert.model.*;
import examen.java.csntransfert.services.ClientService;
import examen.java.csntransfert.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class transfertController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransfertRepository transfertRepository;
    @Autowired
    private CaissieRepository caissieRepository ;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransfertService transfertService;

    @GetMapping({"/CaissierTransfertErreur"})
    public String CaissierTransfertErreur()
    {
        return "caissier/transferterreur";
    }
    @GetMapping({"/CaissierTransfertAdd"})
    public String CaissierTransfertAdd(Model model)
    {
        model.addAttribute("transfert",new Transfert());
        return "caissier/transfertadd";
    }

    @GetMapping({"/CaissierTransfert"})
    public String CaissierTransfert(Model model)
    {
        model.addAttribute("client",new Client());
        return "caissier/transfert";
    }

    @RequestMapping("/caissier/CaissierTransfert")
    public String add(@ModelAttribute("client") Client client,  HttpSession session,Model model)
    {
        int cni = client.getCni();
        boolean tmp  = false;
        System.out.println(cni);
        if(clientRepository.findByCni(cni) != null)
        {

            for (Client cl : clientRepository.findAll())
            {

                if(cl.getCni() == cni)
                {
                    tmp = true;
                    session.setAttribute("idClientTrans",cl.getId());
                    session.setAttribute("cniClientTrans",cl.getCni());
                    session.setAttribute("prenomClientTrans",cl.getPrenom());
                    session.setAttribute("nomClientTrans",cl.getNom());
                    session.setAttribute("adresseClientTrans",cl.getAdresse());
                    session.setAttribute("telClientTrans",cl.getTel());
                }
            }
            return "redirect:/CaissierTransfertAdd";
        }else
        {
//            modelMap.put("cniErreur",client.getCni());
            session.setAttribute("cniErreur",client.getCni());
            return "redirect:/CaissierTransfertErreur";
        }

    }


    static String getNumeric(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "0123456789";

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
    @RequestMapping("/caissier/CaissierTransfertadd")
    public String add(@ModelAttribute("transfert") Transfert transfert, HttpSession session) throws Exception
    {


        String code_transfert = this.getNumeric(10);
        System.out.println(code_transfert);
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        transfert.setDate_creation(format.format(date));
        transfert.setCodetransfert(code_transfert);
        session.setAttribute("code_transfertTrans",transfert.getCodetransfert());

        int frais_retrait = (transfert.getMontant()*5)/100;
        int commission_systeme = (frais_retrait*40)/100;
        transfert.setCommission_systeme(commission_systeme);

        int commission_taxe_etat = (frais_retrait*20)/100;
        transfert.setCommission_taxe_etat(commission_taxe_etat);

        int commission_caissier= (frais_retrait*20)/100;
        transfert.setCommission_caissier(commission_caissier);

        transfert.setEtat("non retire");

        ///Update compte
        long idcompte = 1;
        int commission_systeme_total = 0,commission_taxe_etat_total =0 ;

        for (Compte co : compteRepository.findAll())
        {

            if(co.getId() == 1)
            {
                commission_systeme_total = co.getCommission_systeme_total();
                commission_taxe_etat_total = co.getCommission_taxe_etat_total();
            }
        }

        Compte compte = new Compte();

        compte.setId(idcompte);
        int tmpcommission_systeme_total = commission_systeme_total + commission_systeme;
        int tmpcommission_taxe_etat_total = commission_taxe_etat_total + commission_taxe_etat;
        compte.setCommission_systeme_total(tmpcommission_systeme_total);
        compte.setCommission_taxe_etat_total(tmpcommission_taxe_etat_total);


        ///Update caissier

        Caissier caissier = new Caissier();
    int tmpmontant_compte1 = 0;
        for (Caissier cais : caissieRepository.findAll())
        {

            if(cais.getId() == transfert.getCaissier().getId())
            {
                String adresse = cais.getAdresse();
                caissier.setAdresse(adresse);
                int  cni = cais.getCni();
                caissier.setCni(cni);
                String    date_creation  = cais.getDate_creation();
                caissier.setDate_creation(date_creation);
                String date_naissance  = cais.getDate_naissance();
                caissier.setDate_naissance(date_naissance);
                String      email = cais.getEmail();
                caissier.setEmail(email);
                String lieu_naissance  = cais.getLieu_naissance();
                caissier.setLieu_naissance(lieu_naissance);
                int     matricule  = cais.getMatricule();
                caissier.setMatricule(matricule);
                String       motpasse = cais.getMotpasse();
                caissier.setMotpasse(motpasse);
                int ninea = cais.getNinea();
                caissier.setNinea(ninea);
                String nom = cais.getNom();
                caissier.setNom(nom);
                Long id  = cais.getId();
                caissier.setId(id);
                String  photo  = cais.getPhoto();
                caissier.setPhoto(photo);
                String prenom  = cais.getPrenom();
                caissier.setPrenom(prenom);
//           boolean     statut  = transfert.getCaissier().getStatut();
                int tel  = cais.getTel();
                caissier.setTel(tel);
                String    username = cais.getUsername();
                caissier.setUsername(username);
//       Long admin_id  = transfert.getCaissier().getAdmin().getId();
//                Admin admin = new Admin();
//                admin = cais.getAdmin();
//                caissier.setAdmin(admin);
                int tmpcommission_total  = cais.getCommission_total() + commission_caissier;
                caissier.setCommission_total(tmpcommission_total);
                tmpmontant_compte1 = cais.getMontant_compte();
                int tmpmontant_compte  = cais.getMontant_compte() - transfert.getMontant();
                caissier.setMontant_compte(tmpmontant_compte);

            }
        }

        if(tmpmontant_compte1 < transfert.getMontant())
        {
            session.setAttribute("tmpmontant_compte1",tmpmontant_compte1);
            return "redirect:/CaissierTransfertEchouer";

        }else
        {

            caissieRepository.save(caissier);
            compteRepository.save(compte);
            transfertRepository.save(transfert);
            return "redirect:/CaissierTransfertInfo";
        }

    }

    @GetMapping({"/CaissierTransfertEchouer"})
    public String CaissierTransfertEchouer()
    {
        return "caissier/transfertechouer";
    }
    @GetMapping({"/CaissierTransfertInfo"})
    public String CaissierTransfertInfo()
    {
        return "caissier/transfertinfo";
    }

    @GetMapping({"/CaissierRetrait"})
    public String CaissierRetrait(Model model)
    {
        model.addAttribute("transfert",new Transfert());
        return "caissier/retrait";
    }

    @GetMapping({"/CaissierRetrait1"})
    public String CaissierRetrait1(Model model)
    {
//        session.removeAttribute("idClientTrans");
//        session.removeAttribute("cniClientTrans");
//        session.removeAttribute("prenomClientTrans");
//        session.removeAttribute("nomClientTrans");
//        session.removeAttribute("adresseClientTrans");
//        session.removeAttribute("telClientTrans");
//        session.removeAttribute("code_transfertTrans");

        return "redirect:CaissierRetrait";
    }
    @GetMapping({"/CaissierRetraitErreur"})
    public String CaissierRetraitErreur()
    {
        return "caissier/retraiterreur";
    }




    @RequestMapping("/caissier/CaissierRetrait")
    public String addretrati(@ModelAttribute("transfert") Transfert transfert,  HttpSession session,Model model)
    {
        String code_transfert = transfert.getCodetransfert();
        boolean tmp  = false;
        System.out.println(code_transfert);
        session.setAttribute("codetransfertErreur",transfert.getCodetransfert());
        if(transfertRepository.findByCodetransfert(code_transfert) != null)
        {

            for (Transfert tr : transfertRepository.findAll())
            {

                if(tr.getCodetransfert().equals(transfert.getCodetransfert()))
                {
                    System.out.println("okkkk");
                    tmp = true;
                    session.setAttribute("idTrans",tr.getId());
                    session.setAttribute("cniTrans",tr.getCni());
                    session.setAttribute("prenomTrans",tr.getPrenom());
                    session.setAttribute("nomTrans",tr.getNom());
                    session.setAttribute("adresseTrans",tr.getAdresse());
                    session.setAttribute("telTrans",tr.getTel());
                    session.setAttribute("montantTrans",tr.getMontant());
                    session.setAttribute("codetransfertErreur",tr.getCodetransfert());
                    Long id = tr.getClient().getId();
                    for( Client cl : clientRepository.findAll())
                    {
                        if(cl.getId() == id){
                            session.setAttribute("idCl",tr.getId());
                            session.setAttribute("cniCl",tr.getCni());
                            session.setAttribute("prenomCl",tr.getPrenom());
                            session.setAttribute("nomCl",tr.getNom());
                            session.setAttribute("adresseCl",tr.getAdresse());
                            session.setAttribute("telCl",tr.getTel());
                        }
                    }


                }
            }
            model.addAttribute("transfert",new Transfert());
            return "redirect:/CaissierRetraitAdd";
        }else
        {
//            modelMap.put("cniErreur",client.getCni());
            return "redirect:/CaissierRetraitErreur";
        }

    }

    @GetMapping({"/CaissierRetraitAdd"})
    public String CaissierRetraitAdd(Model model)
    {
        model.addAttribute("transfert",new Transfert());
        return "caissier/retraitadd";
    }

    @RequestMapping("/CaissierRetraitAdd")
    public String add(@ModelAttribute("transfert") Transfert transfert) throws Exception
    {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        int frais_retrait = (transfert.getMontant()*5)/100;
        int commission_caissier= (frais_retrait*20)/100;

        Long idTrans = transfert.getId();
//        Long idCais = transfert.getCaissier().getId();
//        Long idCl  = transfert.getClient().getId();

        Caissier caissier = new Caissier();


        Transfert transfert1 = new Transfert();
        for(Transfert trans : transfertRepository.findAll())
        {
            if(trans.getId().equals(transfert.getId()))
            {
                transfert1.setAdresse(trans.getAdresse());
                transfert1.setCni(trans.getCni());
                transfert1.setCodetransfert(trans.getCodetransfert());

                transfert1.setCommission_caissier(trans.getCommission_caissier());
                transfert1.setCommission_systeme(trans.getCommission_systeme());
                transfert1.setCommission_taxe_etat(trans.getCommission_taxe_etat());
                transfert1.setDate_creation(trans.getDate_creation());
                transfert1.setDate_retrait(format.format(date));
                transfert1.setEmail(trans.getEmail());
                transfert1.setEtat("retire");
                transfert1.setMontant(trans.getMontant());
                transfert1.setNom(trans.getNom());
                transfert1.setPrenom(trans.getPrenom());
                transfert1.setTel(trans.getTel());
                transfert1.setCaissier(trans.getCaissier());
                transfert1.setClient(trans.getClient());

            }
        }

        for(Caissier cais : caissieRepository.findAll())
        {
            if(cais.getId().equals(transfert1.getCaissier().getId()))
            {
                String adresse = cais.getAdresse();
                caissier.setAdresse(adresse);
                int  cni = cais.getCni();
                caissier.setCni(cni);
                String    date_creation  = cais.getDate_creation();
                caissier.setDate_creation(date_creation);
                String date_naissance  = cais.getDate_naissance();
                caissier.setDate_naissance(date_naissance);
                String      email = cais.getEmail();
                caissier.setEmail(email);
                String lieu_naissance  = cais.getLieu_naissance();
                caissier.setLieu_naissance(lieu_naissance);
                int     matricule  = cais.getMatricule();
                caissier.setMatricule(matricule);
                String       motpasse = cais.getMotpasse();
                caissier.setMotpasse(motpasse);
                int ninea = cais.getNinea();
                caissier.setNinea(ninea);
                String nom = cais.getNom();
                caissier.setNom(nom);
                Long id  = cais.getId();
                caissier.setId(id);
                String  photo  = cais.getPhoto();
                caissier.setPhoto(photo);
                String prenom  = cais.getPrenom();
                caissier.setPrenom(prenom);
//           boolean     statut  = transfert.getCaissier().getStatut();
                int tel  = cais.getTel();
                caissier.setTel(tel);
                String    username = cais.getUsername();
                caissier.setUsername(username);
//       Long admin_id  = transfert.getCaissier().getAdmin().getId();
//                Admin admin = new Admin();
//                admin = cais.getAdmin();
//                caissier.setAdmin(admin);
                int tmpcommission_total  = cais.getCommission_total() + commission_caissier;
                caissier.setCommission_total(tmpcommission_total);
//                int tmpmontant_compte1 = cais.getMontant_compte();
                int tmpmontant_compte  = cais.getMontant_compte() + transfert.getMontant();
                caissier.setMontant_compte(tmpmontant_compte);
            }
        }


        caissieRepository.save(caissier);
        transfertRepository.save(transfert1);
        return "redirect:/CaissierRetraitInfo";

    }

    @GetMapping({"/CaissierRetraitInfo"})
    public String CaissierRetraitInfo()
    {
        return "caissier/retraitinfo";
    }

//    @GetMapping({"/CaissierRetraitD"})
//    public String CaissierRetraitListe()
//    {
//        return "retraitdetail";
//    }


    @GetMapping({"/CaissierTransfertListe"})
    public String CaissierTransfertListe()
    {
        return "caissier/transfertliste";
    }

    @GetMapping("CaissierTransfertListe/{id}")
    public String CaissierTransfertListe(Model model, @PathVariable String id)
    {
        model.addAttribute("transferts", transfertRepository.findByCaissier_Id(Long.parseLong(id)));
//        List<Transfert> transfertList;
//
//        for(Transfert tr : transfertRepository.findByCaissier_Id(Long.parseLong(id)))
//        {
//            if(tr.getEtat())
//        }
        return "caissier/transfertliste";

    }

//    @GetMapping({"/CaissierTransfertDetail"})
//    public String CaissierTransfertDetail()
//    {
//        return "caissier/transfertdetail";
//    }


    @GetMapping("/CaissierTransfertDetail/{id}")
    public String show(Model model,HttpSession session, @PathVariable String id)
    {
        try
        {
            Optional<Transfert> transfert = transfertRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (transfert.isPresent())
            {

                model.addAttribute("transfert", transfert.get());

            }
            return "caissier/transfertdetail";
        }
        catch (Exception e)
        {
            return "redirect:/CaisierAcceuil";
        }
    }


//    @GetMapping({"/AdminTransfertListe"})
//    public String AdminCaissierTransfertListe()
//    {
//        return "admin/transfertliste";
//    }

    @GetMapping("/AdminTransfertListe")
    public String AdminCaissierTransfertListe(Model model)
    {
        model.addAttribute("transferts", transfertRepository.findAll());
//        List<Transfert> transfertList;
//
//        for(Transfert tr : transfertRepository.findByCaissier_Id(Long.parseLong(id)))
//        {
//            if(tr.getEtat())
//        }
        return "admin/transfertliste";

    }

    @GetMapping("/AdminTransfertDetail/{id}")
    public String AdminTransfertDetail(Model model,HttpSession session, @PathVariable String id)
    {
        try
        {
            Optional<Transfert> transfert = transfertRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (transfert.isPresent())
            {

                model.addAttribute("transfert", transfert.get());

            }
            return "admin/transfertdetail";
        }
        catch (Exception e)
        {
            return "admin:/AdminAcceuil";
        }
    }

    @GetMapping("/AdminTransfertListeNonRetire")
    public String AdminTransfertListeNonRetire(Model model)
    {
        model.addAttribute("transferts", transfertRepository.findByEtat("non retire"));
//        List<Transfert> transfertList;
//
//        for(Transfert tr : transfertRepository.findByCaissier_Id(Long.parseLong(id)))
//        {
//            if(tr.getEtat())
//        }
        return "admin/transfertlistenonretire";

    }

    @GetMapping("/AdminTransfertNonRetireDetail/{id}")
    public String AdminTransfertNonRetireDetail(Model model,HttpSession session, @PathVariable String id)
    {
        try
        {
            Optional<Transfert> transfert = transfertRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (transfert.isPresent())
            {

                model.addAttribute("transfert", transfert.get());

            }
            return "admin/transfertnonretiredetail";
        }
        catch (Exception e)
        {
            return "admin:/AdminAcceuil";
        }
    }


    @GetMapping("/AdminTransfertListeRetire")
    public String AdminTransfertListeRetire(Model model)
    {
        model.addAttribute("transferts", transfertRepository.findByEtat("retire"));

        return "admin/transfertlisteretire";

    }

    @GetMapping("/AdminTransfertRetireDetail/{id}")
    public String AdminTransfertetireDetail(Model model,HttpSession session, @PathVariable String id)
    {
        try
        {
            Optional<Transfert> transfert = transfertRepository.findById(Long.parseLong(id));
//            model.addAttribute("employe", employe.get());
            if (transfert.isPresent())
            {

                model.addAttribute("transfert", transfert.get());

            }
            return "admin/transfertretiredetail";
        }
        catch (Exception e)
        {
            return "admin:/AdminAcceuil";
        }
    }
}
