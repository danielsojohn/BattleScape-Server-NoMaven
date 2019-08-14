package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class StaffOnlyCommand implements Command {
    @Override
    public String getExample() {
        return "true/false";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        Main.getSettings().setStaffOnly(Boolean.parseBoolean(message));
        player.getGameEncoder().sendMessage("Staff only: " + message);
    }
}
