package my.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}

@RefreshScope
@RestController
class MessageRestController {

    @Autowired
    private K8sProperties k8sProperties;

    @RequestMapping("/k8s-props")
    K8sProperties getK8sProperties() {
        System.out.println("**** My account count :"+ k8sProperties.getAccounts().size());
        return k8sProperties;
    }


}
