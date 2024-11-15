package engineer.comanmadalin.cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * The type Base card.
 */
@Getter
@Setter
public abstract class BaseCard implements Cloneable {
    private int manaCost;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private Boolean attackedThisRound = false;

    /**
     * Instantiates a new Base card.
     *
     * @param manaCost    the mana cost
     * @param health      the health
     * @param description the description
     * @param colors      the colors
     * @param name        the name
     */
    public BaseCard(final int manaCost, final int health, final String description,
                    final ArrayList<String> colors, final String name) {
        this.manaCost = manaCost;
        this.health = health;
        this.description = description;
        this.colors = colors;
        this.name = name;
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     */
    public void takeDamage(final int damage) {
        this.health -= damage;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return copy of this object
     */
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
