package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.rating.RatingDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/rate/{id}")
    public ResponseEntity<ItemDTO> postRate(@RequestBody RatingDTO ratingDTO,
                                            @PathVariable Long id) {
        ItemDTO result = ratingService.rateItem(ratingDTO, id);

        return ResponseEntity.ok(result);
    }
}
