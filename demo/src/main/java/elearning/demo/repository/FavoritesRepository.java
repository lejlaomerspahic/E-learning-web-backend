package elearning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elearning.demo.models.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}