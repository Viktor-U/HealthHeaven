package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemOrderDTO;
import bg.softuni.healthheaven.model.dtos.shop.OrderDTO;
import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.Rating;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ItemService itemService;

    private Item item;
    private ItemDTO itemDTO;
    private User user;
    private OrderDTO orderDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setItems(new ArrayList<>());

        item = new Item();
        item.setId(1L);
        item.setName("Sample Item");
        item.setDescription("Sample description");
        item.setPrice(100.0);
        item.setRatings(new ArrayList<>());

        Rating rating = new Rating();
        rating.setRating(4);
        item.getRatings().add(rating);

        itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("Sample Item");
        itemDTO.setDescription("Sample description");
        itemDTO.setPrice(100.0);
        itemDTO.setRating(4);

        orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setItemId(1L);
        orderDTO.setQuantity(2);
    }

    @Test
    public void testGetAllItems() {
        when(itemRepository.findAll()).thenReturn(Collections.singletonList(item));
        when(modelMapper.map(any(Item.class), eq(ItemDTO.class))).thenReturn(itemDTO);

        List<ItemDTO> result = itemService.getAllItems();

        assertEquals(1, result.size());
        assertEquals(itemDTO, result.get(0));
    }

    @Test
    public void testGetOneItem() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(modelMapper.map(any(Item.class), eq(ItemDTO.class))).thenReturn(itemDTO);

        ItemDTO result = itemService.getOneItem(1L);

        assertEquals(itemDTO, result);
    }

    @Test
    public void testGetAllItemsOnUser() {
        user.getItems().add(item);
        user.getItems().add(item);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(any(Item.class), eq(ItemOrderDTO.class))).thenAnswer(invocation -> {
            Item item = invocation.getArgument(0);
            ItemOrderDTO itemOrderDTO = new ItemOrderDTO();
            itemOrderDTO.setId(item.getId());
            itemOrderDTO.setName(item.getName());
            itemOrderDTO.setDescription(item.getDescription());
            itemOrderDTO.setPrice(item.getPrice());
            itemOrderDTO.setQuantity(2);
            return itemOrderDTO;
        });

        List<ItemOrderDTO> result = itemService.getAllItemsOnUser(1L);

        assertEquals(1, result.size());
        assertEquals(item.getId(), result.get(0).getId());
        assertEquals(2, result.get(0).getQuantity());
    }

    @Test
    public void testAddItemToCart() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        itemService.addItemToCart(orderDTO);

        verify(userRepository, times(1)).save(user);
        assertEquals(2, user.getItems().size());
    }

    @Test
    public void testRemoveItemFromCart() {
        user.getItems().add(item);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        itemService.removeItemFromCart(orderDTO);

        verify(userRepository, times(1)).save(user);
        assertEquals(0, user.getItems().size());
    }
}
