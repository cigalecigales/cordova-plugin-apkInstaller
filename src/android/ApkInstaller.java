package plugin.apkInstaller;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.support.v4.content.FileProvider;
import java.text.SimpleDateFormat;

public class ApkInstaller extends CordovaPlugin {

    private static final String ACTION_INSTALL = "install";

    private static final String MYME_TYPE_APK = "application/vnd.android.package-archive";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (action.equals(ACTION_INSTALL)) {
            String fileName = data.getString(0);
            Context context = this.cordova.getActivity().getApplicationContext();
            File file = new File(context.getFilesDir() + "/" + fileName);

            try {
                // Get file uri
                Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                // MIME-TYPE settings
                intent.setDataAndType(uri, MYME_TYPE_APK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                );
                context.startActivity(intent);

                callbackContext.success();
            } catch (Exception ex) {
                callbackContext.error(ex.toString());
            }
            return true;
        } else {
            return false;
        }
    }
}
