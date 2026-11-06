# The Abandoned

A PS1-style survival horror game. This repository builds it as an Android app.

## Getting the app on your phone

1. On your phone, open the **Releases** page of this repository (sign in to GitHub first).
2. Download `TheAbandoned.apk` from the newest release.
3. Open the download. The first time, Android asks you to allow installs from your browser; allow it.
4. For updates, download the newest `TheAbandoned.apk` and install it over the old one. Your saves stay.

## How updates work

- `game/the-abandoned.html` is the game itself (the same page as the Claude artifact).
- `tools/build_index.py` wraps it into `app/src/main/assets/index.html` with three.js bundled, so it runs offline (fonts still load from Google Fonts when online).
- Every push to `main` runs `.github/workflows/build.yml`, which builds the APK and publishes it as a new release.
- Every build is signed with the same key (`app/abandoned.keystore`), so updates install over the old app. That key is only suitable for sideloading, not for the Play Store.
