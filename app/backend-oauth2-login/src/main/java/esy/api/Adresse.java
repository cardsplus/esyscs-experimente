package esy.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Adresse {
    private final UUID id;
    private final Anschrift anschrift;

    public static List<Adresse> loadAll() {
        final var idPrefix = "4f31e3db-1e3d-4283-a45b-";
        return List.of(
                new Adresse(UUID.fromString(idPrefix + "000000000001"),
                        new Anschrift("Brunwiesweg 3", "5400", "Hallein", "AT")));
    }
}
