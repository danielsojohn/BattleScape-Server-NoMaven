package script.world.pvptournament.dialogue;

import com.palidino.osrs.io.ValueEnteredEvent;
import com.palidino.osrs.model.dialogue.SelectionDialogue;
import com.palidino.osrs.model.player.Player;
import lombok.var;
import script.world.pvptournament.PvpTournament;
import script.world.pvptournament.prize.DefaultPrize;
import script.world.pvptournament.prize.OsrsPrize;
import script.world.pvptournament.state.IdleState;

public class AdminCofferDialogue extends SelectionDialogue {
    public AdminCofferDialogue(Player player) {
        var tournament = player.getWorld().getWorldEvent(PvpTournament.class);
        addOption("View prizes", (childId, slot) -> {
            tournament.viewPrizes(player);
        });
        addOption("View shop", (childId, slot) -> {
            player.openShop("pvp_tournament");
        });
        addOption("Select mode", (childId, slot) -> {
            new ChooseModeDialogue(player);
        });
        addOption("Set OSRS prize", (childId, slot) -> {
            player.getGameEncoder().sendEnterAmount("OSRS prize:", new ValueEnteredEvent.IntegerEvent() {
                @Override
                public void execute(int value) {
                    if (!(tournament.getState() instanceof IdleState)
                            || !(tournament.getPrize() instanceof DefaultPrize)
                                    && !(tournament.getPrize() instanceof OsrsPrize)) {
                        player.getGameEncoder().sendMessage("A tournament has already been configured.");
                        return;
                    }
                    tournament.setPrize(new OsrsPrize(value));
                    new ChooseModeDialogue(player);
                }
            });
        });
        open(player);
    }
}
