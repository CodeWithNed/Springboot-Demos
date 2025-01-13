package com.nadun.ims.controller;

import com.nadun.ims.dto.InventoryDTO;
import com.nadun.ims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    // ==========================
    // CRUD Operations
    // ==========================

    /**
     * Retrieve all inventorys.
     *
     * @return a list of all inventorys.
     */
    @GetMapping("/inventorys")
    public List<InventoryDTO> allInventorys() {
        return inventoryService.getAllInventorys();
    }

    /**
     * Save a new inventory.
     *
     * @param inventoryDTO the inventory details to be saved.
     * @return a success message.
     */
    @PostMapping("/inventory")
    public String saveInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.saveInventory(inventoryDTO);
    }

    /**
     * Update an existing inventory.
     *
     * @param inventoryDTO the updated inventory details.
     * @return a success message.
     */
    @PutMapping("/inventory")
    public String updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }

    /**
     * Retrieve a inventory by their ID.
     *
     * @param id the inventory ID.
     * @return the inventory details.
     */
    @GetMapping("/inventory/{id}")
    public InventoryDTO getInventoryById(@PathVariable Long id) throws Exception {
        return inventoryService.getInventoryById(Math.toIntExact(id));
    }

    /**
     * Delete a inventory by their ID.
     *
     * @param id the inventory ID.
     * @return a success message.
     */
    @DeleteMapping("/inventory/{id}")
    public String deleteInventory(@PathVariable String id) {
        return inventoryService.deleteInventory(Long.valueOf(id));
    }

    // ==========================
    // Partial Updates
    // ==========================

    /**
     * Update the name of a inventory.
     *
     * @param id   the inventory ID.
     * @param name the new name for the inventory.
     * @return a success message.
     */
    @PatchMapping("/inventory/{id}/name")
    public String updateInventoryName(@PathVariable Long id, @RequestParam String name) {
        return inventoryService.updateInventoryName(id, name);
    }

    // ==========================
    // Additional Functionality
    // ==========================

    /**
     * Search for inventorys by name.
     *
     * @param name the name or partial name to search for.
     * @return a list of inventorys matching the search criteria.
     */
    @GetMapping("/inventorys/search")
    public List<InventoryDTO> searchInventorysByName(@RequestParam String name) {
        return inventoryService.searchInventorysByName(name);
    }

    /**
     * Save multiple inventorys in bulk.
     *
     * @param inventoryDTOList the list of inventorys to be saved.
     * @return a success message.
     */
    @PostMapping("/inventorys")
    public String saveInventorys(@RequestBody List<InventoryDTO> inventoryDTOList) {
        return inventoryService.saveInventorys(inventoryDTOList);
    }

    /**
     * Check if a inventory exists by their ID.
     *
     * @param id the inventory ID.
     * @return true if the inventory exists, false otherwise.
     */
    @GetMapping("/inventory/{id}/exists")
    public String doesInventoryExist(@PathVariable Long id) {
        return inventoryService.doesInventoryExist(id);
    }
}
