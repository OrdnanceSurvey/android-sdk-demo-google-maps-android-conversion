android-sdk-demo-google-maps-android-conversion
===============================================

A demo app using the openspace-android-SDK as a drop in replacement for the Google Maps Android SDK.

This project contains an example of how convert an application currently using Google Maps Android SDK by renaming the imports.

See [Conversion steps](#conversion-steps) for details about converting and the two targets in this project.


![ScreenShot](https://github.com/OrdnanceSurvey/android-sdk-demo-google-maps-android-conversion/raw/master/screenshot-mk.png "Screenshot of android-conversion app")
![ScreenShot](https://github.com/OrdnanceSurvey/android-sdk-demo-google-maps-android-conversion/raw/master/screenshot-os.png "Screenshot of android-conversion OS app")


Getting started
---


#### Registration and Access

In order to access and use the Service via the OpenSpace Android-SDK, you must [apply for an API key.](https://github.com/OrdnanceSurvey/openspace-android-sdk#getting-started)


#### Clone project

Copy the project to your local machine, eg:

```bash
git clone https://github.com/OrdnanceSurvey/android-sdk-demo-mapkit-conversion.git
```

#### Download openspace-ios-sdk

 - Download the latest [openspace-android-sdk static framework](http://www.ordnancesurvey.co.uk/oswebsite/web-services/os-openspace/android-sdk.html)
 - Unzip into the StaticFrameworks directory under the project root
 <pre>
 $SRCROOT/StaticFrameworks
 </pre>
 

#### Update demo app with your API Key and Bundle Identifier details

Copy and paste the API Key into the demo app

Update Bundle Identifier

Change kIS_PRO appropriately

```objective-c
//In MapViewController.m

static NSString *const kOSApiKey = @"YOUR_KEY_HERE";
static BOOL const kOSIsPro = YES;

```

#### Build and run

Project can now be built and run.

- Select the OSMKDemoApp target for Apple Mapkit version
- Select the OSMKDemoAppOS target for the iOS Openspace SDK version

#### Conversion steps

The initial project was a basic demo using Apple Mapkit and Storyboards. To convert an existing Mapkit application:

1. If using storyboard or nib then replace the existing MKMapView with a UIView of class name OSMapView
2. Rename symbols, add the lines below in an appropriate file, the demo app does this in `MapViewController.h`

```java
package com.example.hellomap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity {
    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
 ```


All code using prefix MK will now be replaced, the only extra code required is that to configure your API key and tile sources.

##### Demo project

This demo project has two targets, the OSMKDemoApp target has a preprocessor macro USE_MAPKIT build setting which allows you to switch back to Mapkit as below:

```objective-c
#ifdef USE_MAPKIT // or to negate #ifndef USE_MAPKIT
//do something
#endif
```


Questions and Issues
-------

If you have any questions or issues with the openspace-ios-sdk or this demo app then please email osopenspace@ordnancesurvey.co.uk


Licence
-------
OpenSpace iOS SDK Licence Terms

The OpenSpace iOS SDK is protected by © Crown copyright – Ordnance Survey 2012.[https://github.com/OrdnanceSurvey]

All rights reserved (subject to the BSD licence terms as follows):.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

- Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
- Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
- Neither the name of Ordnance Survey nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE


The OpenSpace iOS SDK includes the Route-Me library. 
The Route-Me library is copyright (c) 2008-2012, Route-Me Contributors
All rights reserved (subject to the BSD licence terms as follows):

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

- Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
- Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

Route-Me depends on the Proj4 Library. [ http://trac.osgeo.org/proj/wiki/WikiStart ]
Proj4 is copyright (c) 2000, Frank Warmerdam / Gerald Evenden
Proj4 is subject to the MIT licence as follows:

 Permission is hereby granted, free of charge, to any person obtaining a
 copy of this software and associated documentation files (the "Software"),
 to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense,
 and/or sell copies of the Software, and to permit persons to whom the
 Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included
 in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 DEALINGS IN THE SOFTWARE.

Route-Me depends on the fmdb library. [ https://github.com/ccgus/fmdb ]
fmdb is copyright (c) 2008 Flying Meat Inc 
fmdb is subject to the MIT licence as follows:

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
