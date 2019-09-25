package script.world.pvptournament.state;

import com.palidino.osrs.Main;
import com.palidino.osrs.world.World;
import com.palidino.util.Time;
import lombok.var;
import script.world.pvptournament.PvpTournament;
import script.world.pvptournament.prize.DefaultPrize;

public class IdleState implements State {
    private PvpTournament tournament;
    private boolean announcedStartingSoon;

    public IdleState(PvpTournament tournament) {
        this.tournament = tournament;
        tournament.setMode(null);
        tournament.setPrize(new DefaultPrize(false));
        tournament.getPlayers().clear();
        tournament.setController(null);
        tournament.setViewerList("");
    }

    @Override
    public String getMessage() {
        if (PvpTournament.TIME.length == 0 || Main.getWorld().getId() != World.SPECIAL_FEATURES_WORLD_ID) {
            return "Unavailable";
        }
        var currentHour = Time.getHour24();
        var currentMinute = Time.getMinute();
        var dayMinute = (int) Time.hourToMin(currentHour) + currentMinute;
        var nextTime = PvpTournament.getNextTime();
        var remainingMinutes = Time.getRemainingMinutes(dayMinute, nextTime[0] * 60 + nextTime[1]);
        return Time.ticksToLongDuration(Time.minToTick(remainingMinutes));
    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public void execute() {
        var nextTime = PvpTournament.getNextTime();
        if (nextTime == null) {
            return;
        }
        var isCustom = !(tournament.getPrize() instanceof DefaultPrize);
        var isCustomReady = isCustom && tournament.getMode() != null;
        if (!isCustom && Main.getWorld().getId() != World.SPECIAL_FEATURES_WORLD_ID) {
            return;
        }
        var currentHour = Time.getHour24();
        var currentMinute = Time.getMinute();
        var dayMinute = (int) Time.hourToMin(currentHour) + currentMinute;
        var remainingMinutes = Time.getRemainingMinutes(dayMinute, nextTime[0] * 60 + nextTime[1]);
        if (!isCustom && !announcedStartingSoon) {
            if (remainingMinutes == 4) {
                announcedStartingSoon = true;
                Main.getWorld().sendNotice("The tournament lobby will open in 4 minutes!");
            }
        }
        if (!isCustom && remainingMinutes != 0 || isCustom && !isCustomReady) {
            return;
        }
        tournament.setState(new LobbyState(tournament));
    }
}
