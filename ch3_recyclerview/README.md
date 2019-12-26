# Ch3_recyclerview

リサクラビューのサンプルアプリ

![play.gif](https://github.com/nebusokuhibari/LearningArchtecture/blob/master/ch3_recyclerview/play.gif?raw=true)

# リサイクラビューとは

ListViewよりも柔軟な表現をすることができるビューのこと。用意されたAPIで自作のListViewを作るという認識で良いかと。

この手法を知ったとき、ListViewで大量にデータを表示しようとすると、Viewが大量に生成されて処理が重くなるので、リサイクラビューとはその対策手法だと勘違いしてた。
ListViewはAdapterViewというViewを再利用するクラス継承しているので、ちゃんと再利用してる様子。

# 備忘録

各クラスの説明

## <ListAdapterの実装>

リサイクラビューを実装する上で、以下の抽象クラスを継承した実装クラスを作る必要がある

- ListAdapter
- DiffUtil.ItemCallback

詳細はTasksAdapterを参照

## <findViewByIdによるUI呼び出しを避けたい>

findViewByIdは低速なので、キャッシュからUI呼び出しをしたほうが高速

詳細はTasksAdapter.ViewHolderを参照

## <resファイル上でコレクションとUIをデータバインドしたい>

@BindingAdapter("app:items")

という属性を付与したメソッドを作る必要がある様子

詳細はTasksListBindings.ktを参照

# 個人的メモ

リサクラビューのための[epoxy](https://github.com/airbnb/epoxy)とかいうライブラリもあるようで、様々なViewTypeを持つListViewを作るならこのライブラリを使ったほうがいいらしい