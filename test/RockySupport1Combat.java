package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class RockySupport1Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ROCKY_SUPPORT_1);
        combat.hitpoints(NpcCombatHitpoints.total(175));
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("InfernoSupportCS").deathAnimation(7561);


        return Arrays.asList(combat.build());
    }
}
