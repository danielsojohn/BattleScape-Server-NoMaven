package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class PestilentBloat870Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.PESTILENT_BLOAT_870);
        combat.hitpoints(NpcCombatHitpoints.builder().total(2000).bar(HitpointsBar.GREEN_RED_100).build());
        combat.stats(NpcCombatStats.builder().attackLevel(250).magicLevel(150).rangedLevel(180).defenceLevel(100).bonus(CombatBonus.MELEE_ATTACK, 150).bonus(CombatBonus.ATTACK_RANGED, 180).bonus(CombatBonus.DEFENCE_STAB, 40).bonus(CombatBonus.DEFENCE_SLASH, 20).bonus(CombatBonus.DEFENCE_CRUSH, 40).bonus(CombatBonus.DEFENCE_MAGIC, 600).bonus(CombatBonus.DEFENCE_RANGED, 800).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("pestilentbloat");


        return Arrays.asList(combat.build());
    }
}
