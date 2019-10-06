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
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class RevenantOrk105Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.REVENANT_ORK_105);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(50).build());
        combat.hitpoints(NpcCombatHitpoints.total(105));
        combat.stats(NpcCombatStats.builder().attackLevel(99).magicLevel(110).rangedLevel(130).defenceLevel(60)
                .bonus(CombatBonus.MELEE_ATTACK, 60).bonus(CombatBonus.DEFENCE_STAB, 148)
                .bonus(CombatBonus.DEFENCE_SLASH, 150).bonus(CombatBonus.DEFENCE_CRUSH, 146)
                .bonus(CombatBonus.DEFENCE_MAGIC, 50).bonus(CombatBonus.DEFENCE_RANGED, 148).build());
        combat.aggression(NpcCombatAggression.builder().always(true).build());
        combat.focus(NpcCombatFocus.builder().keepWithinDistance(1).build());
        combat.killCount(NpcCombatKillCount.builder().asName("Revenant").build());
        combat.combatScript("revenant").type(NpcCombatType.UNDEAD).deathAnimation(4321).blockAnimation(4322);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(20));
        style.animation(4320).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(20));
        style.animation(4320).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).weight(32).build());
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(4320).attackSpeed(5);
        style.targetGraphic(new Graphic(1454, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(4320).attackSpeed(5);
        style.targetGraphic(new Graphic(363));
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().magicBind(16).build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
