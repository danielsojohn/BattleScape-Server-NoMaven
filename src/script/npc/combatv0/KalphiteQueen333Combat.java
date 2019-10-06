package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.player.Skills;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class KalphiteQueen333Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.KALPHITE_QUEEN_333);
        combat.spawn(NpcCombatSpawn.builder().respawnId(NpcId.KALPHITE_QUEEN_333_965).deathDelay(6).build());
        combat.hitpoints(NpcCombatHitpoints.total(255));
        combat.stats(NpcCombatStats.builder().attackLevel(300).magicLevel(150).defenceLevel(300)
                .bonus(CombatBonus.DEFENCE_STAB, 50).bonus(CombatBonus.DEFENCE_SLASH, 50)
                .bonus(CombatBonus.DEFENCE_CRUSH, 10).bonus(CombatBonus.DEFENCE_MAGIC, 100)
                .bonus(CombatBonus.DEFENCE_RANGED, 100).build());
        combat.aggression(NpcCombatAggression.builder().range(8).build());
        combat.immunity(NpcCombatImmunity.builder().venom(true).build());
        combat.focus(NpcCombatFocus.builder().keepWithinDistance(1).build());
        combat.combatScript("kalphitequeen").deathAnimation(6242).blockAnimation(6232);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_STAB));
        style.damage(NpcCombatDamage.maximum(31));
        style.animation(6241).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.builder().maximum(31).ignoreDefence(true).build());
        style.animation(6231).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().statDrain(Skills.PRAYER, 1).includeMiss(true).build());
        style.multiNearTarget(1);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(31).ignoreDefence(true).build());
        style.animation(6231).attackSpeed(4);
        style.castGraphic(new Graphic(278)).targetGraphic(new Graphic(281));
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
