package net.not_a_cat;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;

public class TemplateModPlugin extends BaseModPlugin implements EconomyAPI.EconomyUpdateListener {

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);
        Global.getSector().getEconomy().addUpdateListener(this);
    }

    @Override
    public void commodityUpdated(String commodityId) {
        // Log the shit out of that
    }

    @Override
    public void economyUpdated() {
        Global.getSector().getIntelManager().addIntel(new CommodityDeficitIntel(
                "tushenka", 100, 500
        ));
    }

    @Override
    public boolean isEconomyListenerExpired() {
        return false;
    }
}
