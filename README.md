# GwentStone

## Overview

This project is a Java-based framework for creating and running a card game. <p>
Currently, the variety of cards / what cards can do / actions is pretty limited. <p>
It needs an input json file and will write a json file where you can see the result of most of the actions called. <p>
To run, it will need an input json file described [here](#how-to-create-a-test).
---

## Project Structure

### Source Code (`/src/main/java/org/poo`)

- **Actions**:
    - All of them are a subclass of the abstract class `BaseAction`.
    - General actions: The actions that we expect the player to use (`PlaceCard`).
    - Statistical actions: Information about the games played until now, in the current test (`GetPlayerWins`).
    - Debugging utilities: Inspect the game state (`GetPlayerDeck`).
- **Cards**:
    - All of them are a subclass of the abstract class `BaseCard`.
    - After that, we specialize them into `BaseHero` or `BaseMinionCard`.
        - If the minion has a special ability, it will extend `BaseSpecialCard`.
    - Specific implementations examples `EmpressThorina`, `Disciple`, `Berserker`.
- **Game Management**:
    - `Test` and `Main` have the scope to open the test json file, deserialize it using `InputDeserializer` and then
      running `.playAllGames()` on it.
    - The actual game logic is present in `Input` and `Game`.
- **Player**:
    - Player-specific details:
        - `PlayerData` holds the data got from input and will suffer no changes from start to finish.
        - `Player` is the actual class which will be used to simulate the game.
- **Json**:
    - Custom deserializers and serializers for processing data.

### Entry Points

- **`Main.java`**: Main class that runs all tests from ./input and verify them against the corresponding file from
  ./ref/
- **`Test.java`**: It's used to test a single input json present in ./input. The output will be put in `out.txt`

---

## Extending the Framework

### Adding New Cards

- Navigate to the appropriate package:
    - if it is a common minion: `cards/minion/specific`.
    - if it is a minion that has an ability: `cards/minion/specials`.
    - if it is a hero: `cards/hero/specific`.
- Create a new class that extends the corresponding base class.
    - NOTE: you need a constructor that has only the parameters present in `BaseCard` as that one will be called by
      `BaseCardDeserializer`.
- Add the class in an entry in the corresponding map present in `BaseCardDeserializer`.

### Adding New Actions

- Navigate to the appropriate package.
    - To determine it, read section Project Structure > Source Code > Actions.
- Create a new class that extends `BaseAction`.
    - NOTE: you need a constructor that has only JsonNode's as parameters.
- Add the class in an entry in BOTH maps present in `BaseActionDeserializer`.
- Add the class in an entry in the SimpleModule named `CUSTOM_SERIALIZERS` present in `JsonUtils`.
    - You can use `EmptySerializer<>(...)` if you don't want to have the command printed in the output json.

---

## How to create a test

### Json Structure

```
ROOT
├── playerOneDecks (Object)
│   ├── nrCardsInDeck (Number, Optional)
│   ├── nrDecks (Number, Optional)
│   └── decks (Array of Arrays)
│       ├── Deck 1 (Array)
│       │   ├── Card (Object)
│       │   │   ├── mana (Number)
│       │   │   ├── attackDamage (Number)
│       │   │   ├── health (Number)
│       │   │   ├── description (String)
│       │   │   ├── colors (Array of Strings)
│       │   │   └── name (String)  
│       │   └── (Repeat for other cards)
│       ├── (Repeat for other decks)
│       .
│       .
│       .
│
├── playerTwoDecks (Object, same structure as playerOneDecks)
└── games (Array)
    ├── Game (Object)
    │   ├── startGame (Object)
    │   │   ├── playerOneDeckIdx (Number)
    │   │   ├── playerTwoDeckIdx (Number)
    │   │   ├── shuffleSeed (Number)
    │   │   ├── playerOneHero (Object)
    │   │   │   ├── mana (Number)
    │   │   │   ├── description (String)
    │   │   │   ├── colors (Array of Strings)
    │   │   │   └── name (String)
    │   │   └── playerTwoHero (Object, same structure as playerOneHero)
    │   ├── startingPlayer (Number)
    │   └── actions (Array)
    │       ├── Action (Object)
    │       │   ├── command (String)
    │       │   ├── <command_argument1> (Optional)
    │       │   ├── <command_argument2> (Optional)
    │       │   .
    │       │   .
    │       │   .
    │       │   
    │       ├── (Repeat for other actions)
    │       .
    │       .
    │       .
    │       
    ├── (Repeat for other games)
    .
    .
    .
```

### Official Actions Structures

```
ROOT
└── command ("endPlayerTurn", "getCardsOnTable", "getPlayerTurn", "getFrozenCardsOnTable", "getTotalGamesPlayed", 
             "getPlayerOneWins", "getPlayerTwoWins")
```

```
ROOT
├── command ("placeCard")
└── handIdx Number
```

```
ROOT
├── command ("getCardsInHand", "getPlayerDeck", "getPlayerHero", "getPlayerMana")
└── playerIdx (Number)
```

```
ROOT
├── command ("cardUsesAttack", "cardUsesAbility")
├── cardAttacker (Object)
│   ├── x (Number)
│   └── y (Number)
└── cardAttacked (Object)
    ├── x (Number)
    └── y (Number)
```

```
ROOT
├── command ("useAttackHero")
└── cardAttacker (Object)
    ├── x (Number)
    └── y (Number)
```

```
ROOT
├── command ("getCardAtPosition")
├── x (Number)
└── y (Number)
```

```
ROOT
├── command ("useHeroAbility")
└── affectedRow (Number)
```

---

## Notes

1. We needed two different Player objects because it supports multiple games in one test.
2. It is not interactive. It only processes an input json where all the actions are predefined.
    1. Most probably this won't be subject to change as this project is more intended for me to understand the logic
       behind a game of this genre more than making the game more user-friendly.
    2. Although, if you still want to try it out, see [this](#how-to-create-a-test).
3. It is build with jdk21.
    1. If for some reason jdk21 can't be used, the project is verified to work with jdk11 too, but without Maven.