package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatSlayer;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Dawn228_7885Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.DAWN_228_7885);
        combat.spawn(NpcCombatSpawn.builder().lock(16).direction(3).animation(7766).build());
        combat.hitpoints(NpcCombatHitpoints.total(450));
        combat.stats(NpcCombatStats.builder().attackLevel(140).magicLevel(100).rangedLevel(140).defenceLevel(100).bonus(CombatBonus.DEFENCE_MAGIC, 80).build());
        combat.slayer(NpcCombatSlayer.builder().level(75).build());
        combat.aggression(NpcCombatAggression.builder().range(20).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.combatScript("GrotesqueGuardianCS").deathAnimation(7777);


        return Arrays.asList(combat.build());
    }
}
