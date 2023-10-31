package elearning.app.service;

import elearning.app.dto.favorite.FavoriteCreateRequest;
import elearning.app.model.Favorite;

public interface FavoriteService {

    Favorite createFavorite(FavoriteCreateRequest request);

}