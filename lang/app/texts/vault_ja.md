# XPipe Git Vault

XPipeは、すべての接続データを独自のgitリモートリポジトリと同期することができる。このリポジトリは、すべてのXPipeアプリケーションインスタンスで同じように同期でき、あるインスタンスで行ったすべての変更がリポジトリに反映される。

まず最初に、お気に入りのgitプロバイダでリモートリポジトリを作成する必要がある。このリポジトリはプライベートでなければならない。
そして、そのURLをXPipeのリモートリポジトリ設定にコピー＆ペーストすればよい。

また、ローカルマシンに`git`クライアントをインストールしておく必要がある。ローカルターミナルで`git`を実行して確認できる。
持っていない場合は、[https://git-scm.com](https://git-scm.com/) にアクセスして git をインストールすることができる。

## リモートリポジトリを認証する

認証には複数の方法がある。ほとんどのリポジトリは HTTPS を使っており、ユーザー名とパスワードを指定する必要がある。
プロバイダによっては SSH プロトコルをサポートしているところもあり、XPipe でもサポートしている。
git で SSH を使っている人なら、おそらく設定方法は知っていると思うので、ここでは HTTPS についてのみ説明する。

リモートの git リポジトリと HTTPS 経由で認証できるように git CLI を設定する必要がある。それには複数の方法がある。
リモートリポジトリの設定が完了したら、XPipe を再起動することで設定が完了しているかどうかを確認できる。
ログイン認証情報の入力を求められたら、それを設定する必要がある。

この[GitHub CLI](https://cli.github.com/)のような特別なツールの多くは、インストールすると自動的にすべてをやってくれる。
新しいバージョンの git クライアントの中には、特別なウェブサービスを使って認証できるものもあり、その場合はブラウザで自分のアカウントにログインするだけでよい。

ユーザー名とトークンを使って手動で認証する方法もある。
現在では、ほとんどのプロバイダが、コマンドラインからの認証に、従来のパスワードの代わりにパーソナル・アクセストークン（PAT）を要求している。
一般的な(PAT)ページはこちら：
- **GitHub**：[個人アクセストークン（クラシック）](https://github.com/settings/tokens)
- **GitLab**：[個人アクセストークン](https://docs.gitlab.com/ee/user/profile/personal_access_tokens.html)
- **BitBucket**: [個人アクセストークン]()[個人アクセストークン](https://support.atlassian.com/bitbucket-cloud/docs/access-tokens/)
- **Gitea**: `Settings -> Applications -> Manage Access Tokens セクション`。
リポジトリのトークン権限をReadとWriteに設定する。残りのトークンのパーミッションはReadに設定できる。
gitクライアントがパスワードの入力を促しても、プロバイダーがまだパスワードを使っていない限り、トークンを入力する必要がある。
- ほとんどのプロバイダーはもうパスワードをサポートしていない。

毎回認証情報を入力したくない場合は、git認証情報マネージャを使えばいい。
詳しくは
- [https://git-scm.com/doc/credential-helpers](https://git-scm.com/doc/credential-helpers)
- [https://docs.github.com/en/get-started/getting-started-with-git/caching-your-github-credentials-in-git](https://docs.github.com/en/get-started/getting-started-with-git/caching-your-github-credentials-in-git)

最近の git クライアントの中には、認証情報を自動的に保存してくれるものもある。

うまくいけば、XPipe がリモートリポジトリにコミットをプッシュしてくれるはずだ。

## カテゴリをリポジトリに追加する

デフォルトでは、コミットする接続を明示的に制御できるように、同期する接続カテゴリは設定されていない。
そのため、最初はリモートリポジトリは空になる。

あるカテゴリの接続を git リポジトリに追加するには、歯車のアイコンをクリックする必要がある、
歯車のアイコンをクリックする必要がある。
をクリックする。
それから`Add to git repository`をクリックして、カテゴリと接続をgitリポジトリに同期する。
これで、同期可能なすべての接続がgitリポジトリに追加される。

## ローカル接続は同期されない

ローカルマシンの下にある接続は、ローカルシステムでのみ利用可能な接続やデータを参照するため、共有することができない。

ローカルファイルに基づいた接続、たとえば SSH 設定などは、その基礎となるデータ（この場合はファイル）が git リポジトリに追加されていれば、git 経由で共有することができる。

## git にファイルを追加する

すべての設定が終わったら、SSH キーなどの追加ファイルを git に追加することもできる。
すべてのファイルを選択すると、その横に git ボタンが表示され、ファイルが git リポジトリに追加される。
プッシュされたファイルは暗号化される。
