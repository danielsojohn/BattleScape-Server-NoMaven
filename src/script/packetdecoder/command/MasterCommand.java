package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.Skills;
import lombok.var;

public class MasterCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return Main.isBeta() && !Main.isBetaSaving() || player.getRights() == Player.RIGHTS_ADMIN;
    }

    @Override
    public void execute(Player player, String message) {
        for (var i = 0; i < Skills.SKILL_COUNT; i++) {
            player.getSkills().setXP(i, Skills.XP_TABLE[99]);
            player.getGameEncoder().sendSkillLevel(i);
            player.restore();
        }
    }
}
