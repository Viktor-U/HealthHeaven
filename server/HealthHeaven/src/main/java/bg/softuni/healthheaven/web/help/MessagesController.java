package bg.softuni.healthheaven.web.help;

import bg.softuni.healthheaven.model.dtos.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class MessagesController {

    @GetMapping("/messages")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<String>> message() {
        return ResponseEntity.ok(Arrays.asList("first", "second", "third"));
    }

    @GetMapping("/protected/messages")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDTO> protectedMessage() {
        return ResponseEntity.ok(new MessageDTO("protected user's message"));
    }
}
