package script.packetdecoder.widget;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.dialogue.old.DialogueOld;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.Utils;

public class WelcomeWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.WELCOME
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        switch (childId) {
        case 81:
            player.getWidgetManager().sendGameViewport();
            if (player.isNewAccount()) {
                player.getWidgetManager().sendInteractiveOverlay(WidgetId.CHARACTER_DESIGN);
            } else if (!player.isPremiumMember() && Utils.randomE(5) == 0 && !Main.isSpawn()) {
                DialogueOld.openChatboxMessage(player,
                        "<col=ff0000>Bonds</col> can be purchased and used on: trading, membership, mystery boxes, blood money, and other items!");
            }
            break;
        }
    }
}
