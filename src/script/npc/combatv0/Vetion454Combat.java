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
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.NpcCombatType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.style.special.NpcCombatTargetTile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Vetion454Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable =
                NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_4096).broadcast(true).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.VESTAS_LONGSWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.STATIUSS_WARHAMMER)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.VESTAS_SPEAR)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MORRIGANS_JAVELIN, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MORRIGANS_THROWING_AXE, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ZURIELS_STAFF)));
        dropTable = NpcCombatDropTable.builder().chance(0.05).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.VETION_JR)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.29).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RING_OF_THE_GODS)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.58).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_2H_SWORD)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(0.87).log(true);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_PICKAXE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(1.0);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CLUE_SCROLL_ELITE)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(1.4);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_RANARR_WEED_NOTED, 100)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_RARE);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_2H_SWORD)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CANNONBALL, 250)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGIC_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PALM_TREE_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.YEW_SEED)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MORT_MYRE_FUNGUS_NOTED, 200)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DARK_FISHING_BAIT, 375)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_STAFF)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CHAOS_RUNE, 400)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DEATH_RUNE, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLOOD_RUNE, 200)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SANFEW_SERUM_4_NOTED, 10)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MAGIC_LOGS_NOTED, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GOLD_ORE_NOTED, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BONES_NOTED, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_DRAGONSTONE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OAK_PLANK_NOTED, 300)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPERCOMPOST_NOTED, 100)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.GRIMY_TORSTOL_NOTED, 1, 5)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_PICKAXE)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DARK_CRAB_NOTED, 8)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPER_RESTORE_4, 3)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 15000, 20000)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OGRE_COFFIN_KEY_NOTED, 10)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.LIMPWURT_ROOT_NOTED, 50)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_RUBY_NOTED, 20)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.UNCUT_DIAMOND_NOTED, 10)));
        drop.table(dropTable.build());
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BIG_BONES)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.VETION_454);
        combat.spawn(NpcCombatSpawn.builder().respawnId(NpcId.VETION_REBORN_454).deathDelay(8).build());
        combat.hitpoints(NpcCombatHitpoints.total(255));
        combat.stats(NpcCombatStats.builder().attackLevel(430).magicLevel(300).defenceLevel(395).bonus(CombatBonus.DEFENCE_STAB, 201).bonus(CombatBonus.DEFENCE_SLASH, 200).bonus(CombatBonus.DEFENCE_MAGIC, 250).bonus(CombatBonus.DEFENCE_RANGED, 270).build());
        combat.aggression(NpcCombatAggression.builder().range(8).build());
        combat.immunity(NpcCombatImmunity.builder().venom(true).build());
        combat.killCount(NpcCombatKillCount.builder().sendMessage(true).build());
        combat.combatScript("VetionCS").type(NpcCombatType.UNDEAD).blockAnimation(5508);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_STAB));
        style.damage(NpcCombatDamage.maximum(30));
        style.animation(5499).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.builder().type(HitType.MAGIC).subType(HitType.TYPELESS).build());
        style.damage(NpcCombatDamage.maximum(30));
        style.animation(5499).attackSpeed(4);
        style.targetGraphic(new Graphic(749));
        style.projectile(NpcCombatProjectile.builder().id(280).speedMinimumDistance(8).build());
        var targetTile = NpcCombatTargetTile.builder();
        targetTile.breakOff(NpcCombatTargetTile.BreakOff.builder().count(2).distance(2).build());
        style.specialAttack(targetTile.build());
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
