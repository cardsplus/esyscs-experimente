package esy;

import esy.api.*;
import esy.api.Enum;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class EsyBackendOauth2LoginRestController {

    @GetMapping("/version")
    public Map<String, Object> getVersion() {
        final var allProperty = new LinkedHashMap<String, Object>();
        allProperty.put("major", "0");
        allProperty.put("minor", "1");
        allProperty.put("version", "0.1");
        allProperty.put("oauth2", "github");
        return allProperty;
    }

    @GetMapping("/login/{provider}")
    public RedirectView login(@PathVariable String provider) {
        final var redirectView = new RedirectView();
        redirectView.setUrl(DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/" + provider);
        return redirectView;
    }

    @GetMapping("/who")
    public Map<String, Object> who(@AuthenticationPrincipal OAuth2User principal) {
        final var allProperty = new LinkedHashMap<>(principal.getAttributes());
        Objects.requireNonNull(allProperty.get("id"));
        Objects.requireNonNull(allProperty.get("login"));
        Objects.requireNonNull(allProperty.get("email"));
        Objects.requireNonNull(allProperty.get("name"));
        allProperty.put("titel", allProperty.get("name"));
        allProperty.put("allRole", Set.of("VERWALTUNG"));
        allProperty.put("allGroup", Set.of());
        return allProperty;
    }

    @GetMapping("/api/enum/kanal")
    public ResponseEntity<CollectionModel<Enum>> apiEnumGetAllKanal() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Enum.loadAllKanal("kanal")));
    }

    @GetMapping("/api/enum/land")
    public ResponseEntity<CollectionModel<Enum>> apiEnumGetAllLand() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Enum.loadAllLand("land")));
    }

    @GetMapping("/api/enum/quelle")
    public ResponseEntity<CollectionModel<Enum>> apiEnumGetAllQuelle() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Enum.loadAllQuelle("quelle")));
    }

    @GetMapping("/api/enum/sprache")
    public ResponseEntity<CollectionModel<Enum>> apiEnumGetAllSprache() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Enum.loadAllSprache("sprache")));
    }

    @GetMapping("/api/adresse")
    public ResponseEntity<CollectionModel<Adresse>> apiAdresseGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Adresse.loadAll()));
    }

    @GetMapping("/api/adresse/search/findAllItem")
    public ResponseEntity<CollectionModel<Item>> apiAdresseFindAllItem() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Adresse.loadAll()
                        .stream()
                        .map(e -> new Item(e.getId(), e.getAnschrift().toString()))
                        .collect(Collectors.toList())));
    }

    @GetMapping("/api/gruppe")
    public ResponseEntity<CollectionModel<Gruppe>> apiGruppeGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Gruppe.loadAll()));
    }

    @GetMapping("/api/gruppe/search/findAllItem")
    public ResponseEntity<CollectionModel<Item>> apiGruppeFindAllItem() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Gruppe.loadAll()
                        .stream()
                        .map(e -> new Item(e.getId(), e.getName()))
                        .collect(Collectors.toList())));
    }

    @GetMapping("/api/nutzer")
    public ResponseEntity<CollectionModel<Nutzer>> apiNutzerGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Nutzer.loadAll()));
    }

    @GetMapping("/api/nutzer/search/findAllItem")
    public ResponseEntity<CollectionModel<Item>> apiNutzerFindAllItem() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Nutzer.loadAll()
                        .stream()
                        .map(e -> new Item(e.getId(), e.getName()))
                        .collect(Collectors.toList())));
    }

    @GetMapping("/api/projekt")
    public ResponseEntity<CollectionModel<Projekt>> apiProjektGetAll() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Projekt.loadAll()));
    }

    @GetMapping("/api/projekt/search/findAllItem")
    public ResponseEntity<CollectionModel<Item>> apiProjektFindAllItem() {
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                Projekt.loadAll()
                        .stream()
                        .map(e -> new Item(e.getId(), e.getName()))
                        .collect(Collectors.toList())));
    }
}
