package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class JungleDemon195Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.JUNGLE_DEMON_195);
        combat.hitpoints(NpcCombatHitpoints.total(170));
        combat.stats(NpcCombatStats.builder().attackLevel(170).magicLevel(170).defenceLevel(170).bonus(CombatBonus.MELEE_ATTACK, 50).bonus(CombatBonus.DEFENCE_SLASH, 50).bonus(CombatBonus.DEFENCE_MAGIC, 50).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.combatScript("jungledemon").type(NpcCombatType.DEMON).deathAnimation(67).blockAnimation(65);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_SLASH));
        style.damage(NpcCombatDamage.maximum(32));
        style.animation(64).attackSpeed(6).attackRange(2);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(32).splashOnMiss(true).build());
        style.animation(69).attackSpeed(6);
        style.targetGraphic(new Graphic(134, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(32).splashOnMiss(true).build());
        style.animation(69).attackSpeed(6);
        style.targetGraphic(new Graphic(137, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(32));
        style.animation(69).attackSpeed(6);
        style.targetGraphic(new Graphic(140, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(32).splashOnMiss(true).build());
        style.animation(69).attackSpeed(6);
        style.targetGraphic(new Graphic(131, 124));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
