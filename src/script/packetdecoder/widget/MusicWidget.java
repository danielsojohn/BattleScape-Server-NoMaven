package script.packetdecoder.widget;

import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;

public class MusicWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.MUSIC
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {}
}
