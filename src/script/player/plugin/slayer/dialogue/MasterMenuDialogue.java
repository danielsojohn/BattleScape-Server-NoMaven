package script.player.plugin.slayer.dialogue;

import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.slayer.SlayerMaster;
import lombok.var;
import script.player.plugin.slayer.SlayerPlugin;

public class MasterMenuDialogue extends SelectionDialogue {
    public MasterMenuDialogue(Player player) {
        var plugin = player.getPlugin(SlayerPlugin.class);
        addOption("Get task", (childId, slot) -> {
            new ChooseMasterDialogue(player);
        });
        addOption("Current task", (childId, slot) -> {
            plugin.sendTask();
        });
        addOption("Get boss task", (childId, slot) -> {
            plugin.getAssignment(SlayerMaster.BOSS_MASTER);
        });
        addOption("Cancel boss task", (childId, slot) -> {
            if (player.getInventory().getCount(ItemId.COINS) < 500000) {
                player.getGameEncoder().sendMessage("You need 500K coins to do this.");
                return;
            }
            if (player.getInventory().getCount(ItemId.VOTE_TICKET) < 2) {
                player.getGameEncoder().sendMessage("You need 2 vote tickets to do this.");
                return;
            }
            if (plugin.getBossTask().isComplete()) {
                player.getGameEncoder().sendMessage("You don't have a boss task to cancel.");
                return;
            }
            plugin.getBossTask().cancel();
            player.getInventory().deleteItem(ItemId.COINS, 500000);
            player.getInventory().deleteItem(ItemId.VOTE_TICKET, 2);
            player.getGameEncoder().sendMessage("Your boss task has been cancelled.");
        });
        open(player);
    }
}