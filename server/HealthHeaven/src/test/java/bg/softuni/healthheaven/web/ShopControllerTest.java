package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.IdRequestDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemOrderDTO;
import bg.softuni.healthheaven.model.dtos.shop.OrderDTO;
import bg.softuni.healthheaven.services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ShopControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ShopController shopController;

    private ItemDTO itemDTO;
    private ItemOrderDTO itemOrderDTO;
    private OrderDTO orderDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setName("Sample Item");
        itemDTO.setDescription("Sample description");
        itemDTO.setPrice(100.0);
        itemDTO.setRating(4);

        itemOrderDTO = new ItemOrderDTO();
        itemOrderDTO.setId(1L);
        itemOrderDTO.setName("Sample Item");
        itemOrderDTO.setDescription("Sample description");
        itemOrderDTO.setPrice(100.0);
        itemOrderDTO.setQuantity(2);

        orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setItemId(1L);
        orderDTO.setQuantity(2);
    }

    @Test
    public void testGetAllItems() {
        when(itemService.getAllItems()).thenReturn(Collections.singletonList(itemDTO));

        ResponseEntity<List<ItemDTO>> response = shopController.getAllItems();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(itemDTO, response.getBody().get(0));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testGetOneItem() throws Exception {
        when(itemService.getOneItem(1L)).thenReturn(itemDTO);

        ResponseEntity<ItemDTO> response = shopController.getOneItem(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(itemDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testGetAllItemsByUser() {
        IdRequestDTO idRequestDTO = new IdRequestDTO();
        idRequestDTO.setId(1L);

        when(itemService.getAllItemsOnUser(1L)).thenReturn(Collections.singletonList(itemOrderDTO));

        ResponseEntity<List<ItemOrderDTO>> response = shopController.getAllItemsByUser(idRequestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(itemOrderDTO, response.getBody().get(0));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testAddItemToCart() {
        doNothing().when(itemService).addItemToCart(any(OrderDTO.class));

        ResponseEntity<OrderDTO> response = shopController.addItemToCart(orderDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testRemoveItemFromCart() {
        doNothing().when(itemService).removeItemFromCart(any(OrderDTO.class));

        ResponseEntity<OrderDTO> response = shopController.removeItemFromCart(orderDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testRemoveAllFromCart() {
        doNothing().when(itemService).removeAllItemFromCart(any(OrderDTO.class));

        ResponseEntity<OrderDTO> response = shopController.removeAllFromCart(orderDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orderDTO, response.getBody());
    }
}
