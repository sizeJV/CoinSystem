package me.size.util;

import java.util.UUID;


/**
 * <API>
 * Only use API related functions when using this API
 * Methods to be used are tagged with the API brackets!
 * </API>
 */

public class API {

    /**
     * <API>
     *
     * @param uuid: Player the Coins will get set to
     * @param amount: Amount the Player will get set
     * </API>
     */

    public static void setCoins(UUID uuid, Integer amount) {
        CoinsHandler.getCachedValues().put(uuid, amount);
    }


    /**
     * <API>
     *
     * @param uuid: Player to receive the Coins from
     * </API>
     */

    public static int getCoins(UUID uuid) {
        if (!CoinsHandler.getCachedValues().containsKey(uuid)) {
            return 0;
        }
        return CoinsHandler.getCachedValues().get(uuid);
    }
}
