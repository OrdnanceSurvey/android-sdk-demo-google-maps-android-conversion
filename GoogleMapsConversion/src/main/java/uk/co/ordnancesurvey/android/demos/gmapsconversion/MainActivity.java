package uk.co.ordnancesurvey.android.demos.gmapsconversion;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import uk.co.ordnancesurvey.android.maps.OSMap;
import uk.co.ordnancesurvey.android.maps.OSTileSource;
import uk.co.ordnancesurvey.android.maps.SupportMapFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private final static String TAG = "MainActivity";

    private OSMap mMap;

    private boolean FREE_KEY = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
        setUpSources();
    }

    private void setUpSources() {
        ArrayList<OSTileSource> sources = new ArrayList<OSTileSource>();
        sources.addAll(mMap.localTileSourcesInDirectory(this,
                Environment.getExternalStorageDirectory()));
        sources.add(mMap.webTileSource("E956736CF5B25963E0430C6CA40A215D", FREE_KEY, null));
        mMap.setTileSources(sources);
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment))
                .getMap();
        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
