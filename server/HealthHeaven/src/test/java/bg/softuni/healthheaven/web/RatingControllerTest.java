package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.rating.RatingDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    private RatingDTO ratingDTO;
    private ItemDTO itemDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

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
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testPostRate() {
        when(ratingService.rateItem(any(RatingDTO.class), eq(1L))).thenReturn(itemDTO);

        ResponseEntity<ItemDTO> response = ratingController.postRate(ratingDTO, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(itemDTO, response.getBody());
    }
}
