package script.world.pvptournament.dialogue;

import com.palidino.osrs.model.dialogue.LargeSelectionDialogue;
import com.palidino.osrs.model.player.Player;
import lombok.var;
import script.world.pvptournament.PvpTournament;

public class ChooseModeDialogue extends LargeSelectionDialogue {
    public ChooseModeDialogue(Player player) {
        var tournament = player.getWorld().getWorldEvent(PvpTournament.class);
        addOption("Option", (childId, slot) -> {
            tournament.selectCustomMode(player, slot);
        });
        open(player);
    }

    @Override
    public void sendWidgetTextHook(Player player) {
        sendWidgetText(player, null, PvpTournament.getModeNames());
    }
}
