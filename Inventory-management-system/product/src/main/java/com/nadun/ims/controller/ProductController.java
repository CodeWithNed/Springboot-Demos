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
     * Retrieve all products.
     *
     * @return a list of all products.
     */
    @GetMapping("/products")
    public List<ProductDTO> allProducts() {
        return productService.getAllProducts();
    }

    /**
     * Save a new product.
     *
     * @param productDTO the product details to be saved.
     * @return a success message.
     */
    @PostMapping("/product")
    public String saveproduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    /**
     * Update an existing product.
     *
     * @param productDTO the updated product details.
     * @return a success message.
     */
    @PutMapping("/product")
    public String updateproduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    /**
     * Retrieve a product by their ID.
     *
     * @param id the product ID.
     * @return the product details.
     */
    @GetMapping("/product/{id}")
    public ProductDTO getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(Math.toIntExact(id));
    }

    /**
     * Delete a product by their ID.
     *
     * @param id the product ID.
     * @return a success message.
     */
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(Long.valueOf(id));
    }

    // ==========================
    // Partial Updates
    // ==========================

    /**
     * Update the name of a product.
     *
     * @param id   the product ID.
     * @param name the new name for the product.
     * @return a success message.
     */
    @PatchMapping("/product/{id}/name")
    public String updateProductName(@PathVariable Long id, @RequestParam String name) {
        return productService.updateProductName(id, name);
    }

    // ==========================
    // Additional Functionality
    // ==========================

    /**
     * Search for products by name.
     *
     * @param name the name or partial name to search for.
     * @return a list of products matching the search criteria.
     */
    @GetMapping("/products/search")
    public List<ProductDTO> searchProductsByName(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    /**
     * Save multiple products in bulk.
     *
     * @param productDTOList the list of products to be saved.
     * @return a success message.
     */
    @PostMapping("/products")
    public String saveProducts(@RequestBody List<ProductDTO> productDTOList) {
        return productService.saveProducts(productDTOList);
    }

    /**
     * Check if a product exists by their ID.
     *
     * @param id the product ID.
     * @return true if the product exists, false otherwise.
     */
    @GetMapping("/product/{id}/exists")
    public String doesProductExist(@PathVariable Long id) {
        return productService.doesProductExist(id);
    }
}
