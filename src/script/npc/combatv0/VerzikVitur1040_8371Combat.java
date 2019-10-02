package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class VerzikVitur1040_8371Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VERZIK_VITUR_1040_8371);
        combat.combatScript("VerzikViturPhase2Combat");


        return Arrays.asList(combat.build());
    }
}
