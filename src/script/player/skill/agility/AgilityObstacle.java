package script.player.skill.agility;

import java.util.ArrayDeque;
import java.util.Deque;
import com.palidino.osrs.model.map.MapObject;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.Skills;
import com.palidino.util.PEvent;
import lombok.var;

public class AgilityObstacle extends PEvent {
    private Player player;
    private int level;
    private int experience;
    private Deque<AgilityEvent> events = new ArrayDeque<>();

    public AgilityObstacle(Player player, int level, int experience) {
        this.player = player;
        this.level = level;
        this.experience = experience;
    }

    @Override
    public void execute() {
        if (getExecutions() == 0) {
            if (player.getSkills().getLevel(Skills.AGILITY) < level) {
                player.getGameEncoder().sendMessage("You need an Agility level of " + level + " to do this.");
                stop();
                return;
            }
        }
        if (events.isEmpty()) {
            stop();
            return;
        }
        var event = events.peek();
        if (event.getDelay() > 0) {
            event.setDelay(event.getDelay() - 1);
            return;
        }
        if (!event.complete(player)) {
            return;
        }
        events.remove();
    }

    @Override
    public void stopHook() {
        player.unlock();
        if (events.isEmpty() && experience > 0) {
            player.getSkills().addXp(Skills.AGILITY, experience);
        }
    }

    public AgilityObstacle add(AgilityEvent event) {
        return add(0, event);
    }

    public AgilityObstacle add(int delay, AgilityEvent event) {
        event.setDelay(delay);
        events.add(event);
        return this;
    }

    public void start(MapObject mapObject) {
        player.lock();
        if (mapObject == null || mapObject.getType() != 0) {
            execute();
        }
        player.getWorld().addEvent(this);
    }
}
