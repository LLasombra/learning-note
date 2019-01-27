## git 相关命令:
 ### git 简介:
  * **[Git](https://www.yiibai.com/git/getting-started-git-basics.html) 更像是把数据看作是对小型文件系统的一组快照。 每次你提交更新，或在 Git 中保存项目状态时，它主要对当时的全部文件制作一个快照并保存这个快照的索引。 为了高效，如果文件没有修改，Git 不再重新存储该文件，而是只保留一个链接指向之前存储的文件。 Git 对待数据更像是一个 快照流。**
  * **Git 数据库中保存的信息都是以文件内容的哈希值(24b9da6552252987aa493b52f8696cd6d3b0037)来索引，不是文件名**
  * **Git 一般只添加数据**
  * **remote/origin/master 表示远程分支**
  * **Git 有三种状态**
   + committed(已提交): Git 仓库--Git 用来保存项目的元数据和对象数据库的地方
   + modified(已修改): 工作目录--对项目的某个版本独立提取出来的内容--暂存区域是一个文件，保存了下次将提交的文件列表信息，一般在 Git 仓库目录中
   + staged(已暂存): 暂存区域
  ![avatar](http://www.yiibai.com/uploads/images/201707/0607/744160702_48164.png)
 ### git 安装和设置
  #### 安装
  ```shell
  sudo yum install git
  sudo apt-get install git
  ```
  #### 设置
   ```shell
   # 查看设置
   git config user.name
   git config --global  --list
   # 更改设置
   git config --global[|system|local] user.name "username"
   git config --global user.email maxsu@yiibai.com
   git config --global core.editor
   # 配置的使用
   git help config
   ```

 ### 最初使用
  ```shell
  git clone url [own-repository-name]  --branch xxx
  cd [own-repository-name]
  git init
  # 做改动
  git add [-A | text.txt | *]
  # 放弃暂存
  git reset HEAD text.txt
  # 放弃更改
  git checkout -- mytext.txt
  # 查看当前工作区的状态
  git status [--short | -s]
  git commit -m 'your commits'
  # 等价于 git add -A + git commit -m ''
  git commit -a -m 'your commits'
  git rm log/\*.log

  # 改名
  git mv file_from file_to
  # 查看提交历史: 显示每次提交的内容差异,次数
  git log [-p -2]
  git log --[stat| pretty] [short| full| fuller] | [=oneline]
  git diff 本身只显示尚未暂存的改动
  git diff --[cached |staged] 查看已暂存的更改
  git difftool --tool-help 使用UI来看不同

  # 查看你已经配置的远程仓库服务器
  git remote -v

 # 查看更改
 git log
 git show ad4d41e969ff9fb5952560b28ed08bb430dd0356
  ```
 ### .gitignore 的使用
  ```shell
  # no .a files
  *.a
  # but do track lib.a, even though you're ignoring .a files above
  !lib.a
  # only ignore the TODO file in the current directory, not subdir/TODO
  /TODO
  # ignore all files in the build/ directory
  build/
  # ignore doc/notes.txt, but not doc/server/arch.txt
  doc/*.txt
  # ignore all .pdf files in the doc/ directory
  doc/**/*.pdf
  ```

 ### git 分支:
  ```shell
  # 查看分支[所有]
  git branch [-a]
  # 创建分支
  git branch new_branch
  # 切换分支[没有就创建新的分支]
  # git push set-upstream origin new_branch
  git checkout [-b] new_branch [remote_branch]
  # 删除本地分支
  git branch -D test_branch
  # 删除远程分支
  git push origin --delete Chapater6
  # 重命名分支
  git branch -m new_branch wchar_support
  ```
 ### git 冲突
  * git diff
  * git rebase: **在另一个分支基础之上重新应用，用于把一个分支的修改合并到当前分支**
  * 出现冲突，git 停下来解决冲突，在执行
  * git git rebase --continue
  * git rebase --abort 放弃rebase<br/>
  * [参考](https://www.yiibai.com/git/git_rebase.html)
  * 开发<br/>
  ![avatar](http://www.yiibai.com/uploads/images/201707/1307/842100748_44775.png)<br/>
  ![avatar](http://www.yiibai.com/uploads/images/201707/1307/810100749_17109.png)
  * git merge<br/>
  ![avatar](http://www.yiibai.com/uploads/images/201707/1307/350100750_71786.png)
  * git rebase<br/>
   ![avatar](http://www.yiibai.com/uploads/images/201707/1307/845100751_76810.png)
   ![avatar](http://www.yiibai.com/uploads/images/201707/1307/645100753_82870.png)

 ### FETCH_HEAD:
  **是一个版本链接，记录在本地的一个文件中，指向着目前已经从远程仓库取下来的分支的末端版本**
 ### git fetch 和 git pull 的区别:
  * [参考](https://www.cnblogs.com/ToDoToTry/p/4095626.html)
  * git remote: 本地的repo和远程的repo进行版本对应
   * *git remote add：*  来添加当前本地长度的远程repo，本地就知道push到哪个branch
  * git branch: 为了单独记录软件的某一个发布版本而存在的
   - *git branch:*  可以查看本地分支
   - *git branch -r:*  可以用来查看远程分支
   - *git push origin feature/001:* 地分支和远程分支在git push 的时候可以随意指定，交错对应，只要不出现**版本从图**即可
   - *git pull :*  等价于 *git fetch + git merge*
  * git push 和 commit-id: git commit 操作来保存当前工作到本地的repo，此时会产生一个commit-id，这是一个能唯一标识一个版本的序列号。 在使用git push后，这个序列号还会同步到远程repo.
  * git merge: 团队协作时使用
  * git fetch: **将更新 git remote 中所有的远程 repo 所包含分支的最新commit-id, 将其记录到.git/FETCH_HEAD文件中(将远程的commit-id 拿到本地)**
  * git fetch remote_repo: **将更新名称为remote_repo 的远程repo上的所有branch的最新commit-id，将其记录**
  * git fetch remote_repo remote_branch_name: **将更新名称为remote_repo 的远程repo上的分支： remote_branch_name**
  * git fetch remote_repo remote_branch_name:local_branch_name: **将更新名称为remote_repo 的远程repo上的分支： remote_branch_name ，并在本地创建local_branch_name 本地分支保存远端分支的所有数据**
  * git pull [origin feature/001]: **首先，基于本地的[FETCH_HEAD](#FETCH_HEAD)记录，比对本地的FETCH_HEAD记录与远程仓库的版本号，然后git fetch 获得当前指向的远程分支的后续版本的数据，然后再利用git merge将其与本地的当前分支合并**
  ![avatar](https://www.yiibai.com/uploads/allimg/140613/0A025G34-0.jpg)
  
### git commit 解决

 ```shell
 # 提交改动
 git add -A
 git commit -m 'xxx'
 # 重命名分支
 git branch -m old-name new-name
 # 切到主分支
 git checkout develop
 # 拉取最新的代码
 git pull 
 # 切回之前的分支
 git checkout -b new-new-name
 # rebase develop 的代码
 git rebase develop
 # 解决冲突，继续rebase
 git rebase continue
 # merge 代码进这个分支
 git merge --squash new-name
 # 添加 commit 信息
 git commit -m 'xxx'
 # 推送远程分支
 git push -f origin xxx
 ```