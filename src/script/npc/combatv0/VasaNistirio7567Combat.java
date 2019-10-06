package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.special.NpcCombatTargetTile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class VasaNistirio7567Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ENDARKENED_JUICE, 5)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TWISTED_PLUS_4, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.XERICS_AID_PLUS_4, 2)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.TWISTED_PLUS_4, 2)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VASA_NISTIRIO_7567);
        combat.hitpoints(NpcCombatHitpoints.total(400));
        combat.stats(NpcCombatStats.builder().magicLevel(230).rangedLevel(230).defenceLevel(175)
                .bonus(CombatBonus.ATTACK_RANGED, 100).bonus(CombatBonus.DEFENCE_STAB, 170)
                .bonus(CombatBonus.DEFENCE_SLASH, 190).bonus(CombatBonus.DEFENCE_CRUSH, 50)
                .bonus(CombatBonus.DEFENCE_MAGIC, 400).bonus(CombatBonus.DEFENCE_RANGED, 60).build());
        combat.aggression(NpcCombatAggression.builder().range(12).always(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).bind(true).build());
        combat.focus(NpcCombatFocus.builder().disableFacingOpponent(true).disableFollowingOpponent(true).build());
        combat.combatScript("VasaNistirioCS").deathAnimation(7415);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.RANGED).subType(HitType.TYPELESS).build());
        style.damage(NpcCombatDamage.builder().maximum(30).prayerEffectiveness(0.5).build());
        style.attackSpeed(2).attackRange(7);
        style.targetGraphic(new Graphic(1330));
        style.projectile(NpcCombatProjectile.builder().id(1329).speedMinimumDistance(10).build());
        style.multiTarget(true);
        var targetTile = NpcCombatTargetTile.builder().radius(1);
        style.specialAttack(targetTile.build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
