package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("TourGuide-User")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
