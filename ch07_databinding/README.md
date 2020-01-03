# DataBinding

サンプルアプリ

![play.gif](./play.gif?raw=true)

ツールバー

# Todo

まだ未完成。現在、詰まっている箇所

このようにレイアウトxmlでContentCOmpatをインポートすれば、xml上でリソースファイルにアクセスできるようになる。

    <import type="androidx.core.content.ContextCompat" />

これを利用すれば、xml上でContextCompatクラスにアクセスできて便利
        
    <TextView
            ：
        android:src="@{ContextCompat.getDrawable(content, viewmodel.noTaskIconRes)}" /> -->

それでBlueprintのサンプルではタスクが何もない時はImageViewとTextViewを表示するために使われているんだけど、自分の環境だとデータバインドの自動生成クラスが正しく出力されないみたいで、上手く行かない。
