package com.nadun.ims.controller;

import com.nadun.ims.dto.InventoryDTO;
import com.nadun.ims.model.Inventory;
import com.nadun.ims.repository.InventoryRepository;
import com.nadun.ims.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ModelMapper modelMapper;

    private Inventory inventory;
    private InventoryDTO inventoryDTO;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        inventory.setInventoryId(1);
        inventory.setInventoryName("Laptop");

        inventoryDTO = new InventoryDTO();
        inventoryDTO.setInventoryId(1);
        inventoryDTO.setInventoryName("Laptop");
    }

    @Test
    public void testGetAllInventorys() {
        List<Inventory> inventoryList = Arrays.asList(inventory);
        when(inventoryRepository.findAll()).thenReturn(inventoryList);
        when(modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>() {}.getType()))
                .thenReturn(Arrays.asList(inventoryDTO));

        List<InventoryDTO> result = inventoryService.getAllInventorys();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(inventoryRepository, times(1)).findAll();
    }

    @Test
    public void testSaveInventory() {
        when(modelMapper.map(inventoryDTO, Inventory.class)).thenReturn(inventory);

        String result = inventoryService.saveInventory(inventoryDTO);

        assertEquals("1 Laptop saved successfully.", result);
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    public void testUpdateInventory() {
        when(modelMapper.map(inventoryDTO, Inventory.class)).thenReturn(inventory);

        String result = inventoryService.updateInventory(inventoryDTO);

        assertEquals("1 Laptop updated successfully.", result);
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    public void testGetInventoryById_Success() throws Exception {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));
        when(modelMapper.map(inventory, InventoryDTO.class)).thenReturn(inventoryDTO);

        InventoryDTO result = inventoryService.getInventoryById(1);

        assertNotNull(result);
        assertEquals(1L, result.getInventoryId());
        assertEquals("Laptop", result.getInventoryName());
    }

    @Test
    public void testGetInventoryById_NotFound() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> inventoryService.getInventoryById(1));

        assertEquals("Inventory with ID 1 not found.", exception.getMessage());
    }

    @Test
    public void testDeleteInventory_Success() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        String result = inventoryService.deleteInventory(1L);

        assertEquals("Inventory with ID 1 deleted successfully.", result);
        verify(inventoryRepository, times(1)).delete(inventory);
    }

    @Test
    public void testDeleteInventory_NotFound() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        String result = inventoryService.deleteInventory(1L);

        assertEquals("Inventory with ID 1 not found.", result);
    }

    @Test
    public void testUpdateInventoryName_Success() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        String result = inventoryService.updateInventoryName(1L, "Desktop");

        assertEquals("Inventory name updated to Desktop for ID 1.", result);
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    public void testUpdateInventoryName_NotFound() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        String result = inventoryService.updateInventoryName(1L, "Desktop");

        assertEquals("Inventory with ID 1 not found.", result);
    }

    @Test
    public void testSearchInventorysByName() {
        List<Inventory> inventoryList = Arrays.asList(inventory);
        when(inventoryRepository.findByInventoryNameContaining("Laptop")).thenReturn(inventoryList);
        when(modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>() {}.getType()))
                .thenReturn(Arrays.asList(inventoryDTO));

        List<InventoryDTO> result = inventoryService.searchInventorysByName("Laptop");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testSaveInventorys() {
        List<InventoryDTO> inventoryDTOList = Arrays.asList(inventoryDTO);
        List<Inventory> inventoryList = Arrays.asList(inventory);
        when(modelMapper.map(inventoryDTOList, new TypeToken<List<Inventory>>() {}.getType())).thenReturn(inventoryList);

        String result = inventoryService.saveInventorys(inventoryDTOList);

        assertEquals("1 inventories saved successfully.", result);
        verify(inventoryRepository, times(1)).saveAll(inventoryList);
    }

    @Test
    public void testDoesInventoryExist_True() {
        when(inventoryRepository.existsById(1L)).thenReturn(true);

        String result = inventoryService.doesInventoryExist(1L);

        assertEquals("Inventory exists.", result);
    }

    @Test
    public void testDoesInventoryExist_False() {
        when(inventoryRepository.existsById(1L)).thenReturn(false);

        String result = inventoryService.doesInventoryExist(1L);

        assertEquals("Inventory doesn't exists.", result);
    }
}
