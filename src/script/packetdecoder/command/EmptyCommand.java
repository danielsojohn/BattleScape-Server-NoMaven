package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.dialogue.old.DialogueOld;
import com.palidino.osrs.model.dialogue.old.DialogueEntry;
import com.palidino.osrs.model.dialogue.old.DialogueScript;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class EmptyCommand implements Command {

    @Override
    public String getExample() {
        return "- Empties your inventory.";
    }

    @Override
    public void execute(Player player, String message) {
        DialogueEntry entry = new DialogueEntry();
        entry.setSelection("Are you sure you want to empty your inventory?", "Yes, empty my inventory!", "No!");
        DialogueScript script = (p, index, childId, slot) -> {
            for (var i = 0; i < player.getInventory().size(); i++) {
                var id = player.getInventory().getId(i);
                if (slot == 0) {
                    player.getInventory().deleteItem(id, Item.MAX_AMOUNT);
                    player.getGameEncoder().sendMessage("You empty your inventory..");
                } else {
                    return;
                }
            }
        };
        DialogueOld.open(player, entry, script);
    }
}
