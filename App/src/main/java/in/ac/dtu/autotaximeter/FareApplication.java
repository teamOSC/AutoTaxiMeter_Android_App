package in.ac.dtu.autotaximeter;

import android.app.Application;
import android.content.Context;

/**
 * Created by championswimmer on 5/1/14.
 */
public class FareApplication extends Application {

    public static Context context;

    @Override
    public void onCreate () {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getFareContext () {
        return context;
    }
}
