package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Vanguard7526Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VANGUARD_7526);
        combat.hitpoints(NpcCombatHitpoints.total(133));
        combat.stats(
                NpcCombatStats.builder().attackLevel(150).magicLevel(150).rangedLevel(150).defenceLevel(210).build());
        combat.aggression(NpcCombatAggression.builder().range(6).always(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).bind(true).build());
        combat.combatScript("VanguardCS").deathAnimation(7432);


        return Arrays.asList(combat.build());
    }
}
