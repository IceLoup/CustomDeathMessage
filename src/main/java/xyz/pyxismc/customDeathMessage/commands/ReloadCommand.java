package xyz.pyxismc.customDeathMessage.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.pyxismc.customDeathMessage.files.DeathMessage;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("deathmessage.reload")) {
            sender.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        DeathMessage.reload();
        sender.sendMessage("§aLes messages de mort ont été rechargés avec succès !");
        return true;
    }
}

// N'oublie pas d'enregistrer la commande dans ton onEnable() :
// getCommand("reloaddeathmessages").setExecutor(new ReloadDeathMessagesCommand());

// Et d'ajouter dans ton plugin.yml :
/*
commands:
  reloaddeathmessages:
    description: Recharge les messages de mort personnalisés
    usage: /<command>
    permission: deathmessage.reload
    permission-message: Tu n'as pas la permission d'utiliser cette commande.
*/