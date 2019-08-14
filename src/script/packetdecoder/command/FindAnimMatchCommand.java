package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.io.cache.AnimationDef;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class FindAnimMatchCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String command) {
        var id = Integer.parseInt(command);
        AnimationDef.findMatches(id);
    }
}
