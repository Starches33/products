package products.app.back.controller;

import products.app.back.entity.Product;
import products.app.back.exception.ResourceNotFoundException;
import products.app.back.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id")
                                                    Long id) throws ResourceNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Product not found for this id :: " + id));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id")
                                                   Long id, @RequestBody Product productDto)
            throws ResourceNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Product not found for this id :: " + id));

        product.setEmailId(productDto.getEmailId());
        product.setLastName(productDto.getLastName());
        product.setFirstName(productDto.getFirstName());
        product.setId(id);
        final Product updateProduct = productRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id")
                                                   Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Product not found for this id :: " + id));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
