package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.special.NpcCombatTargetTile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class ChaosElemental610_16016Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().underKiller(true).rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(0.33).broadcast(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PET_CHAOS_ELEMENTAL)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLOODY_KEY)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.CHAOS_ELEMENTAL_610_16016);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(36000).build());
        combat.hitpoints(NpcCombatHitpoints.builder().total(2500).bar(HitpointsBar.GREEN_RED_120).build());
        combat.stats(NpcCombatStats.builder().attackLevel(270).magicLevel(270).rangedLevel(270).defenceLevel(270).bonus(CombatBonus.MELEE_DEFENCE, 70).bonus(CombatBonus.DEFENCE_MAGIC, 70).bonus(CombatBonus.DEFENCE_RANGED, 70).build());
        combat.aggression(NpcCombatAggression.builder().range(8).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.killCount(NpcCombatKillCount.builder().sendMessage(true).build());
        combat.combatScript("ChaosElementalCS").deathAnimation(3147);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_STAB));
        style.damage(NpcCombatDamage.maximum(28));
        style.animation(3146).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_SLASH));
        style.damage(NpcCombatDamage.maximum(28));
        style.animation(3146).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(28));
        style.animation(3146).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(28));
        style.animation(3146).attackSpeed(5);
        style.castGraphic(new Graphic(556, 100)).targetGraphic(new Graphic(558, 100));
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(28));
        style.animation(3146).attackSpeed(5);
        style.castGraphic(new Graphic(556, 100)).targetGraphic(new Graphic(558, 100));
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.identifier(1);
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.TYPELESS).build());
        style.damage(NpcCombatDamage.builder().maximum(4).alwaysMaximum(true).build());
        style.animation(3146).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.TYPELESS).build());
        style.damage(NpcCombatDamage.maximum(28));
        style.animation(3146).attackSpeed(5);
        style.castGraphic(new Graphic(556, 100)).targetGraphic(new Graphic(558, 100));
        style.projectile(NpcCombatProjectile.builder().id(557).speedMinimumDistance(8).build());
        style.multiTarget(true);
        var targetTile = NpcCombatTargetTile.builder();
        targetTile.breakOff(NpcCombatTargetTile.BreakOff.builder().count(2).distance(2).build());
        style.specialAttack(targetTile.build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
