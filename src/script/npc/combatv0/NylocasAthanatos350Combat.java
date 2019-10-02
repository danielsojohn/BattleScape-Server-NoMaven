package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class NylocasAthanatos350Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.NYLOCAS_ATHANATOS_350);
        combat.hitpoints(NpcCombatHitpoints.total(180));
        combat.stats(NpcCombatStats.builder().magicLevel(50).defenceLevel(50).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("VerzikViturPhase2Combat").deathAnimation(8078);


        return Arrays.asList(combat.build());
    }
}
