package com.spring.bestpractices.service;

import com.spring.bestpractices.domain.Product;
import com.spring.bestpractices.domain.ProductStatus;
import com.spring.bestpractices.handler.EntityNotFoundException;
import com.spring.bestpractices.handler.BusinesRuleException;
import com.spring.bestpractices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    private static final String PRODUCT_NOT_FOUND = "Unable to find product with id ";
    private static final String PRODUCT_WITH_ID = "Unable to insert product because product was already inserted wi id ";
    private static final String PRODUCT_WITHOUT_ID = "Unable to update product because there is no id was informed";
    private static final String INVALID_PRODUCT = "Unable to insert product with invalid status";

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND + id));
    }

    public Product insert(Product product) {
        if (product.getId() != null) {
            throw new BusinesRuleException(PRODUCT_WITH_ID + product.getId());
        }
        if (ProductStatus.INACTIVE.equals(product.getStatus())) {
            throw new BusinesRuleException(INVALID_PRODUCT);
        }
        return productRepository.save(product);
    }

    public Product update(Product product) {
        if (product.getId() == null) {
            throw new BusinesRuleException(PRODUCT_WITHOUT_ID);
        }
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND + id));
        productRepository.delete(product);
    }

}
