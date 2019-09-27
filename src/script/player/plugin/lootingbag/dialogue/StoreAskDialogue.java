package script.player.plugin.lootingbag.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import script.player.plugin.lootingbag.LootingBagPlugin;
import script.player.plugin.lootingbag.StoreType;

public class StoreAskDialogue extends SelectionDialogue {
    public StoreAskDialogue(Player player, LootingBagPlugin plugin) {
        addOption("Store-1", (childId, slot) -> {
            plugin.storeItemFromInventory(player.getAttributeInt("looting_bag_item_slot"), StoreType.STORE_1);
        });
        addOption("Store-5", (childId, slot) -> {
            plugin.storeItemFromInventory(player.getAttributeInt("looting_bag_item_slot"), StoreType.STORE_5);
        });
        addOption("Store-All", (childId, slot) -> {
            plugin.storeItemFromInventory(player.getAttributeInt("looting_bag_item_slot"), StoreType.STORE_ALL);
        });
        addOption("Store-X", (childId, slot) -> {
            plugin.storeItemFromInventory(player.getAttributeInt("looting_bag_item_slot"), StoreType.STORE_X);
        });
        open(player);
    }
}
