package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.setting.SqlUserRank;

public class BroadcastCommand implements Command {
    @Override
    public String getExample() {
        return "message";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_MOD || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER)
                || player.getRights() == Player.RIGHTS_ADMIN || Main.eventPriviledges(player);
    }

    @Override
    public void execute(Player player, String message) {
        player.getWorld().sendBroadcast(player.getMessaging().getIconImage() + player.getUsername() + ": " + message);
    }
}
