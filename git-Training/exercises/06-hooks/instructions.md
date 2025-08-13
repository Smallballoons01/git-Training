### 06 Git Hooks

目标：在本地安装 `pre-commit` 与 `commit-msg` 钩子，实现简单的格式检查与提交信息校验。

步骤：
1) `./setup.sh` 生成示例项目与钩子脚本。
2) 触发 `pre-commit`：在 `src/bad.js` 中故意留下 `console.log`，尝试提交观察被阻止。
3) 触发 `commit-msg`：尝试使用不符合 Conventional Commits 的信息提交，观察被拒绝。
4) 修复问题后再次提交，成功通过钩子。


