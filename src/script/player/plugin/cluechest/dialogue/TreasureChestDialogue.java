package script.player.plugin.cluechest.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.item.clue.ClueChestType;
import com.palidino.osrs.model.player.Player;
import script.player.plugin.cluechest.ClueChestPlugin;

public class TreasureChestDialogue extends SelectionDialogue {
    public TreasureChestDialogue(Player player, ClueChestPlugin plugin) {
        addOption("Easy", (childId, slot) -> {
            plugin.open(ClueChestType.EASY);
        });
        addOption("Medium", (childId, slot) -> {
            plugin.open(ClueChestType.MEDIUM);
        });
        addOption("Hard", (childId, slot) -> {
            plugin.open(ClueChestType.HARD);
        });
        addOption("Elite", (childId, slot) -> {
            plugin.open(ClueChestType.ELITE);
        });
        addOption("Master", (childId, slot) -> {
            plugin.open(ClueChestType.MASTER);
        });
        open(player);
    }
}
