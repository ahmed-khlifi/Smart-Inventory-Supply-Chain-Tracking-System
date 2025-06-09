package com.invent.inventory.service;

import com.invent.inventory.Iservice.IProductService;
import com.invent.inventory.entity.Product;
import com.invent.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository repo;
    public List<Product> findAll() { return repo.findAll(); }
    public Optional<Product> findById(Long id) { return repo.findById(id); }
    public Product save(Product product) { return repo.save(product); }
    public void deleteById(Long id) { repo.deleteById(id); }
}