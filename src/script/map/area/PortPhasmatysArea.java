package script.map.area;

import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.map.Area;
import com.palidino.osrs.model.map.MapObject;
import lombok.var;

public class PortPhasmatysArea extends Area {
    public PortPhasmatysArea() {
        super(14647, 14646);
    }

    @Override
    public boolean mapObjectOptionHook(int index, MapObject mapObject) {
        var player = getPlayer();
        switch (mapObject.getId()) {
        case 16648:
            if (!player.getInventory().hasItem(ItemId.ECTOPHIAL_4252)) {
                player.getGameEncoder().sendMessage("Nothing interesting happens...");
                return true;
            }
            player.getInventory().deleteItem(ItemId.ECTOPHIAL_4252);
            player.getInventory().addOrDropItem(ItemId.ECTOPHIAL);
            player.setAnimation(1651);
            return true;
        }
        return false;
    }
}
