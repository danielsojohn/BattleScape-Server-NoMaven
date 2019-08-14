package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.NpcCombatSlayer;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class AlchemicalHydra426_8616Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ALCHEMICAL_HYDRA_426_8616);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(42).build());
        combat.stats(NpcCombatStats.builder().attackLevel(100).magicLevel(260).rangedLevel(260).defenceLevel(100).build());
        combat.slayer(NpcCombatSlayer.builder().level(95).build());
        combat.combatScript("alchemicalhydra");


        return Arrays.asList(combat.build());
    }
}
