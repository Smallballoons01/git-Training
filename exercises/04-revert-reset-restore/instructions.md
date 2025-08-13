### 04 回滚与修复

目标：体验 `revert`、`reset`、`restore` 的差异与适用场景。

步骤：
1) `./setup.sh` 创建三次提交。
2) 用 `git revert <sha>` 回滚第二次提交，观察历史新增一次“逆向”提交。
3) 用 `git reset --soft <sha>` 回到第一次提交但保留暂存；再 `--hard` 体验清空工作目录的效果（谨慎）。
4) 修改一个文件后，用 `git restore` 与 `git restore --staged` 感受还原粒度差异。


