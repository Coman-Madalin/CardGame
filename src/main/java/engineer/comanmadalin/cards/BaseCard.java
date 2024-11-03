package engineer.comanmadalin.cards;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public abstract class BaseCard implements Cloneable {
    private int mana;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public BaseCard(int mana, int health, String description, ArrayList<String> colors, String name) {
        this.mana = mana;
        this.health = health;
        this.description = description;
        this.colors = colors;
        this.name = name;
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
