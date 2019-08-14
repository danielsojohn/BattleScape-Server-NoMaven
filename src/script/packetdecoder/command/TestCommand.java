package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.io.cache.WidgetSetting;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.world.LastManStandingEvent;
import lombok.var;

/*
 * Freely edit this to quickly test features. For commands that need more than a single use/test,
 * consider a proper command for it.
 */
@SuppressWarnings("all")
public class TestCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        var npc = new Npc(player.getController(), NpcId.VERZIK_VITUR_1040_8370, new Tile(3166, 4323));
    }
}
