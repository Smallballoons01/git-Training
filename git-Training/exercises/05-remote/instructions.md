### 05 远端与协作

目标：在本机用“裸仓库”模拟 `origin`，练习 fetch/pull/push、跟踪分支与 PR 流程。

步骤：
1) `./setup.sh` 创建 `origin.git`（裸仓库）与 `workspace`（工作副本）。
2) 在 `workspace` 中创建 `feature/x`，推送到 origin 并设置上游：`git push -u origin feature/x`。
3) 切换回 `main`，拉取他人更改；模拟评审后合并 `feature/x`。
4) 体验 `git pull --rebase` 与 `git merge` 的差异。


