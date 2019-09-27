package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.setting.SqlUserRank;
import lombok.var;

public class HpCommand implements Command {
    @Override
    public String getExample() {
        return "amount";
    }

    @Override
    public boolean canUse(Player player) {
        return player.getRights() == Player.RIGHTS_ADMIN || player.isUsergroup(SqlUserRank.COMMUNITY_MANAGER)
                || Main.eventPriviledges(player);
    }

    @Override
    public void execute(Player player, String message) {
        var amount = Integer.parseInt(message);
        player.getMovement().setEnergy(amount);
        player.setHitpoints(amount);
        player.getSkills().setLevel(3, amount);
        player.getGameEncoder().sendSkillLevel(3);
        player.getGameEncoder().sendMessage("You set your hitpoints to " + amount + ".");
    }
}
