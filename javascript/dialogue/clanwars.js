var entries = new ArrayList();
var title = "";
var lines = new ArrayList();
var actions = new ArrayList();

title = "Select an Option";
lines.add("Join your clan's battle.");
actions.add("close|script");
lines.add("Observe your clan's battle.");
actions.add("close|script");
lines.add("Cancel.");
actions.add("close");
var obj0 = new DialogueEntry();
entries.add(obj0);
obj0.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Select an Option";
lines.add("Leave the Clan War.");
actions.add("close|script");
lines.add("Nevermind.");
actions.add("close");
var obj1 = new DialogueEntry();
entries.add(obj1);
obj1.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Select an Option";
lines.add("Competitive.");
actions.add("close|script");
lines.add("Casual.");
actions.add("close|script");
lines.add("Cancel.");
actions.add("close");
var obj2 = new DialogueEntry();
entries.add(obj2);
obj2.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Item Name: Value";
lines.add("Give to Place #1");
actions.add("script");
lines.add("Give to Place #2");
actions.add("script");
lines.add("Give to Place #3");
actions.add("script");
lines.add("Give to Place #4");
actions.add("script");
var obj3 = new DialogueEntry();
entries.add(obj3);
obj3.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Choose a Mode";
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
actions.add("close|script");
var obj4 = new DialogueEntry();
entries.add(obj4);
obj4.setLargeSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Choose an Option";
lines.add("Default Equipment");
actions.add("close|script");
lines.add("Default Inventory");
actions.add("close|script");
lines.add("Clear Equipment");
actions.add("close|script");
lines.add("Clear Inventory");
actions.add("close|script");
var obj5 = new DialogueEntry();
entries.add(obj5);
obj5.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Choose an Option";
lines.add("View Prizes");
actions.add("close|script");
lines.add("Start Custom Tournament");
actions.add("close|script");
lines.add("Start Custom Tournament - Coin Prize");
actions.add("close|script");
lines.add("Start Custom Tournament - OSGP Prize");
actions.add("close|script");
var obj6 = new DialogueEntry();
entries.add(obj6);
obj6.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

instance = new DialogueScript() {
    execute: function(player, index, childId, slot) {
        if (player.isLocked()) {
            return;
        }
        if (index == 0) {
            if (slot == 0) {
                player.getClanWars().openJoin();
            } else if (slot == 1) {
                player.getClanWars().openView();
            }
        } else if (index == 1) {
            if (!player.getController().getVariableBool("clan_wars")) {
                return;
            }
            if (slot == 0) {
                player.getController().stop();
            }
        } else if (index == 2) {
            if (slot == 0) {
                player.getClanWars().openJoinTournament();
            } else if (slot == 1) {
                player.getGameEncoder().sendMessage("Coming soon.");
            }
        } else if (index == 3) {
            var itemId = player.getAttributeInt("clan_wars_coffer_item_id");
            player.getWidgetManager().removeInteractiveWidgets();
            if (ClanWarsTournament.donateItem(player, itemId, slot)) {
                player.openDialogue("clanwars", 4);
                Dialogue.setText(player, null, ClanWarsTournament.getWarModeNames());
            }
        } else if (index == 4) {
            ClanWarsTournament.donatedSelectWarMode(player, slot);
        } else if (index == 5) {
        } else if (index == 6) {
            if (slot == 0) {
                ClanWarsTournament.viewDonatedItems(player);
            } else if (slot == 1) {
                if (ClanWarsTournament.startCustom(player, 0)) {
                    player.openDialogue("clanwars", 4);
                    Dialogue.setText(player, null, ClanWarsTournament.getWarModeNames());
                }
            } else if (slot == 2) {
                player.getGameEncoder().sendEnterAmount("Prize (Ex. 8M entered: 1st: 8M, 2nd: 4M, 3rd: 2M, 4th: 1M):",
                        new ValueEnteredEvent.IntegerEvent() {
                    execute: function(value) {
                        if (ClanWarsTournament.startCustom(player, value)) {
                            player.openDialogue("clanwars", 4);
                            Dialogue.setText(player, null, ClanWarsTournament.getWarModeNames());
                        }
                    }
                });
            } else if (slot == 3) {
                player.getGameEncoder().sendEnterAmount("First place OSGP prize:",
                        new ValueEnteredEvent.IntegerEvent() {
                    execute: function(value) {
                        if (ClanWarsTournament.startCustom(player, 0)) {
                            ClanWarsTournament.setOSCoinsPrize(value);
                            player.openDialogue("clanwars", 4);
                            Dialogue.setText(player, null, ClanWarsTournament.getWarModeNames());
                        }
                    }
                });
            }
        }
    },

    getDialogueEntries: function() {
        return entries;
    }
}
