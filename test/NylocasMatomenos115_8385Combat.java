package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class NylocasMatomenos115_8385Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.NYLOCAS_MATOMENOS_115_8385);
        combat.hitpoints(NpcCombatHitpoints.total(75));
        combat.stats(NpcCombatStats.builder().attackLevel(100).magicLevel(100).rangedLevel(100).defenceLevel(100).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("VerzikNylocasMatomenosCombat").deathAnimation(8097);


        return Arrays.asList(combat.build());
    }
}
