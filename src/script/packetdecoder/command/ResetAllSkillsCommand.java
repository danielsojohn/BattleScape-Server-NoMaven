package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.Skills;
import lombok.var;

public class ResetAllSkillsCommand implements Command {
    @Override
    public boolean canUse(Player player) {
        return Main.isLocal();
    }

    @Override
    public void execute(Player player, String message) {
        for (var i = 0; i < Skills.SKILL_COUNT; i++) {
            player.getSkills().setXP(i, 1);
            if (i == Skills.HITPOINTS) {
                player.getSkills().setXP(i, Skills.XP_TABLE[10]);
            }
            player.getGameEncoder().sendSkillLevel(i);
        }
        player.restore();
    }
}
