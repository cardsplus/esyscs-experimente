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
public class Gruppe {
    private final UUID id;
    private final String name;

    public static List<Gruppe> loadAll() {
        return List.of();
    }
}
