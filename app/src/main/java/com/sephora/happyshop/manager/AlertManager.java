package com.sephora.happyshop.manager;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by simtech on 20/8/2017.
 */

public class AlertManager {

    public static void longtoastMessage(Context ctx,String message)
    {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }

    public static void shorttoastMessage(Context ctx, String message)
    {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
}
