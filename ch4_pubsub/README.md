# PubSub

サンプルアプリ

![play.gif](https://github.com/nebusokuhibari/LearningArchtecture/blob/master/ch4_pubsub/play.gif?raw=true)

タスクのチェックが変更されるとスナックバーが表示される

# Pub/Subパターンとは

Observerパターン似ているけど、違う点は「イベントバス」と呼ばれる。発行者と受信者から認識されるコンポーネントを作って送信者と受信者を完全に分離してしまうのが特徴。

Androidではクリック時に他の画面に遷移するなど、Activity・Fragment・BackgroundThreadなどコンポーネント間の通信で使われています。

# 備忘録

## フローについて

送信者(レイアウトxmlが受信者(スナックバー)にイベントバス(Event)を渡す流れ

1. レイアウトxmlからバインドされたメソッドが呼ばれる
2. メソッドがEventを生成してLiveDataの値に代入
3. LiveDataが値が変わったこと検知して登録されたメソッドを呼び出す
4. 登録されたメソッドはスナックバーを生成
5. Eventがスナックバーに文字を渡してスナックバーのアニメーションが開始
6. 念のためEventの値はnullにして再び使用できないようにする

## スナックバーの表示

トースト表示みたいにスナックバーを簡単に表示できるライブラリがあるので、簡単に実装できる。

        implementation "com.google.android.material:material:1.0.0"

BluePrintではViewの拡張メソッドを作ってスナックバーを使えるようにしているみたい。

詳細はViewExt.ktを参照

## 非同期処理のテスト

非同期をテストするための「Espresso Idling Resource」に関するコードもあったけど、今回は解説は省略。

# 個人的メモ

このあたりの処理に関するライブラリはリアクティブプログラミングができる[RxKotlin](https://github.com/ReactiveX/RxKotlin)を使うのがベストみたい。他にもあるけど、[Otto](https://github.com/square/otto) は更新停止。[EventBus](https://github.com/greenrobot/EventBus)はピークスの書籍「Androidアプリ設計パターン入門」によるとメリカリでは使うのを止めたらしい。
