package examen.java.csntransfert.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.services.CustumUserDetailsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@SuppressWarnings("unused")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  protected Log logger = LogFactory.getLog(this.getClass());

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();



  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, 
    HttpServletResponse response, Authentication authentication)
    throws IOException {

      handle(request, response, authentication);
      clearAuthenticationAttributes(request);
  }

  protected void handle(HttpServletRequest request, 
    HttpServletResponse response, Authentication authentication)
    throws IOException {

      String targetUrl = determineTargetUrl(authentication);

      if (response.isCommitted()) {
          logger.debug(
            "Response has already been committed. Unable to redirect to "
            + targetUrl);
          return;
      }

      redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  @Autowired
  private CaissieRepository caissieRepository;
  @Autowired
  private Utils utils;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private CustumUserDetailsService userDetailsService;

  String tmpUsername;
  String tmpMotpasse;
  
  protected String determineTargetUrl(Authentication authentication) {
//      boolean isSuperAdmin = false;
//      boolean isCaisse = false;
      boolean isAdmin = false;
      boolean isUser = false;
      Collection<? extends GrantedAuthority> authorities
       = authentication.getAuthorities();
      String role = "";
      for (GrantedAuthority grantedAuthority : authorities) {
        if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
        	  role = grantedAuthority.getAuthority();
              isAdmin = true;
              break;
          }
          else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
              role = grantedAuthority.getAuthority();
              isUser = true;
              break;
          }
          System.out.println("role : "+role);
      }

      final UserDetails userDetails = userDetailsService
              .loadUserByUsername(utils.getConnectedUser());
      final String token = jwtTokenUtil.generateToken(userDetails);

//      System.out.println(userDetails.getUsername());
//      System.out.println(userDetails.getPassword());

       tmpUsername = userDetails.getUsername();
      tmpMotpasse = userDetails.getPassword();


      Utils.setToken(token);
      System.out.println("Token :"+token);
//      Caissier u = caissieRepository.findByUsername(utils.getConnectedUser());
//      if(!u.isChanged()){
//          return "api/utilisateur/changepwd";
//      }
     if (isAdmin) {
          return "AdminAcceuil";
      } else if (isUser) {
        return "CaissierAcceuil";
      }
      else {
          throw new IllegalStateException();
      }
  }


  protected void clearAuthenticationAttributes(HttpServletRequest request) {
      HttpSession session = request.getSession(false);
      if (session == null) {
          return;
      }
      session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
      this.redirectStrategy = redirectStrategy;
  }
  protected RedirectStrategy getRedirectStrategy() {
      return redirectStrategy;
  }

}