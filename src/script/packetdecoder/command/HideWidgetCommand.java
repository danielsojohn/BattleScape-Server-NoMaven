package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class HideWidgetCommand implements Command {
    @Override
    public String getExample() {
        return "id child_id true/false";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        var values = message.split(" ");
        var id = Integer.parseInt(values[0]);
        var childId = Integer.parseInt(values[1]);
        var hidden = Boolean.parseBoolean(values[2]);
        player.getGameEncoder().sendHideWidget(id, childId, hidden);
    }
}
