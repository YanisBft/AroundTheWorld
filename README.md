# Around The World

Around The World is a mod made for [ModFest 1.17](https://modfest.net/1.17/). It allows players to travel to specific biomes.

## Gameplay

To start with the mod, you need to get both a **traveler** and a **traveler key**.
These items will be tied to you, and you won't be able to use other players' travelers and traveler keys.

![Traveler Recipe](https://user-images.githubusercontent.com/16228338/124362845-d6ccf380-dc37-11eb-811d-59d7616bba90.png)
![Traveler Key Recipe](https://user-images.githubusercontent.com/16228338/124362844-d59bc680-dc37-11eb-8844-5a26472cd173.png)

Then, you need to choose a biome you want to travel in. This is done with items called **biome emblems**.
Every emblem has a different recipe, using a specific block of the biome.

*13 biome emblems are currently available.*

## Customization

The traveler can be customized in two different ways.

### Disabling biome emblems

If you want the players to not be able to travel to some biomes, you can add biome emblems to the ``aroundtheworld:disabled_biome_emblems`` item tag.

### Setting a cooldown

If you run a server with many players, you may want to set a cooldown before traveling again to improve performance.
You can do that with the ``travelerUseCooldown`` game rule, which defines a cooldown in seconds. It defaults to 0 (no cooldown).
