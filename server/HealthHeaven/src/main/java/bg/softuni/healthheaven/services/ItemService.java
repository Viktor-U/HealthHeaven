package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.shop.ItemDTO;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public List<ItemDTO> getAllItems() {

        List<Item> items = itemRepository.findAll();

        List<ItemDTO> result = new ArrayList<>();

        for (Item item : items) {

            result.add(modelMapper.map(item, ItemDTO.class));

        }

        return result;
    }
}
