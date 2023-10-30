package elearning.app.serviceImpl;

import org.springframework.stereotype.Service;

import elearning.app.dto.favorite.FavoriteCreateRequest;
import elearning.app.model.Favorite;
import elearning.app.repository.FavoriteRepository;
import elearning.app.service.FavoriteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public Favorite create(FavoriteCreateRequest favorite) {
        Favorite favorites = new Favorite();
        favorites.setProduct(favorite.getProduct());
        favorites.setUser(favorite.getUser());
        favorites.setCourse(favorite.getCourse());
        return favoriteRepository.save(favorites);

    }

}