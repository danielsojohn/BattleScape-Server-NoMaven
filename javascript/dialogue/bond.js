var entries = new ArrayList();
var title = "";
var lines = new ArrayList();
var actions = new ArrayList();

title = "Select an Option";
lines.add("+14 Days Premium Membership");
actions.add("close|script");
lines.add("Nevermind");
actions.add("close");
var obj0 = new DialogueEntry();
entries.add(obj0);
obj0.setSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Select an Option";
lines.add("Open Bond Pouch");
actions.add("close|script");
lines.add("Information");
actions.add("close|script");
lines.add("Donator Options");
actions.add("close|dialogue=bond,2");
var obj1 = new DialogueEntry();
entries.add(obj1);
obj1.setLargeSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

title = "Select an Option";
lines.add("Shop");
actions.add("close|script");
lines.add("Default Skin");
actions.add("close|script");
lines.add("Red Skin (Donator)");
actions.add("close|script");
lines.add("Yellow Skin (Super Donator)");
actions.add("close|script");
lines.add("Purple Skin (Extreme Donator)");
actions.add("close|script");
lines.add("Green Skin (Legendary Donator)");
actions.add("close|script");
var obj2 = new DialogueEntry();
entries.add(obj2);
obj2.setLargeSelection(title, Utils.toStringArray(lines, true), Utils.toStringArray(actions, true));

instance = new DialogueScript() {
    execute: function(player, index, childId, slot) {
        if (player.isLocked()) {
            return;
        }
        if (index == 0) {
            if (slot == 0) {
                if (Main.isSpawn()) {
                    return;
                }
                if ( player.getInventory().getCount(ItemId._14_DAYS_PREMIUM_MEMBERSHIP_32303) == 0) {
                    player.getGameEncoder().sendMessage("You need a bond to do this.");
                    return;
                }
                RequestServer.getInstance().addSqlUpdate("INSERT INTO goldmember_update (userid) VALUES("
                        + player.getId() + ")");
                player.setPremiumMember(true);
                player.getGameEncoder().sendMessage("<col=ff0000>14 days of membership have been added to your account.");
                player.getInventory().deleteItem(ItemId._14_DAYS_PREMIUM_MEMBERSHIP_32303, 1);
                player.setPremiumMemberDays(player.isPremiumMemberDays() + 14);
                RequestManager.addPlayerLog(player, "bond", player.getLogName()
                        + " received Premium Membership from a bond.");
                player.getFamiliar().rollPet(ItemId.CHOMPY_CHICK, 1);
            }
        } else if (index == 1) {
            if (slot == 0) {
                player.getBonds().sendPouch();
            } else if (slot == 1) {
                Guide.openEntry(player, "main", "bonds");
            }
        } else if (index == 2) {
            if (slot == 0) {
                if (!player.isUsergroup(SqlUserRank.DONATOR)) {
                    player.getGameEncoder().sendMessage("Only red bond donators and above can use this.");
                    return;
                }
                player.openShop("donator");
            } else if (slot == 1) {
                player.getAppearance().setColor(4, 1);
            } else if (slot == 2) {
                if (!player.isUsergroup(SqlUserRank.DONATOR)) {
                    player.getGameEncoder().sendMessage("Only red donators or above can use this.");
                    return;
                }
                player.getAppearance().setColor(4, 16);
            } else if (slot == 3) {
                if (!player.isUsergroup(SqlUserRank.SUPER_DONATOR)) {
                    player.getGameEncoder().sendMessage("Only green donators and above can use this.");
                    return;
                }
                player.getAppearance().setColor(4, 17);
            } else if (slot == 4) {
                if (!player.isUsergroup(SqlUserRank.EXTREME_DONATOR)) {
                    player.getGameEncoder().sendMessage("Only blue donators donators and above can use this.");
                    return;
                }
                player.getAppearance().setColor(4, 18);
            } else if (slot == 5) {
                if (!player.isUsergroup(SqlUserRank.LEGENDARY_DONATOR)) {
                    player.getGameEncoder().sendMessage("Only yellow donators and above can use this.");
                    return;
                }
                player.getAppearance().setColor(4, 19);
            }
        }
    },

    getDialogueEntries: function() {
        return entries;
    }
}
