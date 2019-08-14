package script.npc.combat;

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
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class RevenantDarkBeast120Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.REVENANT_DARK_BEAST_120);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(50).build());
        combat.hitpoints(NpcCombatHitpoints.total(140));
        combat.stats(NpcCombatStats.builder().attackLevel(93).magicLevel(130).rangedLevel(135).defenceLevel(80).bonus(CombatBonus.MELEE_ATTACK, 65).bonus(CombatBonus.ATTACK_RANGED, 45).bonus(CombatBonus.DEFENCE_STAB, 153).bonus(CombatBonus.DEFENCE_SLASH, 152).bonus(CombatBonus.DEFENCE_CRUSH, 155).bonus(CombatBonus.DEFENCE_MAGIC, 70).bonus(CombatBonus.DEFENCE_RANGED, 158).build());
        combat.aggression(NpcCombatAggression.builder().always(true).build());
        combat.focus(NpcCombatFocus.builder().keepWithinCombatTiles(1).build());
        combat.killCount(NpcCombatKillCount.builder().asName("Revenant").build());
        combat.combatScript("revenant").type(NpcCombatType.UNDEAD).deathAnimation(2733).blockAnimation(2732);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_CRUSH));
        style.damage(NpcCombatDamage.maximum(23));
        style.animation(2731).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.maximum(23));
        style.animation(2731).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).weight(32).build());
        style.damage(NpcCombatDamage.builder().maximum(23).splashOnMiss(true).build());
        style.animation(2731).attackSpeed(5);
        style.targetGraphic(new Graphic(1454, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(23).splashOnMiss(true).build());
        style.animation(2731).attackSpeed(5);
        style.targetGraphic(new Graphic(363));
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().magicBind(16).build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
