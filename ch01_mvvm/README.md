# MVVM

サンプルアプリ

![play.gif](./play.gif?raw=true)

変更フラグメントのデータを変更すると
表示フラグメントにデータが反映される

# MVVMパターンとは

MVVM(Model/View/ViewModel)パターンとはデータとGUIを分離する設計手法の１つ。他にもMVC、MVPパターンなどもあるけど、ツール系のアプリではMVVMパターンが採用されることが多いです

# 備忘録

## ViewModelの作成

ObservableFieldもしくは@Bindable属性を付ける方法がありましたが、現在はLiveDataを使う方法が推奨されています

詳しくはTaskViewModelを参照

## フラグメント(.kt)の生成

自動生成されるクラス「***.TaskFragmentBinding」をインポートし忘れないように（ない場合はIDEの「ビルド->プロジェクトをクリーンにする」を実行したあとIDEを再起動すると上手くいくらしい）

どのデータを何処に表示するかのコードはxml側で記述しているので、こっちのコードはかなり短くなっています。

詳しくはTaskFragment.ktとTaskEditFragment.ktを参照

## 双方向バインド

双方向バインドを有効にするには、以下の位置に「=」をつける。

    android:text="@{viewmodel.description}"
                ↓
    android:text="@={viewmodel.description}"

違いが見えにくくてハマってた。ないとユーザーからの値変更がViewModelの値に反映されません。

詳しくはレイアウトのfragment_task_edit.xmlを参照
