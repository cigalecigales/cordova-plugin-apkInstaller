[![npm version](https://badge.fury.io/js/cordova-plugin-apkinstaller.svg)](https://badge.fury.io/js/cordova-plugin-apkinstaller)

# cordova-plugin-apkInstaller

このプラグインはAPKファイルからインストーラーを
立ち上げるためのプラグインです。<br>
This plugin allows you to start the app installer.<br>

## Installation

```bash
$ cordova plugin add cordova-plugin-apkinstaller
```

## Supported Platforms
Android only. (version 7.0.0 ~ )

## How to use
### Parameters
* **fileName**: install apk file name (ex: android-sample.apk)
* **successCallback**: A callback when success to start the installer.
* **errorCallback**: A callback when occur errors.

```js
apkInstaller.install(fileName, function(msg) {
    // Start the installer
}, function(error) {
    // Install error
});
```

### Other
**Shared directory**<br>
Shared directory setting is in `file_paths.xml`.<br>
For details, please click here => [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html)

```xml
<!-- platforms/android/res/xml/file_paths.xml -->
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <files-path name="apkDirectory" path="/"/>
</paths>
```

## Example
以下は、インストールボタンを押したときにAPKファイルを外部からダウンロードし、<br>
ダウンロードしたAPKのインストーラーを立ち上げるサンプルです。<br>
APKファイルのダウンロードは`cordova-plugin-file-transfer`等を用いてください。<br>
<br>
[ApkInstallerPluginSample](https://github.com/cigalecigales/ApkInstallerPluginSample)
<br>
<br>
Click the INSTALL button.<br>
![top page](https://github.com/cigalecigales/cordova-plugin-apkInstaller/wiki/images/image01.png)
<br>
<br>
Downloading apk file...<br>
![download apk](https://github.com/cigalecigales/cordova-plugin-apkInstaller/wiki/images/image02.png)
<br>
<br>
Start the installer !<br>
![start the installer](https://github.com/cigalecigales/cordova-plugin-apkInstaller/wiki/images/image04.png)

```js
var element = document.getElementById(id);

element.addEventListener('click', function() {

    // Apk download by cordova-plugin-file-transfer
    var fileTransfer = new FileTransfer();

    // Get cordova file data directory (app sandbox directory)
    //  > file:///data/user/0/io.cordova.apk.installer.sample/files/
    var sandBoxDirectory = cordova.file.dataDirectory;

    // Apk download path
    var apkUrl = 'http://example.com/sample.apk';

    // Get file name from apk url;
    var fileName = apkUrl.match(/[^/]+$/i)[0];

    fileTransfer.download(
        apkUrl,
        sandBoxDirectory + fileName,
        function(entry) {
            // Install app
            apkInstaller.install(fileName, function(msg) {
                // Start the installer
            }, function(error) {
                // Install error
            });
        },
        function(error) {
            // Download error
        },
        false, {}
    );

}, false);
```
