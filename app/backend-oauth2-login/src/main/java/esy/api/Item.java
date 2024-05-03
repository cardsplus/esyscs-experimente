package esy.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Item {
    private final UUID value;
    private final String text;
}
