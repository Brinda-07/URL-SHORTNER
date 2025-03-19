
package com.example.urlshortner.repository;

import com.example.urlshortner.model.URL;  // Match the actual folder name

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface Repo extends JpaRepository<URL, Long> {
    Optional<URL> findByShortCode(String shortCode);
}
