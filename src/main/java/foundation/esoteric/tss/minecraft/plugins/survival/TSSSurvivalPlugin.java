package foundation.esoteric.tss.minecraft.plugins.survival;

import foundation.esoteric.tss.minecraft.plugins.core.TSSCorePlugin;
import foundation.esoteric.tss.minecraft.plugins.ranks.TSSRanksPlugin;
import foundation.esoteric.tss.minecraft.plugins.survival.commands.claim.*;
import foundation.esoteric.tss.minecraft.plugins.survival.commands.SkillsCommand;
import foundation.esoteric.tss.minecraft.plugins.survival.commands.TradeCommand;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.BlockListener;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.OreMineListener;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.SurvivalConnectionListener;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.TradeListener;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.claim.ClaimListener;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.skill.SkillsListener;
import foundation.esoteric.tss.minecraft.plugins.survival.event.listeners.skill.SkillsMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class TSSSurvivalPlugin extends JavaPlugin {

  private final TSSCorePlugin core = (TSSCorePlugin) Bukkit.getPluginManager().getPlugin("TSSCore");

  private final TSSRanksPlugin ranksPlugin = (TSSRanksPlugin) Bukkit.getPluginManager().getPlugin("TSSRanks");

  private final HashMap<UUID, UUID> outGoingTradeRequests = new HashMap<>();

  public TSSRanksPlugin getRanksPlugin() {
	return ranksPlugin;
  }

  public TSSCorePlugin getCore() {
	return core;
  }

  public HashMap<UUID, UUID> getOutGoingTradeRequests() {
	return outGoingTradeRequests;
  }

  @Override
  public void onEnable() {
	new ClaimCommand(this);
	new UnClaimCommand(this);

	new ClaimMapCommand(this);
	new GiveClaimMapCommand(this);

	new TrustCommand(this);
	new UnTrustCommand(this);

	new SkillsCommand(this);

	new TradeCommand(this);

	PluginManager pluginManager = Bukkit.getPluginManager();
	pluginManager.registerEvents(new SurvivalConnectionListener(this), this);
	pluginManager.registerEvents(new ClaimListener(this), this);
	pluginManager.registerEvents(new SkillsListener(this), this);
	pluginManager.registerEvents(new OreMineListener(this), this);
	pluginManager.registerEvents(new SkillsMenuListener(this), this);
	pluginManager.registerEvents(new BlockListener(this), this);
	pluginManager.registerEvents(new TradeListener(this), this);
  }
}
