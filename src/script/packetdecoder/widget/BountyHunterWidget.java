package script.packetdecoder.widget;

import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;

public class BountyHunterWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.WILDERNESS
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        if (player.isLocked()) {
            return;
        }
        switch (childId) {
        case 57:
            player.openDialogue("bountyhunter", 0);
            break;
        }
    }
}
