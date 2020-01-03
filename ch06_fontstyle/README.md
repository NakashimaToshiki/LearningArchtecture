# FontStyle

サンプルアプリ

![play.png](./play.png?raw=true)

# 概要



# 備忘録

## 1. デフォルトのアクションバーの無効化

無効化していないとエラー「This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.」がでる

アプリマニュフェストでappcompat の NoActionBar テーマのいずれかを使用するように <application> 要素を設定します。

    <application
        android:theme="@style/Theme.AppCompat.Light.NoActionBar"
        />

初期プロジェクトだとthemeの値がstyle.xmlを参照しているので、ここをstyle.xmlを修正する

## 2. fontファイルの生成

fontフォルダを生成してxmlファイル(opensans_font.xml)を生成する

    <font-family xmlns:app="http://schemas.android.com/apk/res-auto">
        <font
            app:fontStyle="normal"
            app:fontWeight="400"
            app:font="@font/opensans_regular" />
        <font
            app:fontStyle="normal"
            app:fontWeight="700"
            app:font="@font/opensans_semibold" />
    </font-family>

fontフォルダにフォントファイル（.ttf)を作成しておく必要があり。opensans_regular.ttf,opensans_semibold.ttfというファイルが必要。そのままBluePrintにあったフォントファイルを頂戴した。

## 3. styles.xmlの修正

ファイルの中身はこんな感じ

    <resources xmlns:tools="http://schemas.android.com/tools">

        <!-- Base application theme. --><style name="AppTheme" parent="Base.AppTheme" />

        <style name="AppTheme.OverlapSystemBar" parent="Base.AppTheme" />

        <style name="Base.AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
            <!-- Customize your theme here. -->
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>
            <!-- For Android sdk versions 26+. -->
            <item name="android:fontFamily" tools:targetApi="jelly_bean">@font/opensans_font</item>
            <!-- Target Android sdk versions < 26 and > 14. -->
            <item name="fontFamily">@font/opensans_font</item>
            <!-- sets the style for the overflow button -->
            <item name="actionOverflowButtonStyle">@style/actionOverflowButtonStyle</item>
        </style>

        <!-- This style defines the tint color of the overflow menu button -->
        <style name="actionOverflowButtonStyle" parent="@style/Widget.AppCompat.ActionButton.Overflow">
            <item name="android:tint">@color/colorAccent</item>
        </style>

        <style name="Toolbar" parent="ThemeOverlay.AppCompat.Light" />
    </resources>

マテリアルデザインというGoogleが推奨するUIでは

colorPrimary
colorPrimaryDark
colorAccent