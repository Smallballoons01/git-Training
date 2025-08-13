### 03 变基与挑拣

目标：用 `rebase` 整理提交历史，使用 `cherry-pick` 挑拣修复。

步骤：
1) `./setup.sh` 创建多次杂乱提交。
2) 在 `feature/refactor` 上执行 `git rebase -i main` 合并与重排提交。
3) 从 `bugfix/typo` 上挑拣一个提交到 `main`：`git cherry-pick <sha>`。

完成标准：
- `main` 历史干净，`feature/refactor` 被 rebase 成线性历史。


