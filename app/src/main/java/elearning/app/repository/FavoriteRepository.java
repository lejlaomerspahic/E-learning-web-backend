package elearning.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elearning.app.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
