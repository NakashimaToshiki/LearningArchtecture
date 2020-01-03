# Toolbar

サンプルアプリ

![play.gif](./play.gif?raw=true)

ツールバーの各項目をクリックするとスナックバーが表示されます。
表示されているViewItemを変更するといった内容は実装していません。

# 備忘録

[公式リファレンス](https://developer.android.com/training/appbar/setting-up?hl=ja)

## 1. デフォルトのアクションバーの無効化

無効化していないとエラー「This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.」がでる

アプリマニュフェストでappcompat の NoActionBar テーマのいずれかを使用するように <application> 要素を設定します。

    <application
        android:theme="@style/Theme.AppCompat.Light.NoActionBar"
        />

初期プロジェクトだとthemeの値がstyle.xmlを参照しているので、ここをstyle.xmlを修正する

## 2. mesuレイアウトの生成

menuフォルダを生成してレイアウトxmlファイル(R.menu.tasks_fragment_menu.xml)を生成する

メニュー上のアイコンはres/drawableフォルダに作る

## 3. Fragmentクラスの修正

Fragmentに先ほど作ったレイアウトxmlを利用するようにコードを修正する。

    override fun onCreateView(・・・)
                ：
        setHasOptionsMenu(true) 
                ：
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_fragment_menu, menu)
    }

## 4. メニューバーのクリックイベントを作成

イベントに登録する処理を作成して完成。
