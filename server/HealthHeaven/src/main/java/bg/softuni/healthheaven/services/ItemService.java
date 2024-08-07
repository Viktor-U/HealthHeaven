package bg.softuni.healthheaven.services;


import bg.softuni.healthheaven.model.dtos.IdRequestDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemOrderDTO;
import bg.softuni.healthheaven.model.dtos.shop.OrderDTO;
import bg.softuni.healthheaven.model.entities.BaseEntity;
import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.Rating;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<ItemDTO> getAllItems() {

        List<Item> items = itemRepository.findAll();

        List<ItemDTO> result = new ArrayList<>();

        for (Item item : items) {

            ItemDTO map = mapToItemDTO(item);
            result.add(map);
        }

        return result;
    }

    private ItemDTO mapToItemDTO(Item item) {
        ItemDTO map = modelMapper.map(item, ItemDTO.class);

        int itemRates = 0;
        for (Rating rating : item.getRatings()) {
            itemRates += rating.getRating();
        }
        if (!item.getRatings().isEmpty()) {
            map.setRating(itemRates / item.getRatings().size());
        }else {
            map.setRating(1);
        }
        return map;
    }

    public ItemDTO getOneItem(Long id) {

        Item item = itemRepository.findById(id).get();
        return mapToItemDTO(item);
    }

    public List<ItemOrderDTO> getAllItemsOnUser(Long id) {

        Optional<User> user = userRepository.findById(id);
        List<Item> items = user.get().getItems();



        Map<Item, Integer> itemMap = new HashMap<>();

        for (Item item : items) {
            if (itemMap.containsKey(item)) {
                int quantity = itemMap.get(item);
                itemMap.remove(item);
                itemMap.put(item, quantity + 1);
            }else {
                itemMap.put(item, 1);
            }

        }

        List<ItemOrderDTO> result = new ArrayList<>();

        itemMap.forEach((key, value) -> {
            ItemOrderDTO itemOrderDTO = modelMapper.map(key, ItemOrderDTO.class);
            itemOrderDTO.setQuantity(value);
            result.add(itemOrderDTO);
        });


        return result;

    }


    public void addItemToCart(OrderDTO orderDTO) {

        User user = userRepository.findById(orderDTO.getUserId()).get();
        Item item = itemRepository.findById(orderDTO.getItemId()).get();
        for (int i = 0; i < orderDTO.getQuantity(); i++) {
            user.getItems().add(item);
        }

        userRepository.save(user);
    }

    public void removeItemFromCart(OrderDTO orderDTO) {

        User user = userRepository.findById(orderDTO.getUserId()).get();
        Item item = itemRepository.findById(orderDTO.getItemId()).get();

        user.getItems().remove(item);

        userRepository.save(user);

    }

    public void removeAllItemFromCart(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId()).get();
        user.getItems().clear();
        userRepository.save(user);
    }
}
