# Navigation

サンプルアプリ

![play.gif](./play.gif?raw=true)

アイテムをクリックすると遷移する

# Navigationとは？

Fragmentの遷移情報を

# 備忘録

## build.gradle(プロジェクト)

    buildscript {
            :
        dependencies {
                :
            classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0"
        }
    }

## build.gradle（モジュール）

    apply plugin: "androidx.navigation.safeargs"

    dependencies {

        implementation "androidx.navigation:navigation-fragment-ktx:2.1.0"
        implementation "androidx.navigation:navigation-ui-ktx:2.1.0"
        implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"
    }

## Navigationレイアウトの作成

resフォルダにnavigaitonフォルダを作成する。

するとIDEのデザイン編集でFragmentの遷移を作成できるので、こんな感じで作る

![01.png](./01.png?raw=true)

このデザインモードの使い方は[ここ](https://inside.dmm.com/entry/2018/05/25/android-navigation)が詳しい説明が載ってた。

## Action

さきほどのActionを呼び出せば、デザインモードで編集した通りに遷移する

    private fun openTaskDetails(taskId: String) {
        val action = TasksFragmentDirections.actionTasksFragmentDestToTaskDetailFragmentDest(taskId)
        findNavController().navigate(action)
    }

このメソッドはviewmodelのLiveDataを通して呼ばれる。

## Argument

デザインモードで編集すると、まるで引数のように値をFragmentに渡せるようになる

class TaskDetailFragment : Fragment() {
            ：
    private val args: TaskDetailFragmentArgs by navArgs()
            ：
}

SimpleAdapterとかクッソ使いづらいので助かる

## navigationの配置

デザインモードで作ったnav_graph.xmlはfragmentと同じ扱いでUI上に配置できる。

        <fragment
            android:id="@+id/nav_host_fragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"

            app:defaultNavHost="true"
            app:navGraph="@navigation/nav_graph" />

## クリック時のイベントの補足

過去のプロジェクトでイベントを表すLiveDataを介して公開されるデータのラッパー"Event<out T>(private val content: T)"メソッドを作成したので、LiveDataに渡されるコンテンツを受け取って処理するメソッド"EventObserver"を追加する必要があったのでメモ

    class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
        override fun onChanged(event: Event<T>?) {
            event?.getContentIfNotHandled()?.let {
                onEventUnhandledContent(it)
            }
        }
    }

（この説明はNavigationとは関係ないので他のプロジェクトにこの説明の文章を移したい）

# 個人的メモ

以下３つの作っていないフラグメントを作成して、全部の遷移を作ってサンプル通りに動くアプリを作ろうと思ったんだけど、作業量が4倍になるだけで、勉強になりそうなものがなかったので省略した。

addEditTask・・・指定のタスクの編集
taskDetail・・・指定のタスクを表示
statistics・・・全タスクの統計を表示

次からはテストファーストでプロジェクトを作成する方針に変更して、残りのコードを作る予定。