package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;

import java.util.ArrayList;

public class GeneralKocioraw extends BaseHero {
    public GeneralKocioraw(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability() {
        // TODO: Implement Blood-Thirst ability
    }
}
