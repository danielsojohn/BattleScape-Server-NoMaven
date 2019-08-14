package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class ZombifiedSpawn64Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ZOMBIFIED_SPAWN_64);
        combat.hitpoints(NpcCombatHitpoints.total(38));
        combat.stats(NpcCombatStats.builder().attackLevel(82).defenceLevel(6).bonus(CombatBonus.ATTACK_STAB, 1).bonus(CombatBonus.ATTACK_SLASH, 1).bonus(CombatBonus.ATTACK_CRUSH, 1).bonus(CombatBonus.ATTACK_MAGIC, 1).bonus(CombatBonus.ATTACK_RANGED, 1).bonus(CombatBonus.MELEE_DEFENCE, 3).bonus(CombatBonus.DEFENCE_MAGIC, -100).bonus(CombatBonus.DEFENCE_RANGED, 3).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().retaliationDisabled(true).build());
        combat.combatScript("vorkathspawn").type(NpcCombatType.UNDEAD).deathAnimation(7891);


        return Arrays.asList(combat.build());
    }
}
