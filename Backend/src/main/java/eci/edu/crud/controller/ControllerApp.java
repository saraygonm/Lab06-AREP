package eci.edu.crud.controller;


import eci.edu.crud.property.PropertyInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan(basePackages = "eci.edu.crud") //escanea todos los paquetes
//@EnableJpaRepositories("eci.edu.crud.property")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "eci.edu.crud.property")
@EntityScan(basePackages = "eci.edu.crud.property")
public class ControllerApp {
    private static final Logger log = LoggerFactory.getLogger(ControllerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ControllerApp.class, args);
        //System.out.println("🔒 Servidor corriendo en HTTPS: https://localhost:8443");

    }

    @Bean
    public CommandLineRunner demo(PropertyInterface repository) {
        return (args) -> {
            // Mensaje que indica que se buscarán todas las propiedades
            System.out.println("Properties found with findAll():");
            System.out.println("-------------------------------");


            repository.findAll().forEach(property -> {
                System.out.println(property.toString());
            });
            System.out.println("");
        };
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://arep1.duckdns.org")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
