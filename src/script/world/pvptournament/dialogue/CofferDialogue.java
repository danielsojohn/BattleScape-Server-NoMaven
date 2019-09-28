package script.world.pvptournament.dialogue;

import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import lombok.var;
import script.world.pvptournament.PvpTournament;

public class CofferDialogue extends SelectionDialogue {
    public CofferDialogue(Player player) {
        var tournament = player.getWorld().getWorldEvent(PvpTournament.class);
        addOption("View prizes", (childId, slot) -> {
            tournament.viewPrizes(player);
        });
        addOption("View shop", (childId, slot) -> {
            player.openShop("pvp_tournament");
        });
        open(player);
    }
}
