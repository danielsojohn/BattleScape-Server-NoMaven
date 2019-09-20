package script.player.plugin.slayer.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import lombok.var;
import script.player.plugin.slayer.SlayerPlugin;

public class ChooseMasterDialogue extends SelectionDialogue {
    public ChooseMasterDialogue(Player player) {
        var plugin = player.getPlugin(SlayerPlugin.class);
        addOption("Mazchna - level 20", (childId, slot) -> {
            plugin.getAssignment("Mazchna");
        });
        addOption("Chaeldar - level 70", (childId, slot) -> {
            plugin.getAssignment("Chaeldar");
        });
        addOption("Nieve - level 85", (childId, slot) -> {
            plugin.getAssignment("Nieve");
        });
        addOption("Duradel - level 100", (childId, slot) -> {
            plugin.getAssignment("Duradel");
        });
        addOption("Krystilia - wilderness", (childId, slot) -> {
            player.getGameEncoder().sendMessage("Please speak to krystilia for a wilderness task.");
        });
        open(player);
    }
}
