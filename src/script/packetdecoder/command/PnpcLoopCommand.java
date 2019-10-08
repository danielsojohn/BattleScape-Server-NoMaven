package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.io.cache.WidgetSetting;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.event.Event;
import lombok.var;

@SuppressWarnings("all")
public class PnpcLoopCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        var fromId = Integer.parseInt(message);
        var event = new Event(1) {
            int id = fromId;
            boolean reset = false;

            @Override
            public void execute() {
                if (reset) {
                    reset = false;
                    player.getAppearance().setNpcId(-1);
                    setTick(0);
                } else {
                    reset = true;
                    player.getGameEncoder().sendMessage("Npc id: " + id);
                    player.getAppearance().setNpcId(id++);
                    setTick(1);
                }
            }
        };
        player.setAction(event);
    }
}
