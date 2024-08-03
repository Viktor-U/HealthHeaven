package bg.softuni.healthheaven.web;



import bg.softuni.healthheaven.model.dtos.IdRequestDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemOrderDTO;
import bg.softuni.healthheaven.model.dtos.shop.OrderDTO;
import bg.softuni.healthheaven.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ItemService itemService;

    @GetMapping("/shop/items")
    public ResponseEntity<List<ItemDTO>> getAllItems() {

        List<ItemDTO> items = itemService.getAllItems();

        return ResponseEntity.ok(items);
    }

    @GetMapping("/shop/items/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ItemDTO> getOneItem(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(itemService.getOneItem(id));
    }


    @PostMapping("/shop/cart")
    public ResponseEntity<List<ItemOrderDTO>> getAllItemsByUser(@RequestBody IdRequestDTO id) {

        List<ItemOrderDTO> items = itemService.getAllItemsOnUser(id.getId());

        return ResponseEntity.ok(items);
    }
    @PostMapping("/shop/cart/add")
    public ResponseEntity<OrderDTO> addItemToCart(@RequestBody OrderDTO orderDTO) {

        itemService.addItemToCart(orderDTO);

        return ResponseEntity.ok(orderDTO);
    }
}
