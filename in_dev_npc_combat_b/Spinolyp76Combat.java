package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.player.Skills;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Spinolyp76Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.SPINOLYP_76);
        combat.noclip(true);
        combat.hitpoints(NpcCombatHitpoints.total(100));
        combat.stats(NpcCombatStats.builder().attackLevel(10).rangedLevel(2).defenceLevel(10).bonus(CombatBonus.MELEE_DEFENCE, 100).bonus(CombatBonus.DEFENCE_MAGIC, 50).bonus(CombatBonus.DEFENCE_RANGED, 50).build());
        combat.aggression(NpcCombatAggression.builder().range(8).build());
        combat.focus(NpcCombatFocus.builder().disableFollowingOpponent(true).build());
        combat.combatScript("SpinolypCS").deathAnimation(2866).blockAnimation(2869);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(10));
        style.animation(2868).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.RANGED).build());
        style.damage(NpcCombatDamage.maximum(10));
        style.animation(2868).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().statDrain(Skills.PRAYER, 1).build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
