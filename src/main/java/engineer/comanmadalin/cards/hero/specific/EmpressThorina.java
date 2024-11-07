package engineer.comanmadalin.cards.hero.specific;

import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.cards.minion.BaseMinionCard;
import engineer.comanmadalin.game.Game;

import java.util.ArrayList;
import java.util.List;

public class EmpressThorina extends BaseHero {
    public EmpressThorina(int mana, int health, String description, ArrayList<String> colors, String name) {
        super(mana, health, description, colors, name);
    }

    @Override
    public void ability(Game game, int affectedRow) {
        int maxHealth = 0;
        int maxHealthCardIndex = -1;

        List<BaseMinionCard> row = game.getBoard().get(affectedRow);
        for (int i = 0; i < row.size(); i++) {
            if (maxHealth < row.get(i).getHealth()) {
                maxHealth = row.get(i).getHealth();
                maxHealthCardIndex = i;
            }
        }

        row.remove(maxHealthCardIndex);
    }
}
