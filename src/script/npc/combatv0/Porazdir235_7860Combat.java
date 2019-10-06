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
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Porazdir235_7860Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.PORAZDIR_235_7860);
        combat.spawn(NpcCombatSpawn.builder().lock(8).animation(7842).build());
        combat.hitpoints(NpcCombatHitpoints.total(320));
        combat.stats(NpcCombatStats.builder().attackLevel(250).magicLevel(80).defenceLevel(100)
                .bonus(CombatBonus.ATTACK_MAGIC, 80).bonus(CombatBonus.MELEE_DEFENCE, 200)
                .bonus(CombatBonus.DEFENCE_MAGIC, -60).bonus(CombatBonus.DEFENCE_RANGED, 200).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.immunity(NpcCombatImmunity.builder().venom(true).build());
        combat.combatScript("magearenademon").deathAnimation(7843).blockAnimation(7838);

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.builder().maximum(43).prayerEffectiveness(0.5).build());
        style.animation(7840).attackSpeed(6);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(43).prayerEffectiveness(0.5).splashOnMiss(true).build());
        style.animation(7841).attackSpeed(6);
        style.targetGraphic(new Graphic(78));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
