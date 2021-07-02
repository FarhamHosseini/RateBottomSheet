RateBottomSheet
=================

This an Android library to help to promote your Android App by prompting users to **rate** your app in the Google Play Store with a material design friendly **BottomSheet**.

#### Set up the dependency
1. Add the mavenCentral() repository to your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		mavenCentral()
	}
}
```
2. Add the RateBottomSheet dependency in the build.gradle:
```
implementation group: 'com.apachat', name: 'ratebottomsheet-android', version: '1.3.1'
```

Badge:
-----
[![Maven Central](https://img.shields.io/maven-central/v/com.apachat/ratebottomsheet-android.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.apachat%22%20AND%20a:%22ratebottomsheet-android%22)

Kotlin
-----

```kotlin
RateBottomSheetManager(this)
    .setInstallDays(1) // 3 by default
    .setLaunchTimes(2) // 5 by default
    .setRemindInterval(1) // 2 by default
    .setShowAskBottomSheet(false) // True by default
    .setShowLaterButton(false) // True by default
    .setShowCloseButtonIcon(false) // True by default
    .monitor()

// Show bottom sheet if meets conditions
// With AppCompatActivity or Fragment
RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)
```

Override string xml resources on your application to change the texts in bottom sheet:
```xml
<resources>
    <string name="rate_popup_ask_title">Like this App?</string>
    <string name="rate_popup_ask_message">Do you like using this application?</string>
    <string name="rate_popup_ask_ok">Yes I do</string>
    <string name="rate_popup_ask_no">Not really</string>

    <string name="rate_popup_title">Rate this app</string>
    <string name="rate_popup_message">Would you mind taking a moment to rate it? It won\'t take more than a minute. Thanks for your support!</string>
    <string name="rate_popup_ok">Rate it now</string>
    <string name="rate_popup_later">Remind me later</string>
    <string name="rate_popup_no">No, thanks</string>
</resources>
```

Listener
-----

When calling `RateBottomSheet.showRateBottomSheetIfMeetsConditions(...)` you can choose to add another parameter of type `AskRateBottomSheet.ActionListener`; this allows you to implement 3 optional callbacks.
Here is how:

```kotlin
RateBottomSheet.showRateBottomSheetIfMeetsConditions(
    this,
    listener = object : AskRateBottomSheet.ActionListener {
        override fun onDislikeClickListener() {
            // Will be called when a click on the "I don't like" button is triggered
        }

        override fun onRateClickListener() {
            // Will be called when a click on the "Rate" button is triggered
        }

        /*override fun onNoClickListener() {
            // Will be called when a click on the "No thanks" button is triggered,
            // in this example is commented,
            // but each callback is optional and it's up to you whether to implement it or not!
        }*/
    }
)
```

Debug
-----

Enable `debugForceOpen` to show bottom sheet without conditions check like this:

```kotlin
RateBottomSheetManager(this)
    .setDebugForceOpenEnable(true) // False by default

// Don't forget to run showRate function
RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)
```

You can also enable logs with `debugLogEnable` properties:

```kotlin
RateBottomSheetManager(this)
    .setDebugLogEnable(true) // False by default
```

Clear all current data from RateBottomSheet like this:

```kotlin
RateBottomSheetManager(this)
    .clear()
```

Java
-----

You can call `showRateBottomSheetIfMeetsConditions` func like this:

```java
RateBottomSheet.Companion.showRateBottomSheetIfMeetsConditions(this);
```

And because this library it's write in Kotlin you need to add **kotlin-stdlib** dependency on your java project:

```groovy
implementation 'org.jetbrains.kotlin:kotlin-stdlib:1.5.20'
```

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/FarhamHosseini/RateBottomSheet/issues).
