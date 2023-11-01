package elearning.app.serviceImpl;

import java.util.List;
import java.util.Optional;

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
    public Favorite createFavorite(FavoriteCreateRequest request) {

        Favorite favorite = new Favorite();
        favorite.setUser(request.getUser());

        if (request.getCourse() != null) {
            favorite.setCourse(request.getCourse());
        } else {
            favorite.setCourse(null);
        }

        if (request.getProduct() != null) {
            favorite.setProduct(request.getProduct());
        } else {
            favorite.setProduct(null);
        }

        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);

    }

    @Override
    public Optional<Favorite> getFavorite(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public List<Favorite> getFavorites() {
        return favoriteRepository.findAll();
    }

}