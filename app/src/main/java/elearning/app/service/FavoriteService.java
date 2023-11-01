package elearning.app.service;

import java.util.List;
import java.util.Optional;

import elearning.app.dto.favorite.FavoriteCreateRequest;
import elearning.app.model.Favorite;

public interface FavoriteService {

    Favorite createFavorite(FavoriteCreateRequest request);

    void deleteFavorite(Long id);

    Optional<Favorite> getFavorite(Long id);

    List<Favorite> getFavorites();

}