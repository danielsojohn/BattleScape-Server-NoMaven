package script.packetdecoder.widget;

import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;

public class WorldMapWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.WORLD_MAP
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        switch (childId) {
        case 3:
        case 37:
            player.getWidgetManager().removeWorldMap();
            break;
        }
    }
}
