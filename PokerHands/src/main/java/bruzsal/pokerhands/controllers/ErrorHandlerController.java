package bruzsal.pokerhands.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

@RestController
@Tag(name = "Nem létező végpont kezelése")
public class ErrorHandlerController implements ErrorController {

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Problem getErrorPath() {
        return Problem.builder()
                .withType(URI.create("resource/not-found"))
                .withStatus(Status.NOT_FOUND)
                .withDetail("A keresett oldal nem található")
                .build();
    }

}
