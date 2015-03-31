package ouchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        System.setProperty("h2.implicitRelativePath", "true");
        System.setProperty("java.net.preferIPv4Stack", "true");
        SpringApplication.run(App.class, args);
        //System.exit(0);
    }

    @RequestMapping(value = "/")
    String hello() {
        return "Hello World!";
    }
}