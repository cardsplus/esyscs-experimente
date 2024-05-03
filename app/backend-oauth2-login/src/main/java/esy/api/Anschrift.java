package esy.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Anschrift {
    private final String strasse;
    private final String plz;
    private final String ort;
    private final String land;
}
