package engineer.comanmadalin.utils;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;
import engineer.comanmadalin.deserializers.*;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.GameConditions;
import engineer.comanmadalin.game.Input;
import lombok.Getter;

public class RegisterDeserializers {
    @Getter
    private static final SimpleModule customDeserializers = new SimpleModule() {{
        addDeserializer(Input.class, new InputDeserializer(Input.class));
        addDeserializer(Game.class, new GameDeserializer(Game.class));
        addDeserializer(BaseAction.class, new BaseActionDeserializer(BaseAction.class));
        addDeserializer(PlayerDecks.class, new PlayerDecksDeserializer(PlayerDecks.class));
        addDeserializer(GameConditions.class, new GameConditionsDeserializer(Game.class));
        addDeserializer(Deck.class, new DeckDeserializer(Deck.class));
        addDeserializer(BaseCard.class, new BaseCardDeserializer(BaseCard.class));
    }};

}
