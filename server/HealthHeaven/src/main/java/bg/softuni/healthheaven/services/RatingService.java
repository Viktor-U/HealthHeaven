package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.rating.RatingDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.Rating;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.RatingRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    private final ItemService itemService;


    public ItemDTO rateItem(RatingDTO ratingDTO, Long itemId) {

        Item item = itemRepository.findById(itemId).get();

        Optional<Rating> byRaterIdAndItemId = ratingRepository.findByRaterIdAndItemId(ratingDTO.getRaterId(), itemId);

        if (byRaterIdAndItemId.isPresent()) {
            Rating rating = byRaterIdAndItemId.get();
            rating.setRating(ratingDTO.getRating());
            ratingRepository.save(rating);
        }else {
            Rating rating = new Rating();
            rating.setRating(ratingDTO.getRating());
            rating.setItem(item);
            rating.setRater(userRepository.findById(ratingDTO.getRaterId()).get());
            ratingRepository.saveAndFlush(rating);

        }

        itemRepository.save(item);

        return itemService.getOneItem(itemId);
    }
}
