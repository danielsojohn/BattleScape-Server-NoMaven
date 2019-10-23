package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Slagilith92_6385Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.SLAGILITH_92_6385);
        combat.hitpoints(NpcCombatHitpoints.total(60));
        combat.stats(NpcCombatStats.builder().attackLevel(60).defenceLevel(75).bonus(CombatBonus.MELEE_ATTACK, 10)
                .bonus(CombatBonus.DEFENCE_STAB, 50).bonus(CombatBonus.DEFENCE_SLASH, 50)
                .bonus(CombatBonus.DEFENCE_CRUSH, 5).bonus(CombatBonus.DEFENCE_MAGIC, 5)
                .bonus(CombatBonus.DEFENCE_RANGED, 50).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.deathAnimation(1752).blockAnimation(1751);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MELEE_CRUSH);
        style.damage(NpcCombatDamage.maximum(13));
        style.animation(1750).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
