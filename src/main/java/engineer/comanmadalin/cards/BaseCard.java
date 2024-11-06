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

    public BaseCard(int manaCost, int health, String description, ArrayList<String> colors, String name) {
        this.manaCost = manaCost;
        this.health = health;
        this.description = description;
        this.colors = colors;
        this.name = name;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public BaseCard clone() {
        try {
            BaseCard clone = (BaseCard) super.clone();
            clone.colors = new ArrayList<>(colors);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
