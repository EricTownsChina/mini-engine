package common.kit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description: some singleton bean in project storage
 *
 * @author EricTowns
 * @date 2023/5/11 20:55
 */
public final class DefaultStorage {

    private static final Gson gson = new Gson();

    private DefaultStorage() {
    }

    public static Gson defaultGson() {
        return gson;
    }

    public static GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

}
