package engineer.comanmadalin.cards;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public abstract class BaseCard {
    private int mana;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public BaseCard() {
    }

    public BaseCard(int mana, int health, String description, ArrayList<String> colors, String name) {
        this.mana = mana;
        this.health = health;
        this.description = description;
        this.colors = colors;
        this.name = name;
    }
}
