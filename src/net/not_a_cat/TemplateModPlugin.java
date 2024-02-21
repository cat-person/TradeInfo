package net.not_a_cat;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import java.util.List;

public class TemplateModPlugin extends BaseModPlugin implements EconomyAPI.EconomyUpdateListener {

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);
        Global.getSector().getEconomy().addUpdateListener(this);
    }

    @Override
    public void commodityUpdated(String commodityId) {

    }

    @Override
    public void economyUpdated() {
        for (IntelInfoPlugin intelInfoPlugin : Global.getSector().getIntelManager().getIntel(CommodityDeficitIntel.class)) {
            ((CommodityDeficitIntel)intelInfoPlugin).markForRemove();
        }

        Global.getSector().getIntelManager().removeAllThatShouldBeRemoved();

        for (String commodityId: Global.getSector().getEconomy().getAllCommodityIds()) {

            if(commodityId.equals("lobster") || commodityId.equals("ships") ) {
                continue;
            }

            CommoditySpecAPI commoditySpecAPI = Global.getSector().getEconomy().getCommoditySpec(commodityId);
            List<LocationAPI> marketLocations = Global.getSector().getEconomy().getLocationsWithMarkets();

            for (LocationAPI marketLocation : marketLocations) {
                for (MarketAPI market : Global.getSector().getEconomy().getMarkets(marketLocation)) {
                    float price = market.getDemandPrice(commodityId, 1.0, false);
                    float demand = market.getDemand(commodityId).getDemandValue();

                    if (commoditySpecAPI.getBasePrice() * 1.4 < price) {
                        Global.getSector().getIntelManager().addIntel(
                                new CommodityDeficitIntel(market.getPrimaryEntity(), commodityId, (int) demand, (int) price));
                    }
                    break;
                }
            }

        }
    }

    @Override
    public boolean isEconomyListenerExpired() {
        return false;
    }
}
