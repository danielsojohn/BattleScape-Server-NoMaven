

package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.rs.setting.SqlUserRank;
import lombok.var;

public class SkinCommand implements Command {
    @Override
    public String getExample() {
        return "id";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER)
                || Main.eventPriviledges(player);
    }

    @Override
    public void execute(Player player, String message) {
        var id = Integer.parseInt(message);
        player.getAppearance().setColor(4, id);
    }
}
