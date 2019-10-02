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
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class RevenantImp7Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.REVENANT_IMP_7);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(50).build());
        combat.hitpoints(NpcCombatHitpoints.total(10));
        combat.stats(NpcCombatStats.builder().attackLevel(5).magicLevel(9).rangedLevel(5).defenceLevel(4).bonus(CombatBonus.ATTACK_MAGIC, 5).bonus(CombatBonus.DEFENCE_MAGIC, 5).build());
        combat.aggression(NpcCombatAggression.builder().always(true).build());
        combat.focus(NpcCombatFocus.builder().keepWithinDistance(1).build());
        combat.killCount(NpcCombatKillCount.builder().asName("Revenant").build());
        combat.combatScript("revenant").type(NpcCombatType.UNDEAD).deathAnimation(172).blockAnimation(170);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_STAB));
        style.damage(NpcCombatDamage.maximum(2));
        style.animation(169).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(2));
        style.animation(173).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(2).splashOnMiss(true).build());
        style.animation(173).attackSpeed(5);
        style.targetGraphic(new Graphic(1454, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
