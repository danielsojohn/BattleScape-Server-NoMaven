package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class EnergyBallCombat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ENERGY_BALL);
        combat.hitpoints(NpcCombatHitpoints.total(20));
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("derwenspawn");


        return Arrays.asList(combat.build());
    }
}
