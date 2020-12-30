package carmesin.camillo.testproject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorld {

    @RequestMapping("/")
    public String index() {

        return "Hello World!!!";

    }

}
