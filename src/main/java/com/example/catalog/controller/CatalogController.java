package com.example.catalog.controller;

import com.example.catalog.entity.Catalog;
import com.example.catalog.service.CatalogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogs")
public class CatalogController {

    private final CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Catalog> create(@Valid @RequestBody Catalog catalog) {
        Catalog created = service.create(catalog);
        return ResponseEntity
                .created(URI.create("/api/v1/catalogs/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<Catalog>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
