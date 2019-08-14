package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.player.Player;

public class TbCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return Main.isSpawn() && player.inEdgeville() && !player.getController().inPvPWorld();
    }

    @Override
    public void execute(Player player, String message) {
        player.getInventory().addItem(ItemId.LAW_RUNE, 5000);
        player.getInventory().addItem(ItemId.CHAOS_RUNE, 5000);
        player.getInventory().addItem(ItemId.DEATH_RUNE, 5000);
    }


}
