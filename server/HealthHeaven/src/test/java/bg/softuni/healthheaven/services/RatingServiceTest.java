package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.rating.RatingDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.Rating;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.RatingRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private RatingService ratingService;

    private Item item;
    private Rating rating;
    private User user;
    private RatingDTO ratingDTO;
    private ItemDTO itemDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");

        item = new Item();
        item.setId(1L);
        item.setName("Sample Item");
        item.setDescription("Sample description");
        item.setPrice(100.0);
        item.setRatings(new ArrayList<>());

        rating = new Rating();
        rating.setId(1L);
        rating.setRating(4);
        rating.setItem(item);
        rating.setRater(user);

        ratingDTO = new RatingDTO();
        ratingDTO.setRaterId(1L);
        ratingDTO.setRating(4);

        itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("Sample Item");
        itemDTO.setDescription("Sample description");
        itemDTO.setPrice(100.0);
        itemDTO.setRating(4);
    }

    @Test
    public void testRateItemNewRating() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ratingRepository.findByRaterIdAndItemId(1L, 1L)).thenReturn(Optional.empty());
        when(ratingRepository.saveAndFlush(any(Rating.class))).thenReturn(rating);
        when(itemService.getOneItem(1L)).thenReturn(itemDTO);

        ItemDTO result = ratingService.rateItem(ratingDTO, 1L);

        verify(ratingRepository, times(1)).saveAndFlush(any(Rating.class));
        assertEquals(itemDTO, result);
    }

    @Test
    public void testRateItemUpdateRating() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ratingRepository.findByRaterIdAndItemId(1L, 1L)).thenReturn(Optional.of(rating));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        when(itemService.getOneItem(1L)).thenReturn(itemDTO);

        ItemDTO result = ratingService.rateItem(ratingDTO, 1L);

        verify(ratingRepository, times(1)).save(any(Rating.class));
        assertEquals(itemDTO, result);
    }
}
