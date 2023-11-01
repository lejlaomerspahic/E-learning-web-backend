package elearning.app.contoller.favoriteController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elearning.app.dto.favorite.FavoriteCreateRequest;
import elearning.app.model.Favorite;
import elearning.app.service.FavoriteService;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/create")
    public Favorite createFavorite(@RequestBody FavoriteCreateRequest favorite) {
        return favoriteService.createFavorite(favorite);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
    }

    @GetMapping("/{id}")
    public Optional<Favorite> getFavorite(@PathVariable Long id) {
        return favoriteService.getFavorite(id);
    }

    @GetMapping("/")
    public List<Favorite> getFavorites() {
        return favoriteService.getFavorites();
    }

}
