package script.packetdecoder.widget;

import com.palidino.osrs.io.ValueEnteredEvent;
import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.WidgetChild;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.world.JavaCord;
import com.palidino.setting.DiscordChannel;
import com.palidino.util.Logger;

public class LogoutWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.LOGOUT, WidgetId.WORLD_SELECT
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        if (player.isLocked()) {
            return;
        }
        if (widgetId == WidgetId.LOGOUT) {
            switch (childId) {
            case 3:
                player.getWidgetManager().sendWidget(WidgetChild.ViewportContainer.LOGOUT, WidgetId.WORLD_SELECT);
                player.getGameEncoder().sendWidgetSettings(WidgetId.WORLD_SELECT, 18, 0, 20, 2);
                break;
            case 8:
                player.getController().logoutWidgetHook();
                if (!player.canLogout() || player.carryingItem(ItemId.BLOODY_KEY)
                        || player.carryingItem(ItemId.BLOODIER_KEY)) {
                    player.getGameEncoder().sendMessage("You can't logout right now.");
                    player.getCombat().getTzHaar().pause();
                    return;
                }
                if (player.isNewAccount()) {
                    player.getGameEncoder().sendEnterString("Will you return? If not, why?",
                            new ValueEnteredEvent.StringEvent() {
                                @Override
                                public void execute(String value) {
                                    Logger.println("[NEW USER FEEDBACK; " + player.getIP() + "] " + player.getUsername()
                                            + ": " + value);
                                    JavaCord.sendMessage(DiscordChannel.FEEDBACK, "[NEW USER FEEDBACK; "
                                            + player.getIP() + "] " + player.getUsername() + ": " + value);
                                    player.getGameEncoder().sendLogout();
                                    player.setVisible(false);
                                }
                            });
                } else {
                    player.getGameEncoder().sendLogout();
                    player.setVisible(false);
                }
                break;
            }
        } else if (widgetId == WidgetId.WORLD_SELECT) {
            switch (childId) {
            case 3:
                player.getWidgetManager().sendWidget(WidgetChild.ViewportContainer.LOGOUT, WidgetId.LOGOUT);
                player.getWidgetManager().sendLogoutText();
                break;
            case 18:
                player.getController().logoutWidgetHook();
                if (!player.canLogout() || player.carryingItem(ItemId.BLOODY_KEY)
                        || player.carryingItem(ItemId.BLOODIER_KEY)) {
                    player.getGameEncoder().sendMessage("You can't logout right now.");
                    player.getCombat().getTzHaar().pause();
                    return;
                }
                if (slot == 1) {
                    player.putAttribute("swap_world_ip", "world1.battle-scape.com");
                    player.putAttribute("swap_world_id", 1);
                    player.putAttribute("swap_world_mask", 1 + 33554432);
                } else if (slot == 2) {
                    player.putAttribute("swap_world_ip", "s2-world1.battle-scape.com");
                    player.putAttribute("swap_world_id", 2);
                    player.putAttribute("swap_world_mask", 1 + 33554432);
                } else if (slot == 3) {
                    player.putAttribute("swap_world_ip", "s2-world2.battle-scape.com");
                    player.putAttribute("swap_world_id", 3);
                    player.putAttribute("swap_world_mask", 1 + 33554432);
                }
                player.setVisible(false);
                break;
            case 23:
                player.getController().logoutWidgetHook();
                if (!player.canLogout() || player.carryingItem(ItemId.BLOODY_KEY)
                        || player.carryingItem(ItemId.BLOODIER_KEY)) {
                    player.getGameEncoder().sendMessage("You can't logout right now.");
                    player.getCombat().getTzHaar().pause();
                    return;
                }
                player.getGameEncoder().sendLogout();
                player.setVisible(false);
                break;
            }
        }
    }
}
