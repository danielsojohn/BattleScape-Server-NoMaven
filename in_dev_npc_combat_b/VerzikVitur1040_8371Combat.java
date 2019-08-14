package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class VerzikVitur1040_8371Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VERZIK_VITUR_1040_8371);
        combat.combatScript("VerzikViturPhase2Combat");


        return Arrays.asList(combat.build());
    }
}
