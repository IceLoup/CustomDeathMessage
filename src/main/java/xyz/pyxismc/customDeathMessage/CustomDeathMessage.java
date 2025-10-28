package xyz.pyxismc.customDeathMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.pyxismc.customDeathMessage.files.DeathMessage;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

public final class CustomDeathMessage extends JavaPlugin {

    // À ajouter dans ta classe Main lors du onEnable()

    @Override
    public void onEnable() {
        // Setup du fichier de configuration
        DeathMessage.setup();

        // Charger les valeurs par défaut si le fichier est vide
        if (DeathMessage.get().getKeys(false).isEmpty()) {
            loadDefaultMessages();
            DeathMessage.save();
        }

        // Enregistrer le listener
        getServer().getPluginManager().registerEvents(new xyz.pyxismc.pyxis.listeners.PlayerDeathListeners(), this);
    }

    private void loadDefaultMessages() {
        FileConfiguration config = DeathMessage.get();

        // Messages PvP
        config.set("death-messages.pvp", Arrays.asList(
                "§c☠ %victim% §7was obliterated by §a\uD83D\uDDE1 %killer%",
                "§c☠ %victim% §7got rekt by §a\uD83D\uDDE1 %killer%",
                "§a\uD83D\uDDE1 %killer% §7sent §c☠ %victim% §7to the shadow realm",
                "§c☠ %victim% §7stood no chance against §a\uD83D\uDDE1 %killer%",
                "§a\uD83D\uDDE1 %killer% §7turned §c☠ %victim% §7into a ragdoll",
                "§c☠ %victim% §7was demolished by §a\uD83D\uDDE1 %killer%",
                "§a\uD83D\uDDE1 %killer% §7ended §c☠ %victim%§7's career",
                "§c☠ %victim% §7got destroyed by §a\uD83D\uDDE1 %killer%",
                "§a\uD83D\uDDE1 %killer% §7showed §c☠ %victim% §7who's boss",
                "§c☠ %victim% §7was no match for §a\uD83D\uDDE1 %killer%"
        ));

        // Messages de chute
        config.set("death-messages.fall", Arrays.asList(
                "§c☠ %player% §7forgot gravity exists",
                "§c☠ %player% §7tested fall damage... it works",
                "§c☠ %player% §7believed they could fly",
                "§c☠ %player% §7discovered the ground",
                "§c☠ %player% §7failed their parkour",
                "§c☠ %player% §7went splat",
                "§c☠ %player% §7learned about terminal velocity",
                "§c☠ %player% §7fell like a stone",
                "§c☠ %player% §7experienced rapid unplanned descent",
                "§c☠ %player% §7missed the water bucket"
        ));

        // Messages de noyade
        config.set("death-messages.drown", Arrays.asList(
                "§c☠ %player% §7slept with the fishes",
                "§c☠ %player% §7forgot their swimming lessons",
                "§c☠ %player% §7became a submarine",
                "§c☠ %player% §7inhaled H2O",
                "§c☠ %player% §7went under... permanently",
                "§c☠ %player% §7tried to breathe underwater",
                "§c☠ %player% §7became fish food",
                "§c☠ %player% §7didn't respect the ocean",
                "§c☠ %player% §7was claimed by the depths",
                "§c☠ %player% §7skipped swimming class"
        ));

        // Messages de feu
        config.set("death-messages.burn", Arrays.asList(
                "§c☠ %player% §7became extra crispy",
                "§c☠ %player% §7was well done",
                "§c☠ %player% §7turned into charcoal",
                "§c☠ %player% §7played with fire and lost",
                "§c☠ %player% §7became a human torch",
                "§c☠ %player% §7was barbecued",
                "§c☠ %player% §7melted like butter",
                "§c☠ %player% §7tried to swim in lava",
                "§c☠ %player% §7went up in smoke",
                "§c☠ %player% §7was cooked to perfection"
        ));

        // Messages d'explosion
        config.set("death-messages.explosion", Arrays.asList(
                "§c☠ %player% §7went boom",
                "§c☠ %player% §7was blown to pieces",
                "§c☠ %player% §7had an explosive experience",
                "§c☠ %player% §7became confetti",
                "§c☠ %player% §7hugged a creeper",
                "§c☠ %player% §7was atomized",
                "§c☠ %player% §7discovered TNT the hard way"
        ));

        // Messages génériques
        config.set("death-messages.generic", Arrays.asList(
                "§c☠ %player% §7died of natural causes (totally)",
                "§c☠ %player% §7has left the game... permanently",
                "§c☠ %player% §7was not the impostor",
                "§c☠ %player% §7skill issue",
                "§c☠ %player% §7didn't make it",
                "§c☠ %player% §7has been deleted",
                "§c☠ %player% §7achieved death",
                "§c☠ %player% §7was sent to respawn",
                "§c☠ %player% §7needs to git gud",
                "§c☠ %player% §7has disconnected from life"
        ));

        // Configuration de la notification de kill
        config.set("kill-notification.sound.enabled", true);
        config.set("kill-notification.sound.type", "BLOCK_NOTE_BLOCK_HARP");
        config.set("kill-notification.sound.volume", 1.0);
        config.set("kill-notification.sound.pitch", 1.0);

        config.set("kill-notification.title.enabled", true);
        config.set("kill-notification.title.format", "§8[§c \uD83D\uDDE1 §8-§c %victim% §8]");
        config.set("kill-notification.title.fade-in", 0);
        config.set("kill-notification.title.stay", 40);
        config.set("kill-notification.title.fade-out", 20);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("CustomDeathMessage has been disabled!");
    }
}
