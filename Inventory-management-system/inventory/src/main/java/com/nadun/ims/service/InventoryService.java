package com.nadun.ims.service;

import com.nadun.ims.dto.InventoryDTO;
import com.nadun.ims.model.Inventory;
import com.nadun.ims.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventorys(){
        List<Inventory> inventories = inventoryRepository.findAll();
        return modelMapper.map(inventories, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public String saveInventory(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO.getInventoryId() + " " + inventoryDTO.getInventoryName() + " saved successfully.";
    }

    public String updateInventory(InventoryDTO inventoryDTO) {
        inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO.getInventoryId() + " " + inventoryDTO.getInventoryName() + " updated successfully.";
    }

    public InventoryDTO getInventoryById(Integer id) throws Exception {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(Long.valueOf(id));
        if (optionalInventory.isPresent()) {
            return modelMapper.map(optionalInventory.get(), InventoryDTO.class);
        } else {
            throw new Exception("Inventory with ID " + id + " not found.");
        }
    }

    public String deleteInventory(Long id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            inventoryRepository.delete(optionalInventory.get());
            return "Inventory with ID " + id + " deleted successfully.";
        } else {
            return "Inventory with ID " + id + " not found.";
        }
    }

    public String updateInventoryName(Long id, String name) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventory.setInventoryName(name);
            inventoryRepository.save(inventory);
            return "Inventory name updated to " + name + " for ID " + id + ".";
        } else {
            return "Inventory with ID " + id + " not found.";
        }
    }

    public List<InventoryDTO> searchInventorysByName(String name) {
        List<Inventory> inventories = inventoryRepository.findByInventoryNameContaining(name);
        return modelMapper.map(inventories, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public String saveInventorys(List<InventoryDTO> inventoryDTOList) {
        List<Inventory> inventories = modelMapper.map(inventoryDTOList, new TypeToken<List<Inventory>>(){}.getType());
        inventoryRepository.saveAll(inventories);
        return inventoryDTOList.size() + " inventories saved successfully.";
    }

    public String doesInventoryExist(Long id) {
        boolean existance = inventoryRepository.existsById(id);
        if(existance){
            return "Inventory exists.";
        }
        else{
            return "Inventory doesn't exists.";
        }
    }
}
