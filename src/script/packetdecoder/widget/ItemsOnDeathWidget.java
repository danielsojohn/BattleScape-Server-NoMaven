package script.packetdecoder.widget;

import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;

public class ItemsOnDeathWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.ITEMS_KEPT_ON_DEATH
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {}
}
