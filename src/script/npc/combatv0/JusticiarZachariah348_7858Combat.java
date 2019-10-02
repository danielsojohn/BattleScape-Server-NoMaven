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
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.special.NpcCombatTargetTile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class JusticiarZachariah348_7858Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.JUSTICIAR_ZACHARIAH_348_7858);
        combat.spawn(NpcCombatSpawn.builder().lock(4).animation(7964).build());
        combat.hitpoints(NpcCombatHitpoints.total(320));
        combat.stats(NpcCombatStats.builder().attackLevel(500).magicLevel(180).defenceLevel(100).bonus(CombatBonus.ATTACK_MAGIC, 80).bonus(CombatBonus.MELEE_DEFENCE, 200).bonus(CombatBonus.DEFENCE_MAGIC, -60).bonus(CombatBonus.DEFENCE_RANGED, 200).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.immunity(NpcCombatImmunity.builder().venom(true).build());
        combat.combatScript("magearenademon").deathAnimation(7854).blockAnimation(7965);

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.builder().maximum(43).prayerEffectiveness(0.5).build());
        style.animation(7963).attackSpeed(3);
        style.targetGraphic(new Graphic(1518, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).weight(6).build());
        style.damage(NpcCombatDamage.builder().maximum(43).prayerEffectiveness(0.5).splashOnMiss(true).build());
        style.animation(7962).attackSpeed(6);
        style.targetGraphic(new Graphic(1518, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.TYPELESS).build());
        style.damage(NpcCombatDamage.builder().maximum(43).ignorePrayer(true).build());
        style.animation(7962).attackSpeed(6);
        style.targetGraphic(new Graphic(137));
        style.projectile(NpcCombatProjectile.builder().id(1515).speedMinimumDistance(8).build());
        var targetTile = NpcCombatTargetTile.builder().teleportOpponent(true);
        style.specialAttack(targetTile.build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
