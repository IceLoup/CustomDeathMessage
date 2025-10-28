package xyz.pyxismc.pyxis.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Sound;
import xyz.pyxismc.customDeathMessage.files.DeathMessage;

import java.util.List;
import java.util.Random;

public class PlayerDeathListeners implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();

        // Si tué par un autre joueur
        if (killer != null) {
            List<String> pvpMessages = DeathMessage.get().getStringList("death-messages.pvp");
            if (!pvpMessages.isEmpty()) {
                String message = getRandomMessage(pvpMessages);
                message = message.replace("%victim%", player.getName())
                        .replace("%killer%", killer.getName());
                e.setDeathMessage(message);
            }
        }
        // Mort naturelle
        else {
            String cause = e.getDeathMessage();
            String message = null;

            if (cause != null && cause.contains("fell")) {
                List<String> fallMessages = DeathMessage.get().getStringList("death-messages.fall");
                if (!fallMessages.isEmpty()) {
                    message = getRandomMessage(fallMessages);
                }
            } else if (cause != null && cause.contains("drowned")) {
                List<String> drownMessages = DeathMessage.get().getStringList("death-messages.drown");
                if (!drownMessages.isEmpty()) {
                    message = getRandomMessage(drownMessages);
                }
            } else if (cause != null && (cause.contains("burned") || cause.contains("lava") || cause.contains("fire"))) {
                List<String> burnMessages = DeathMessage.get().getStringList("death-messages.burn");
                if (!burnMessages.isEmpty()) {
                    message = getRandomMessage(burnMessages);
                }
            } else if (cause != null && (cause.contains("explosion") || cause.contains("blown") || cause.contains("creeper"))) {
                List<String> explosionMessages = DeathMessage.get().getStringList("death-messages.explosion");
                if (!explosionMessages.isEmpty()) {
                    message = getRandomMessage(explosionMessages);
                }
            } else {
                List<String> genericMessages = DeathMessage.get().getStringList("death-messages.generic");
                if (!genericMessages.isEmpty()) {
                    message = getRandomMessage(genericMessages);
                }
            }

            if (message != null) {
                message = message.replace("%player%", player.getName());
                e.setDeathMessage(message);
            }
        }
    }

    private String getRandomMessage(List<String> messages) {
        return messages.get(random.nextInt(messages.size()));
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = player.getKiller();

        // Afficher le titre au tueur
        if (killer != null) {
            // Vérifier si le son et le titre sont activés
            boolean soundEnabled = DeathMessage.get().getBoolean("kill-notification.sound.enabled", true);
            boolean titleEnabled = DeathMessage.get().getBoolean("kill-notification.title.enabled", true);

            // Jouer le son
            if (soundEnabled) {
                String soundName = DeathMessage.get().getString("kill-notification.sound.type", "BLOCK_NOTE_BLOCK_HARP");
                float volume = (float) DeathMessage.get().getDouble("kill-notification.sound.volume", 1.0);
                float pitch = (float) DeathMessage.get().getDouble("kill-notification.sound.pitch", 1.0);

                try {
                    Sound sound = Sound.valueOf(soundName);
                    killer.playSound(killer.getLocation(), sound, volume, pitch);
                } catch (IllegalArgumentException ex) {
                    killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 1.0f);
                }
            }

            // Envoyer le titre
            if (titleEnabled) {
                String titleFormat = DeathMessage.get().getString("kill-notification.title.format", "§8[§c \uD83D\uDDE1 §8-§c %victim% §8]");
                String titleText = titleFormat.replace("%victim%", player.getName())
                        .replace("%killer%", killer.getName());

                int fadeIn = DeathMessage.get().getInt("kill-notification.title.fade-in", 0);
                int stay = DeathMessage.get().getInt("kill-notification.title.stay", 40);
                int fadeOut = DeathMessage.get().getInt("kill-notification.title.fade-out", 20);

                killer.sendTitle(titleText, "", fadeIn, stay, fadeOut);
            }
        }
    }
}
