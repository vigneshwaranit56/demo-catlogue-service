package com.example.catalog.service;

import com.example.catalog.entity.Catalog;
import com.example.catalog.exception.ResourceNotFoundException;
import com.example.catalog.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final CatalogRepository repository;

    public CatalogService(CatalogRepository repository) {
        this.repository = repository;
    }

    public Catalog create(Catalog catalog) {
        return repository.save(catalog);
    }

    public List<Catalog> getAll() {
        return repository.findAll();
    }

    public Catalog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Catalog not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
