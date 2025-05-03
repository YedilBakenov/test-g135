package kz.test.g135test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class G135TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(G135TestApplication.class, args);
    }

}
