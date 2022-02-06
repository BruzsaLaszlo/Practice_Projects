package Human;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "/api/v1/human")
public class HumanController {

    private final HumanService humanService;

    @Autowired
    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }


    @RequestMapping("/")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }

    @GetMapping(path = "/addGet") // Map ONLY POST Requests
    public @ResponseBody
    String addHumanGET(@RequestParam String name, @RequestParam int age, @RequestParam String gender) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        humanService.addHuman(new Human(name, age, Gender.valueOf(gender)));
        return "Saved\n" + name + " " + age;
    }

    @GetMapping(path = "/addAll")
    public String addAllHumans() {
        return "Saved\n" + humanService.addAllHumans();
    }

    @DeleteMapping(path = "{humanId}")
    public String deleteHuman(@PathVariable("humanId") long id) {
        humanService.deleteHuman(id);
        return "Successful deleted: " + id;
    }

    @PutMapping(path = "{humanId}")
    public String updateHumanPlus100(
            @PathVariable("humanId") long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age
    ) {
        return "sikeresen UPDATEL : " + humanService.updateHuman(id, name, age);
    }

    @PostMapping(path = "/addPOST")
    public String addHumanPOST(@RequestBody Human human) {
        humanService.addHuman(human);
        return "Succesfully add:\n" + human;
    }

    @GetMapping(path = "/getAll")
    public List<Human> getHumans() {
        return humanService.getHumans();
    }

}
