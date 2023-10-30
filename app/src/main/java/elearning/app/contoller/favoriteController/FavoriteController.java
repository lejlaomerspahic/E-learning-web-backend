package elearning.app.contoller.favoriteController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.favorite.FavoriteCreateRequest;
import elearning.app.model.Favorite;
import elearning.app.service.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/create")
    public Favorite createFavorite(@RequestBody FavoriteCreateRequest favorite) {
        return favoriteService.create(favorite);
    }
}
