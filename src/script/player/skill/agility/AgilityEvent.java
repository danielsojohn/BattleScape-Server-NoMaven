package script.player.skill.agility;

import com.palidino.osrs.model.player.Player;
import lombok.Getter;
import lombok.Setter;

public abstract class AgilityEvent {
    @Getter
    @Setter
    private int delay;

    abstract boolean complete(Player player);
}
