package rs.ac.singidunum.apartment.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.FavoriteApmts;
import rs.ac.singidunum.apartment.service.IFavoriteApartmentsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("favorite")
public class FavoriteApartmentsController {

    private IFavoriteApartmentsService favoriteApartmentsService;

    public FavoriteApartmentsController(IFavoriteApartmentsService favoriteApartmentsService) {
        this.favoriteApartmentsService = favoriteApartmentsService;
    }

    @CrossOrigin("*")
    @GetMapping("get/favorites/{id}")
    public List<Apartment> GetFavoriteApartments (@PathVariable("id") Integer id){
        return favoriteApartmentsService.GetFavoriteApmts(id);
    }
    @PostMapping("add/favorite")
    @CrossOrigin("*")
    public void AddFavoriteApartment (@RequestBody FavoriteApmts favoriteApmts){
        favoriteApartmentsService.AddFavoriteApartment(favoriteApmts);
    }

    @CrossOrigin("*")
    @PostMapping("delete")
    public void DeleteFavoriteApartment (@RequestBody FavoriteApmts favoriteApmts){
        favoriteApartmentsService.DeleteFavoriteApmts(favoriteApmts);
    }
}
