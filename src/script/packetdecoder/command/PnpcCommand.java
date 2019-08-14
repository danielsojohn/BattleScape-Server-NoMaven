package script.packetdecoder.command;

import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.npc.NpcDef;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class PnpcCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN;
    }

    public void execute(Player player, String message) {
        var id = Integer.parseInt(message);
        player.getAppearance().setNpcId(id);
        if (id != -1 && NpcDef.getNpcDef(id) != null) {
            var animations = NpcDef.getNpcDef(id).getStanceAnimations();
            player.getGameEncoder().sendMessage("Anim: " + animations[0]);
        }
    }
}
