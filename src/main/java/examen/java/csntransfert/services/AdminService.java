//package examen.java.csntransfert.services;
//
//import examen.java.csntransfert.dao.AdminRepository;
//import examen.java.csntransfert.model.Admin;
//import examen.java.csntransfert.model.Transfert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class AdminService implements IAdmin {
//
//
//        @Autowired
//        private AdminRepository adminRepository;
//        @Override
//        public List<Admin> findAll() {
//            return adminRepository.findAll();
//        }
//
//        @Override
//        public Admin findById(Long id) {
//            Optional<Admin> admin = adminRepository.findById(id);
//            return  admin.isPresent()?admin.get():null;
//        }
//
//    @Override
//    public Admin findByMotpasse(String motpasse) {
//        return adminRepository.findByMotpasse(motpasse);
//    }
//
//    @Override
//    public Admin findByEmail(String email) {
//        return adminRepository.findByEmail(email);
//    }
//    @Override
//    public Admin findByUsername(String username) {
//        return adminRepository.findByUsername(username);
//    }
//}
