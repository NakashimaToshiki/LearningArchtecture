# viewmodelfactory

サンプルアプリ

![play.gif](https://github.com/nebusokuhibari/LearningArchtecture/blob/master/ch2_viewmodelfactory/play.gif?raw=true)

「編集」ボタンを押すと表示アクティビティから編集アクティビティに遷移します
戻ると表示アクティビティにデータの変更が反映されているのが確認できます

# Factoryパターンとは

Factoryパターンとはスーパークラスでインスタンスの作り方を管理して、サブクラスで具体的な処理を行うパターンのこと

GUIアプリケーションでは複数のViewに単体のViewModelを参照させたいことが多いので、View-ViewModel間の管理が楽になるように、このパターンを使ってViewModelのインスタンスを制御を実践してみます

# 備忘録

ch1_mvvmではViewModelのインスタンスに以下のコードが使われています。

        viewModel = ViewModelProviders.of(activity!!).get(TaskViewModel::class.java)

これは同じアクティビティに存在するFragmentのViewModelはすべて共通にするという処理。

この方法ではUI構成の変更に弱く、例えば「データ表示用のFragmentとデータ編集用のフラグメントを同じActivityに置いていたけど、編集用フラグメントを別のActivityに移動したい」といった調整で、上記のコードを変更するのを忘れると、編集フラグメントと表示フラグメントで別々のViewModelの領域を参照してしまいます。

コードの変え忘れがコンパイルのエラーとして発生しないのでバグの温床にもなります。

なのでコードを以下に書き換えます。

        private val viewModel: TaskViewModel by viewModels<TaskViewModel>{getViewModelFactory() }

getViewModelFacotryでViewModelのインスタンスするロジックを自分で作ります。サンプルの中身はシングルトンでViewModelを返すロジックです。
