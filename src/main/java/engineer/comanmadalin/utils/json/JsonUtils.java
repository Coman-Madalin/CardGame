package engineer.comanmadalin.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.specific.*;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.deck.Deck;
import engineer.comanmadalin.deck.PlayerDecks;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.GameConditions;
import engineer.comanmadalin.game.Input;
import engineer.comanmadalin.utils.json.deserializers.*;
import engineer.comanmadalin.utils.json.serializers.*;
import engineer.comanmadalin.utils.json.serializers.actions.*;
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
        addSerializer(GetPlayerMana.class, new GetPlayerManaSerializer(GetPlayerMana.class));

        addSerializer(GetCardsOnTable.class, new GetCardsOnTableSerializer(GetCardsOnTable.class));
        addSerializer(GetCardsInHand.class, new GetCardsInHandSerializer(GetCardsInHand.class));

        addSerializer(PlaceCard.class, new PlaceCardSerializer(PlaceCard.class));
        addSerializer(EndPlayerTurn.class, new EmptySerializer<>(EndPlayerTurn.class));
    }};


    static {
        objectMapper.registerModule(customDeserializers);
        objectMapper.registerModule(customSerializers);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

}
