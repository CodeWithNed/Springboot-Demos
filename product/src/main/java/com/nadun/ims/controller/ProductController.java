package com.nadun.ims.controller;

import com.nadun.ims.dto.ProductDTO;
import com.nadun.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    // ==========================
    // CRUD Operations
    // ==========================

    /**
     * Retrieve all users.
     *
     * @return a list of all users.
     */
    @GetMapping("/users")
    public List<ProductDTO> allProducts() {
        return productService.getAllProducts();
    }

    /**
     * Save a new user.
     *
     * @param productDTO the user details to be saved.
     * @return a success message.
     */
    @PostMapping("/user")
    public String saveUser(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    /**
     * Update an existing user.
     *
     * @param productDTO the updated user details.
     * @return a success message.
     */
    @PutMapping("/user")
    public String updateUser(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    /**
     * Retrieve a user by their ID.
     *
     * @param id the user ID.
     * @return the user details.
     */
    @GetMapping("/user/{id}")
    public ProductDTO getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(Math.toIntExact(id));
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the user ID.
     * @return a success message.
     */
    @DeleteMapping("/user/{id}")
    public String deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(Long.valueOf(id));
    }

    // ==========================
    // Partial Updates
    // ==========================

    /**
     * Update the name of a user.
     *
     * @param id   the user ID.
     * @param name the new name for the user.
     * @return a success message.
     */
    @PatchMapping("/user/{id}/name")
    public String updateProductName(@PathVariable Long id, @RequestParam String name) {
        return productService.updateProductName(id, name);
    }

    // ==========================
    // Additional Functionality
    // ==========================

    /**
     * Search for users by name.
     *
     * @param name the name or partial name to search for.
     * @return a list of users matching the search criteria.
     */
    @GetMapping("/users/search")
    public List<ProductDTO> searchProductsByName(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    /**
     * Save multiple users in bulk.
     *
     * @param productDTOList the list of users to be saved.
     * @return a success message.
     */
    @PostMapping("/users")
    public String saveProducts(@RequestBody List<ProductDTO> productDTOList) {
        return productService.saveProducts(productDTOList);
    }

    /**
     * Check if a user exists by their ID.
     *
     * @param id the user ID.
     * @return true if the user exists, false otherwise.
     */
    @GetMapping("/user/{id}/exists")
    public String doesProductExist(@PathVariable Long id) {
        return productService.doesProductExist(id);
    }
}
