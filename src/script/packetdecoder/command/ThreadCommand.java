package script.packetdecoder.command;

import com.palidino.osrs.Main;
import com.palidino.osrs.io.Command;
import com.palidino.osrs.model.player.Player;

public class ThreadCommand implements Command {
    @Override
    public String getExample() {
        return "- Open a thread by id.";
    }

    @Override
    public void execute(Player player, String id) {
        player.getGameEncoder().sendOpenURL(Main.getSettings().getThreadUrl() + id);
    }
}
