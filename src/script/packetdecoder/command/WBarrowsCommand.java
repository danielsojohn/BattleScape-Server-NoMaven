package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.dialogue.old.DialogueEntry;
import com.palidino.osrs.model.dialogue.old.DialogueOld;
import com.palidino.osrs.model.dialogue.old.DialogueScript;
import com.palidino.osrs.model.player.Player;

public class WBarrowsCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return !player.getController().inWilderness() && !player.getController().inPvPWorld();
    }

    @Override
    public void execute(Player player, String message) {
        if (player.getController().canTeleport(true)) {
            DialogueEntry entry = new DialogueEntry();
            entry.setSelection("Are you sure you want to teleport to the wilderness?",
                    "Yes, teleport me to the wilderness!", "No!");
            DialogueScript script = (p, index, childId, slot) -> {
                if (slot == 0) {
                    player.getMagic().standardTeleport(2967, 3765, 0);
                    player.getGameEncoder().sendMessage("You teleport to Wilderness barrows..");
                } else {
                    return;
                }
            };
            DialogueOld.open(player, entry, script);
        } else {
            return;
        }
    }
}
