package platform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
public class CodeSharingPlatform {

    public static Db db;

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @Bean
    public CommandLineRunner runApplication(CodeRepository codeRepository) {
        return (args -> db = new Db(codeRepository));
    }

    @GetMapping("/code/new")
    public String getCreate() {
        return "create";
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCode(Model model, @PathVariable String id) {
        Code code = db.get(id);
        model.addAttribute("date", code.getUpdateDate());
        model.addAttribute("code", code.getCode());
        model.addAttribute("time", code.getSeconds());
        model.addAttribute("views", code.getViews());
        return "code";
    }

    @GetMapping("/code/latest")
    public String getLatest(Model model) {
        model.addAttribute("codes", db.latest());
        return "latest";
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    @ResponseBody
    public Code getCodeJson(@PathVariable String id) {
        return db.get(id);
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<Code> getLatestJson() {
        return db.latest();
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    @ResponseBody
    public String updateCode(@RequestBody Code code) {
        db.add(code);
        return "{" + " \"id\":\"" + code.getId() + "\" }";
    }

}
