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
    public Favorite createFavorite(FavoriteCreateRequest request) {
        Favorite favorite = new Favorite();
        favorite.setUser(request.getUser());

        if (request.getCourse() != null) {
            favorite.setCourse(request.getCourse());
        }

        if (request.getProduct() != null) {
            favorite.setProduct(request.getProduct());

        }

        return favoriteRepository.save(favorite);
    }

}