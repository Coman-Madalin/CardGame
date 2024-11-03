package engineer.comanmadalin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.specific.GetPlayerDeck;
import engineer.comanmadalin.actions.specific.GetPlayerHero;
import engineer.comanmadalin.actions.specific.GetPlayerTurn;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;
import engineer.comanmadalin.deserializers.*;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.GameConditions;
import engineer.comanmadalin.game.Input;
import engineer.comanmadalin.serializers.BaseCardSerializer;
import engineer.comanmadalin.serializers.BaseHeroSerializer;
import engineer.comanmadalin.serializers.DeckSerializer;
import engineer.comanmadalin.serializers.GameSerializer;
import engineer.comanmadalin.serializers.actions.GetPlayerDeckSerializer;
import engineer.comanmadalin.serializers.actions.GetPlayerHeroSerializer;
import engineer.comanmadalin.serializers.actions.GetPlayerTurnSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonUtils {
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final SimpleModule customDeserializers = new SimpleModule() {{
        addDeserializer(Input.class, new InputDeserializer(Input.class));
        addDeserializer(Game.class, new GameDeserializer(Game.class));
        addDeserializer(BaseAction.class, new BaseActionDeserializer(BaseAction.class));
        addDeserializer(PlayerDecks.class, new PlayerDecksDeserializer(PlayerDecks.class));
        addDeserializer(GameConditions.class, new GameConditionsDeserializer(Game.class));
        addDeserializer(Deck.class, new DeckDeserializer(Deck.class));
        addDeserializer(BaseCard.class, new BaseCardDeserializer(BaseCard.class));
    }};

    private static final SimpleModule customSerializers = new SimpleModule() {{
        addSerializer(Game.class, new GameSerializer(Game.class));
        addSerializer(Deck.class, new DeckSerializer(Deck.class));
        addSerializer(BaseHero.class, new BaseHeroSerializer(BaseHero.class));
        addSerializer(BaseCard.class, new BaseCardSerializer(BaseCard.class));

        addSerializer(GetPlayerDeck.class, new GetPlayerDeckSerializer(GetPlayerDeck.class));
        addSerializer(GetPlayerHero.class, new GetPlayerHeroSerializer(GetPlayerHero.class));
        addSerializer(GetPlayerTurn.class, new GetPlayerTurnSerializer(GetPlayerTurn.class));
    }};


    static {
        objectMapper.registerModule(customDeserializers);
        objectMapper.registerModule(customSerializers);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

}
