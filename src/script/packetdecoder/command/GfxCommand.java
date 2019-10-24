package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.rs.setting.SqlUserRank;
import lombok.var;

public class GfxCommand implements Command {
    @Override
    public String getExample() {
        return "id (height)";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String message) {
        var messages = message.split(" ");
        var id = Integer.parseInt(messages[0]);
        var height = 0;
        if (messages.length > 1) {
            height = Integer.parseInt(messages[1]);
        }
        player.setGraphic(id, height);
    }
}
