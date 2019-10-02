package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class VerzikVitur1040_8370Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VERZIK_VITUR_1040_8370);
        combat.spawn(NpcCombatSpawn.builder().respawnId(NpcId.VERZIK_VITUR_1040_8371).build());
        combat.hitpoints(NpcCombatHitpoints.builder().total(2000).bar(HitpointsBar.GREEN_RED_100).build());
        combat.stats(NpcCombatStats.builder().attackLevel(400).magicLevel(400).rangedLevel(400).defenceLevel(20).bonus(CombatBonus.ATTACK_MAGIC, 80).bonus(CombatBonus.ATTACK_RANGED, 80).bonus(CombatBonus.MELEE_DEFENCE, 20).bonus(CombatBonus.DEFENCE_MAGIC, 20).bonus(CombatBonus.DEFENCE_RANGED, 20).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("VerzikViturPhase1Combat").blockAnimation(8110);


        return Arrays.asList(combat.build());
    }
}
