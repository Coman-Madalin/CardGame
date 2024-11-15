package engineer.comanmadalin.cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class BaseCard implements Cloneable {
    private int manaCost;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private Boolean attackedThisRound = false;

    public BaseCard(final int manaCost, final int health, final String description,
                    final ArrayList<String> colors, final String name) {
        this.manaCost = manaCost;
        this.health = health;
        this.description = description;
        this.colors = colors;
        this.name = name;
    }

    public void takeDamage(final int damage) {
        this.health -= damage;
    }

    @Override
    public BaseCard clone() {
        try {
            final BaseCard clone = (BaseCard) super.clone();
            clone.colors = new ArrayList<>(colors);
            return clone;
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
