package script.player.plugin.slayer.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.slayer.SlayerMaster;
import lombok.var;
import script.player.plugin.slayer.SlayerPlugin;

public class WildernessMasterMenuDialogue extends SelectionDialogue {
    public WildernessMasterMenuDialogue(Player player) {
        var plugin = player.getPlugin(SlayerPlugin.class);
        addOption("Get task", (childId, slot) -> {
            plugin.getAssignment(SlayerMaster.WILDERNESS_MASTER);
        });
        addOption("Current task", (childId, slot) -> {
            plugin.sendTask();
        });
        addOption("Cancel task (30 points)", (childId, slot) -> {
            plugin.cancelWildernessTask();
        });
        open(player);
    }
}
