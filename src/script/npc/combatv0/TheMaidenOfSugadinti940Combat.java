package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.special.NpcCombatTargetTile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class TheMaidenOfSugadinti940Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.THE_MAIDEN_OF_SUGADINTI_940);
        combat.hitpoints(NpcCombatHitpoints.builder().total(3500).bar(HitpointsBar.GREEN_RED_100).build());
        combat.stats(NpcCombatStats.builder().attackLevel(350).magicLevel(350).rangedLevel(350).defenceLevel(200)
                .bonus(CombatBonus.ATTACK_MAGIC, 300).build());
        combat.aggression(NpcCombatAggression.builder().range(25).always(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).bind(true).build());
        combat.focus(NpcCombatFocus.builder().attackClosest(true).disableFollowingOpponent(true).build());
        combat.combatScript("maidensugadinti").deathAnimation(8093);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).weight(8).build());
        style.damage(NpcCombatDamage.builder().maximum(36).prayerEffectiveness(0.6).build());
        style.animation(8092).attackSpeed(8).attackRange(25);
        style.projectile(NpcCombatProjectile.builder().id(1577).startHeight(0).endHeight(0).curve(0).radius(0).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.TYPELESS).build());
        style.damage(NpcCombatDamage.builder().maximum(15).ignorePrayer(true).build());
        style.animation(8091).attackSpeed(8).attackRange(25);
        style.targetGraphic(new Graphic(1579));
        style.projectile(NpcCombatProjectile.builder().id(1578).speedMinimumDistance(8).build());
        style.multiTarget(true);
        var targetTile = NpcCombatTargetTile.builder();
        targetTile.breakOff(
                NpcCombatTargetTile.BreakOff.builder().count(2).onlyFocusedOpponent(true).distance(3).build());
        style.specialAttack(targetTile.build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
