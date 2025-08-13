### 01 基础练习

目标：熟悉工作区/暂存区/本地仓库，完成首次提交并查看历史。

步骤：

1) 运行 `./setup.sh` 生成示例文件并初始化仓库。
2) 使用 `git status` 查看状态；用 `git add -A` 暂存；`git commit -m "feat: init project"` 提交。
3) 修改 `src/app.js` 中的欢迎语，分别查看 `git diff` 与 `git diff --staged` 的差异。
4) 新增一个文件 `docs/notes.md`，尝试 `git add -p` 进行分块暂存。
5) 使用 `git log --oneline --graph --decorate --all` 查看历史。

完成标准：
- 至少 2 次提交，信息遵循 Conventional Commits（如 `feat: ...`、`docs: ...`）。


