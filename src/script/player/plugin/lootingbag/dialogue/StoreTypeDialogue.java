package script.player.plugin.lootingbag.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import lombok.var;
import script.player.plugin.lootingbag.LootingBagPlugin;
import script.player.plugin.lootingbag.StoreType;

public class StoreTypeDialogue extends SelectionDialogue {
    public StoreTypeDialogue(Player player) {
        var plugin = player.getPlugin(LootingBagPlugin.class);
        addOption("Always Ask", (childId, slot) -> {
            plugin.setStoreType(StoreType.ASK);
        });
        addOption("Always Store-1", (childId, slot) -> {
            plugin.setStoreType(StoreType.STORE_1);
        });
        addOption("Always Store-5", (childId, slot) -> {
            plugin.setStoreType(StoreType.STORE_5);
        });
        addOption("Always Store-All", (childId, slot) -> {
            plugin.setStoreType(StoreType.STORE_ALL);
        });
        addOption("Always Store-X", (childId, slot) -> {
            plugin.setStoreType(StoreType.STORE_X);
        });
        open(player);
    }
}
