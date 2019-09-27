package script.player.plugin.slayer.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import script.player.plugin.slayer.SlayerPlugin;

public class RewardsDialogue extends SelectionDialogue {
    public RewardsDialogue(Player player, SlayerPlugin plugin) {
        addOption("Slayer rewards", (childId, slot) -> {
            plugin.openRewards();
        });
        addOption("Boss Slayer rewards", (childId, slot) -> {
            player.openShop("boss_slayer");
        });
        open(player);
    }
}
