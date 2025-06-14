package com.invent.inventory.controller;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
    private final EntityManager em;

    public AuditController(EntityManager em) {
        this.em = em;
    }

    @GetMapping
    public ResponseEntity<?> getRevisions(
            @RequestParam String entity,
            @RequestParam Long id) {
        try {
            Class<?> clazz = Class.forName("com.invent.inventory.entity." + entity);
            AuditReader reader = AuditReaderFactory.get(em);

            // get all revision numbers for this entity+id
            List<Number> revisions = reader.getRevisions(clazz, id);

            // for each revision, load the entity state
            List<?> history = revisions.stream()
                    .map(rev -> reader.find(clazz, id, rev))
                    .toList();

            return ResponseEntity.ok(history);
        } catch (ClassNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body("Unknown entity: " + entity);
        }
    }
}
