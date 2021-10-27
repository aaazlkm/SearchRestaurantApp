# 検索機能の Domain 層実装する #5

- todo
  - [x] resultsReturned などの情報は paging に必要なのか調べる
  - [x] infra -> domain のデータ converter 用意する
  - [x] di に repository を登録する
  - [x] Result 型を用意する
  - [x] domain で返す方は Flow にする
  - [x] コルーチンのドキュメントを読む
  - [x] flow のドキュメントを読む
  - [x] contract とは
  - [x] api のエラーを domain で定義する
  - [x] コルーチンを導入する
  - [x] エラー時のレスポンスを受け取れるようにする
  - [x] エラー時のレスポンスをどのように扱うか考える
  - [x] エラー処理を commmon でできないか調べる
  - [x] エラーを確認する
  - [x] ライブラリの記述をちゃんとする
  - [x] コード確認
  - [x] こだわりポイントまとめる
- src
  - retrofit でエラーハンドリングする方法
    - https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
  - コルーチンと retrofit
    - https://zenn.dev/chmod644/articles/fc304b7e2508de

## こだわりポイントまとめる

- やったことないコルーチンを使用してる
- エラーの定義ちゃんとやってる
  - api通信系のエラーを定義
  - レスポンスのエラーも受け取れるようにしてる
- apiのリクエストレスポンスの処理をできるだけ共通化してる -> 変更があっても一箇所を変えるだけでいい
- load result を用意して、loadの表現をしてる

## エラーを確認する

- [x] ネットワークエラー
- [x] http エラー -> hotpepper は http ステータスコード全部 200
- [x] hotpepper エラー
  - [x] サーバー障害 テストできない
  - [x] api キー
  - [x] パラメータ不正エラー

## エラー処理を共通ででできないか調べる

- 今パース処理に直接書いているが、他のものをパースする際にも同じ処理を書くことになる
  - これはエラー処理が変わったときに都度変更することになるので、うまくない
  - そのため、エラーの処理はできる限りまとめておいた方がよさそうです
- 方法
  - 1 call safly の方で頑張る
    - response の型を見て、エラーフィールドを持ってるのならば、エラー処理を行う
- 結論
  - 1 にする

## エラー時のレスポンスをどのように扱うか考える

- api のエラーをまとめて扱かおうと思っていたが、api ごとにエラーの返り方が異なる気がする
  - api によって http レスポンスステータスが表現することが異なる
  - なのでここを抽象的な表現にするのはあまりうまくなきがする
- よって domain の exception で具体的に書くことにする

## エラー時のレスポンスを受け取れるようにする

- 方法
  - 1 jsonObject を見て、それら次第で convert の方法を変える
  - 2 error オブジェクトが null かどうかで判定する
- 結論
  - error オブジェクトが null かどうかで判断する

## api のエラーを domain で定義する

- どんな種類のエラーが起きるか
  - IOException
    - 通信エラー
  - HttpException
    - HTTP ステータスコードが 4xx or 5xx の時のエラー.
  - UnknownFailure
    - 予期しないエラー（レスポンスの JSON のパースエラーなど）.
- 上記のエラーが表示されるかどうか確認する

## contract とは

- Contracts DSL で事後条件を定義しておくことで、関数呼び出し後の状態を保証することが出来ます。
- isNotNull を通れば、その後の変数は!!などをしなくて済むようになる
- ただし多用は避けた方がよさそう
  - その関数にどんな contract が実装されてるかが、定義ジャンプしないと把握把握することができない
    - しかも contract が間違っていたら終わり
    - https://yyyank.blogspot.com/2021/07/kotlin-contract-usage-idiom.html

## resultsReturned などの情報は paging に必要なのか調べる

- next key を計算するのに resultsReturned が必要そう
  - next key が存在するかどうかを results_returned \* results_start < results_available で判断する
  - https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data
