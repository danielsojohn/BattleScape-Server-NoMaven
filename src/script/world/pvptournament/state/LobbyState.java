package script.world.pvptournament.state;

import com.palidino.osrs.Main;
import com.palidino.util.Time;
import com.palidino.util.Utils;
import lombok.var;
import script.player.plugin.clanwars.ClanWarsPlugin;
import script.player.plugin.clanwars.rule.Rule;
import script.world.pvptournament.Mode;
import script.world.pvptournament.PvpTournament;
import script.world.pvptournament.prize.DefaultPrize;

public class LobbyState implements State {
    private PvpTournament tournament;
    private int countdown = PvpTournament.LOBBY_JOIN_TIME;

    public LobbyState(PvpTournament tournament) {
        this.tournament = tournament;
        var selectedMode = tournament.getMode();
        if (selectedMode == null) {
            tournament.setPrize(new DefaultPrize(Time.getHour24() == 16));
            var attempts = 0;
            while (attempts++ < 16 && (selectedMode == null || tournament.getRecentModes().contains(selectedMode))) {
                selectedMode = Utils.listRandom(Mode.MODES);
            }
            tournament.setMode(selectedMode);
            if (!tournament.getRecentModes().contains(selectedMode)) {
                tournament.getRecentModes().add(selectedMode);
            }
            if (tournament.getRecentModes().size() > 2) {
                tournament.getRecentModes().remove(0);
            }
        }
        var rules = new int[Rule.TOTAL];
        System.arraycopy(selectedMode.getRules(), 0, rules, 0, Rule.TOTAL);
        tournament.setRules(rules);
        tournament.getPlayers().clear();
    }

    @Override
    public String getMessage() {
        var timeRemaining = "";
        var minutes = (int) Time.tickToMin(countdown);
        if (minutes > 0) {
            timeRemaining = minutes + (minutes == 1 ? " minute" : " minutes");
        } else {
            timeRemaining = Time.tickToSec(countdown) + " seconds";
        }
        return "Lobby: " + timeRemaining;
    }

    @Override
    public int getTime() {
        return countdown;
    }

    @Override
    public void execute() {
        var isCustom = !(tournament.getPrize() instanceof DefaultPrize);
        if (countdown-- == PvpTournament.LOBBY_JOIN_TIME) {
            var minutes = (int) Time.tickToMin(countdown + 1);
            var minutesAsString = Integer.toString(minutes) + " minutes.";
            if (minutes <= 1) {
                minutesAsString = Integer.toString(minutes) + " minute.";
            }
            var message = "The tournament lobby is open" + (isCustom ? " for a custom match" : "") + " as "
                    + tournament.getMode().getName() + "! It will begin in " + minutesAsString;
            var prizeMessage = tournament.getPrize().getMessage();
            if (prizeMessage != null) {
                message += " " + prizeMessage;
            }
            Main.getWorld().sendBroadcast(message);
        } else if (countdown == 0) {
            var hasEnoughPlayers = isCustom || tournament.getPlayers().size() >= PvpTournament.MINIMUM_PLAYERS;
            if (hasEnoughPlayers) {
                for (var player : tournament.getPlayers()) {
                    player.getPlugin(ClanWarsPlugin.class).setCountdown(countdown);
                }
                tournament.setState(new RoundsState(tournament));
            } else {
                while (!tournament.getPlayers().isEmpty()) {
                    var player = tournament.getPlayers().get(0);
                    player.getGameEncoder().sendMessage(
                            "<col=ff0000>At least " + PvpTournament.MINIMUM_PLAYERS + " players are needed to start.");
                    player.getController().stop();
                }
                tournament.setState(new IdleState(tournament));
            }
        }
    }
}
