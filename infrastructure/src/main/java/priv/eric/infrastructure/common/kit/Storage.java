package priv.eric.infrastructure.common.kit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description: bean storage
 *
 * @author EricTowns
 * @date 2023/5/11 23:48
 */
public final class Storage {

    private Storage() {}

    private static final Gson DEFAULT_GSON = new Gson();

    public static Gson defaultGson() {
        return DEFAULT_GSON;
    }

    public static GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

}
