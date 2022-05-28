package ptl.cloud.bank.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptl.cloud.bank.entities.Card;
import ptl.cloud.bank.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/")
    public List<Card> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/{id}")
    public Card get(@PathVariable("id") Long id) {
        return cardService.findById(id);
    }

    @PostMapping("/")
    public Card create() {
        return cardService.create();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        cardService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
