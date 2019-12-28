# MVVM

サンプルアプリ

![play.gif](https://github.com/nebusokuhibari/LearningArchtecture/blob/master/ch1_mvvm/play.gif?raw=true)

変更フラグメントのデータを変更すると
表示フラグメントにデータが反映される

# Factoryパターンとは

Factoryパターンとはスーパークラスでインスタンスの作り方を管理して、サブクラスで具体的な処理を行うパターンのこと

GUIアプリケーションでは複数のViewに単体のViewModelを参照させたいことが多いので、View-ViewModel間の管理が楽になるように、このパターンを使ってViewModelのインスタンスを制御を実践してみます

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
