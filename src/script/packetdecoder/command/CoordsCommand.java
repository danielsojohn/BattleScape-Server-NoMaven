package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.rs.setting.SqlUserRank;

public class CoordsCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER);
    }

    @Override
    public void execute(Player player, String message) {
        player.getGameEncoder().sendMessage(
                "mapClip=" + player.getController().getMapClip(player.getX(), player.getY(), player.getHeight()));
        player.getGameEncoder().sendMessage("solidMapObject=" + player.getController().getSolidMapObject(player));
        player.getGameEncoder()
                .sendMessage("x=" + player.getX() + ", y=" + player.getY() + ", z=" + player.getHeight() + ", client-z="
                        + player.getClientHeight() + ", region-id=" + player.getRegionId() + ", instanced="
                        + player.getController().isInstanced());
    }
}
