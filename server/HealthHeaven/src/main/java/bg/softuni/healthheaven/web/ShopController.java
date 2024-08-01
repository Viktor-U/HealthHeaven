package bg.softuni.healthheaven.web;


import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.services.ItemService;
import bg.softuni.healthheaven.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopController {
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/shop/items")
    public ResponseEntity<List<ItemDTO>> getAllItems() {

        List<ItemDTO> items = itemService.getAllItems();

        return ResponseEntity.ok(items);
    }

}
