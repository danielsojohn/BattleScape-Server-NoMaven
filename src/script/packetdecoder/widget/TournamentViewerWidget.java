package script.packetdecoder.widget;

import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;

public class TournamentViewerWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.TOURNAMENT_VIEWER
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        if (player.isLocked()) {
            return;
        }
        if (player.getMovement().getTeleporting() || player.getMovement().getTeleported()
                || player.getMovement().isViewing()) {
            return;
        }
        switch (childId) {
        case 8:
            player.getClanWars().teleportViewing(-1);
            break;
        }
    }
}
