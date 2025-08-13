### Git 常用命令速查

- 初始化与基本操作
  - `git init` / `git clone <url>`
  - `git status` / `git add -A` / `git commit -m "msg"`
  - `git log --oneline --graph --decorate --all`
  - `git diff` / `git diff --staged`

- 分支
  - `git branch -vv` / `git switch -c feature/x`
  - `git merge main` / `git rebase main`
  - `git cherry-pick <sha>`

- 回滚与修复
  - `git revert <sha>` 生成逆向提交
  - `git reset --soft|--mixed|--hard <sha>`
  - `git restore --staged <path>` / `git restore <path>`

- 暂存与选择提交
  - `git stash push -m "wip"` / `git stash pop`
  - `git add -p` 分块暂存

- 远端
  - `git remote -v` / `git fetch --all` / `git pull --rebase`
  - `git push -u origin feature/x`

- 标签
  - `git tag v1.0.0` / `git tag -a v1.0.0 -m "first"`
  - `git push origin --tags`

- 排错
  - `git blame <file>` / `git bisect start`


