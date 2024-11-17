package engineer.comanmadalin.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import engineer.comanmadalin.actions.BaseAction;
import engineer.comanmadalin.actions.debug.*;
import engineer.comanmadalin.actions.specific.*;
import engineer.comanmadalin.actions.statistics.GetPlayerWins;
import engineer.comanmadalin.actions.statistics.GetTotalGamesPlayed;
import engineer.comanmadalin.cards.BaseCard;
import engineer.comanmadalin.cards.Deck;
import engineer.comanmadalin.cards.hero.BaseHero;
import engineer.comanmadalin.game.Game;
import engineer.comanmadalin.game.Input;
import engineer.comanmadalin.json.deserializers.*;
import engineer.comanmadalin.json.serializers.BaseCardSerializer;
import engineer.comanmadalin.json.serializers.BaseHeroSerializer;
import engineer.comanmadalin.json.serializers.DeckSerializer;
import engineer.comanmadalin.json.serializers.GameSerializer;
import engineer.comanmadalin.json.serializers.actions.debug.*;
import engineer.comanmadalin.json.serializers.actions.specific.*;
import engineer.comanmadalin.json.serializers.actions.statistics.GetPlayerWinsSerializer;
import engineer.comanmadalin.json.serializers.actions.statistics.GetTotalGamesPlayedSerializer;
import lombok.Getter;

/**
 * The type Json utils.
 */
@Getter
public final class JsonUtils {
    @Getter
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final SimpleModule CUSTOM_DESERIALIZERS = new SimpleModule() {{
        addDeserializer(Input.class, new InputDeserializer(Input.class));
        addDeserializer(Game.class, new GameDeserializer(Game.class));
        addDeserializer(BaseAction.class, new BaseActionDeserializer(BaseAction.class));
        addDeserializer(Deck.class, new DeckDeserializer(Deck.class));
        addDeserializer(BaseCard.class, new BaseCardDeserializer(BaseCard.class));
    }};

    private static final SimpleModule CUSTOM_SERIALIZERS = new SimpleModule() {{
        addSerializer(Game.class, new GameSerializer(Game.class));
        addSerializer(Deck.class, new DeckSerializer(Deck.class));
        addSerializer(BaseHero.class, new BaseHeroSerializer(BaseHero.class));
        addSerializer(BaseCard.class, new BaseCardSerializer(BaseCard.class));

        addSerializer(GetPlayerDeck.class, new GetPlayerDeckSerializer(GetPlayerDeck.class));
        addSerializer(GetPlayerHero.class, new GetPlayerHeroSerializer(GetPlayerHero.class));
        addSerializer(GetPlayerTurn.class, new GetPlayerTurnSerializer(GetPlayerTurn.class));
        addSerializer(GetPlayerMana.class, new GetPlayerManaSerializer(GetPlayerMana.class));

        addSerializer(GetCardAtPosition.class,
                new GetCardAtPositionSerializer(GetCardAtPosition.class));
        addSerializer(GetCardsInHand.class, new GetCardsInHandSerializer(GetCardsInHand.class));
        addSerializer(GetFrozenCardsOnTable.class,
                new GetFrozenCardsOnTableSerializer(GetFrozenCardsOnTable.class));
        addSerializer(GetCardsOnTable.class, new GetCardsOnTableSerializer(GetCardsOnTable.class));

        addSerializer(PlaceCard.class, new PlaceCardSerializer(PlaceCard.class));
        addSerializer(CardUsesAttack.class, new CardUsesAttackSerializer(CardUsesAttack.class));
        addSerializer(EndPlayerTurn.class, new EmptySerializer<>(EndPlayerTurn.class));
        addSerializer(CardUsesAbility.class, new CardUsesAbilitySerializer(CardUsesAbility.class));
        addSerializer(UseHeroAbility.class, new UseHeroAbilitySerializer(UseHeroAbility.class));

        addSerializer(UseAttackHero.class, new GameEndedSerializer(UseAttackHero.class));

        addSerializer(GetPlayerWins.class, new GetPlayerWinsSerializer(GetPlayerWins.class));
        addSerializer(GetTotalGamesPlayed.class,
                new GetTotalGamesPlayedSerializer(GetTotalGamesPlayed.class));
    }};


    static {
        OBJECT_MAPPER.registerModule(CUSTOM_DESERIALIZERS);
        OBJECT_MAPPER.registerModule(CUSTOM_SERIALIZERS);

        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

}
