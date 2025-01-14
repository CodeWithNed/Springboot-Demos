package com.nadun.ims.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    public void testNoArgsConstructor() {
        Inventory inventory = new Inventory();
        assertNull(inventory.getInventoryName());
        assertEquals(0, inventory.getInventoryId());
    }

    @Test
    public void testAllArgsConstructor() {
        Inventory inventory = new Inventory(1, "SampleInventory");
        assertEquals(1, inventory.getInventoryId());
        assertEquals("SampleInventory", inventory.getInventoryName());
    }

    @Test
    public void testSettersAndGetters() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(2);
        inventory.setInventoryName("NewInventory");

        assertEquals(2, inventory.getInventoryId());
        assertEquals("NewInventory", inventory.getInventoryName());
    }

    @Test
    public void testEqualsAndHashCode() {
        Inventory inventory1 = new Inventory(1, "Inventory1");
        Inventory inventory2 = new Inventory(1, "Inventory1");
        Inventory inventory3 = new Inventory(2, "Inventory2");

        assertEquals(inventory1, inventory2);
        assertNotEquals(inventory1, inventory3);
        assertEquals(inventory1.hashCode(), inventory2.hashCode());
        assertNotEquals(inventory1.hashCode(), inventory3.hashCode());
    }

    @Test
    public void testToString() {
        Inventory inventory = new Inventory(1, "Inventory1");
        String expectedString = "Inventory(inventoryId=1, inventoryName=Inventory1)";
        assertEquals(expectedString, inventory.toString());
    }
}
