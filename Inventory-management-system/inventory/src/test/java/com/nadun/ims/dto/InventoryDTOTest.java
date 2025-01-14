package com.nadun.ims.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryDTOTest {

    @Test
    public void testNoArgsConstructor() {
        InventoryDTO inventoryDTO = new InventoryDTO();
        assertNull(inventoryDTO.getInventoryName());
        assertEquals(0, inventoryDTO.getInventoryId());
    }

    @Test
    public void testAllArgsConstructor() {
        InventoryDTO inventoryDTO = new InventoryDTO(1, "SampleInventory");
        assertEquals(1, inventoryDTO.getInventoryId());
        assertEquals("SampleInventory", inventoryDTO.getInventoryName());
    }

    @Test
    public void testSettersAndGetters() {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setInventoryId(2);
        inventoryDTO.setInventoryName("NewInventory");

        assertEquals(2, inventoryDTO.getInventoryId());
        assertEquals("NewInventory", inventoryDTO.getInventoryName());
    }

    @Test
    public void testEqualsAndHashCode() {
        InventoryDTO inventoryDTO1 = new InventoryDTO(1, "Inventory1");
        InventoryDTO inventoryDTO2 = new InventoryDTO(1, "Inventory1");
        InventoryDTO inventoryDTO3 = new InventoryDTO(2, "Inventory2");

        assertEquals(inventoryDTO1, inventoryDTO2);
        assertNotEquals(inventoryDTO1, inventoryDTO3);
        assertEquals(inventoryDTO1.hashCode(), inventoryDTO2.hashCode());
        assertNotEquals(inventoryDTO1.hashCode(), inventoryDTO3.hashCode());
    }

    @Test
    public void testToString() {
        InventoryDTO inventoryDTO = new InventoryDTO(1, "Inventory1");
        String expectedString = "InventoryDTO(inventoryId=1, inventoryName=Inventory1)";
        assertEquals(expectedString, inventoryDTO.toString());
    }
}
