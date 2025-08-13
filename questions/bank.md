### 题库（含答案）

1. 单选：Git 中用于将工作区修改加入暂存区的命令是？
   - A. git commit
   - B. git add
   - C. git push
   - D. git fetch
  

2. 多选：以下哪些命令会改变提交历史？
   - A. git revert
   - B. git reset --hard HEAD~1
   - C. git rebase -i
   - D. git merge --no-ff
  

3. 判断：`git pull --rebase` 与 `git fetch + git rebase` 等价。（T/F）
   

4. 问答：`revert` 与 `reset` 的区别？
 

5. 场景题：你在 `feature/pay` 上产生了 5 次提交，其中 3 个是修复 typo 的噪声。如何在合并主干前整理历史？
   

6. 问答：如何快速定位引入 Bug 的提交？
   

7. 单选：以下哪种做法是错误的？
   - A. 在 PR 中使用 squash 合并
   - B. 向共享分支强制推送 `git push --force`
   - C. 在本地分支使用 `rebase -i`
   - D. 提交信息遵循 Conventional Commits
  

8. 问答：何时使用 `cherry-pick`？
 

9. 多选：以下哪些属于远端相关操作？
   - A. git fetch
   - B. git tag -a v1.0.0 -m "msg"
   - C. git push --tags
   - D. git remote -v
   

10. 问答：给出一套最小分支策略。
 




1, - 答案：B














2, - 答案：B、C（A 生成新的提交，不改历史；D 默认不改已有提交的哈希）













3, - 答案：T（默认配置下等价）

















4,  - 参考：`revert` 生成一个新的“逆向”提交，安全可共享；`reset` 移动当前分支指针（可能丢工作区修改），不应在已推送的分支上随意使用。














5,- 参考：在分支上 `git rebase -i main`，将 typo 修复 `fixup` 到相应的功能提交，或使用 `squash` 合并，形成语义清晰的历史。













6,- 参考：使用 `git bisect` 在“坏/好”区间做二分定位；或用 `git blame` 查看具体行的最近提交。













7, - 答案：B














8,  - 参考：当需要把某个修复从一个分支挑拣到另一个分支（如从 `develop` 到 `release/x`）且不想合并全部历史时。













9,- 答案：A、C、D











10,  - 参考：以 `main` 为稳定生产线，`develop` 为集成线，功能以 `feature/*` 开发，紧急修复 `hotfix/*` 从 `main` 切分并回合到 `main` 与 `develop`。