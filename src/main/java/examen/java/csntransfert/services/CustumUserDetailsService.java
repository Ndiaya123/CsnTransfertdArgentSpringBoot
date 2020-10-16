package examen.java.csntransfert.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.dao.IRole;
import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
//ou @Component
public class CustumUserDetailsService implements UserDetailsService{
	@Autowired
	private CaissieRepository caissieRepository ;
	@Autowired
	private CaissierService caissierService ;

	@Autowired
	private IRole iRole;

	Caissier caissier1 = new Caissier();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Caissier caissier = caissierService.findByUsername(username);

		if(caissier != null)
		{
			System.out.println("ok");
			List roles = new ArrayList();
			roles.add(caissier.getRole());
			 User u = new User(caissier.getUsername(),caissier.getMotpasse(),
					 true,true,true,true,getAuthorities(roles));
			 System.out.println(u);
			caissier1.setUsername(u.getUsername());
			caissier1.setMotpasse(u.getPassword());
			 return u ;
		}

		return null;
	}

	public Caissier tmpUssernameMotPasse()
	{

		return caissier1;
	}
	
	private Collection getAuthorities(List roles) {
		List authorities = new ArrayList();
		for(Object role : roles)
		{
			Role l = (Role)role;
			authorities.add(new SimpleGrantedAuthority(l.getLibRole()));
		}
		return authorities ;
	}

}
