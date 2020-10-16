package examen.java.csntransfert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CsntransfertApplication implements CommandLineRunner
{

    public static void main(String[] args)
    {
        SpringApplication.run(CsntransfertApplication.class, args);
    }

    @Autowired
    BCryptPasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(encoder.encode("passer"));
    }
}
