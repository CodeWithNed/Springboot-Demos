package com.nadun.ims.service;

import com.nadun.ims.dto.ProductDTO;
import com.nadun.ims.model.Product;
import com.nadun.ims.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public String saveProduct(ProductDTO productDTO){
        productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO.getProductId() + " " + productDTO.getProductName() + " saved successfully.";
    }

    public String updateProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO.getProductId() + " " + productDTO.getProductName() + " updated successfully.";
    }

    public ProductDTO getProductById(Integer id) throws Exception {
        Optional<Product> optionalUser = productRepository.findById(Long.valueOf(id));
        if (optionalUser.isPresent()) {
            return modelMapper.map(optionalUser.get(), ProductDTO.class);
        } else {
            throw new Exception("Product with ID " + id + " not found.");
        }
    }

    public String deleteProduct(Long id) {
        Optional<Product> optionalUser = productRepository.findById(id);
        if (optionalUser.isPresent()) {
            productRepository.delete(optionalUser.get());
            return "Product with ID " + id + " deleted successfully.";
        } else {
            return "Product with ID " + id + " not found.";
        }
    }

    public String updateProductName(Long id, String name) {
        Optional<Product> optionalUser = productRepository.findById(id);
        if (optionalUser.isPresent()) {
            Product product = optionalUser.get();
            product.setProductName(name);
            productRepository.save(product);
            return "Product name updated to " + name + " for ID " + id + ".";
        } else {
            return "Product with ID " + id + " not found.";
        }
    }

    public List<ProductDTO> searchProductsByName(String name) {
        List<Product> products = productRepository.findByProductNameContaining(name);
        return modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public String saveProducts(List<ProductDTO> productDTOList) {
        List<Product> products = modelMapper.map(productDTOList, new TypeToken<List<Product>>(){}.getType());
        productRepository.saveAll(products);
        return productDTOList.size() + " products saved successfully.";
    }

    public String doesProductExist(Long id) {
        boolean existance = productRepository.existsById(id);
        if(existance){
            return "Product exists.";
        }
        else{
            return "Product doesn't exists.";
        }
    }
}
