var entries = new ArrayList();
var title = "";
var lines = new ArrayList();
var actions = new ArrayList();

title = "Select an Option";
lines.add("Store-1");
actions.add("script|close");
lines.add("Store-5");
actions.add("script|close");
lines.add("Store-All");
actions.add("script|close");
lines.add("Store-X");
actions.add("script|close");
var obj0 = new DialogueEntry();
entries.add(obj0);
obj0.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Select an Option";
lines.add("Always Ask");
actions.add("close|script");
lines.add("Always Store-1");
actions.add("close|script");
lines.add("Always Store-5");
actions.add("close|script");
lines.add("Always Store-All");
actions.add("close|script");
lines.add("Always Store-X");
actions.add("close|script");
var obj1 = new DialogueEntry();
entries.add(obj1);
obj1.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

instance = new DialogueScript() {
    execute: function(player, index, childId, slot) {
        if (player.isLocked()) {
            return;
        }
        if (index == 0) {
            var depositingSlot = player.getAttributeInt("looting_bag_item_slot");
            var depositingId = player.getInventory().getId(depositingSlot);
            if (!player.getController().inWilderness() && !player.getController().inPvPWorld()) {
                player.getGameEncoder().sendMessage("You can't put items in the bag unless you're in the Wilderness.");
                return;
            } else if (ItemDef.getUntradable(depositingId)) {
                player.getGameEncoder().sendMessage("You can only put tradeable items in the bag.");
                return;
            } else if (depositingId == ItemId.BLOODY_KEY || depositingId == ItemId.BLOODIER_KEY) {
                player.getGameEncoder().sendMessage("This key can't be stored in the bag.");
                return;
            } else if (depositingId == -1) {
                player.getGameEncoder().sendMessage("Invalid item selected.");
                return;
            }
            player.getWidgetManager().initLootingBag();
            var amount = 0;
            if (slot == 0) {
                amount = 1;
            } else if (slot == 1) {
                amount = 5;
            } else if (slot == 2) {
                amount = ItemDef.getStackOrNote(depositingId) ? player.getInventory().getAmount(depositingSlot)
                        : Item.MAX_AMOUNT;
            } else if (slot == 4) {
                amount = player.getInventory().getAmount(depositingSlot);
            }
            var valueEntered = new ValueEnteredEvent.IntegerEvent() {
                execute: function(value) {
                    value = Math.min(value, player.getInventory().getCount(depositingId));
                    value = player.getWidgetManager().getLootingBag().canAddAmount(depositingId, value);
                    if (value == 0) {
                        player.getWidgetManager().getLootingBag().notEnoughSpace();
                        return;
                    }
                    player.getInventory().deleteItem(depositingId, value, depositingSlot);
                    player.getWidgetManager().getLootingBag().addItem(depositingId, value);
                }
            }
            if (slot == 3) {
               player.getGameEncoder().sendEnterAmount(valueEntered);
            } else {
                valueEntered.execute(amount);
            }
        } else if (index == 1) {
            player.getWidgetManager().setLootingBagStoreType(slot);
        }
    },

    getDialogueEntries: function() {
        return entries;
    }
}
