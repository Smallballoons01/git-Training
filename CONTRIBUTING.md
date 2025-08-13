### 贡献指南（提交/分支/合并/冲突解决规范）

#### 1) 提交规范（Conventional Commits）

- 提交格式：`<type>(<scope>): <subject>`，示例：`feat(auth): support refresh token`。
- 常用 type：`feat`、`fix`、`docs`、`style`、`refactor`、`perf`、`test`、`chore`。
- 主题使用中文或英文皆可，建议 72 字符内；需要说明动机与影响时添加正文段落。
- 关联任务/缺陷：在页脚添加 `Refs: PROJ-123` 或 `Fixes #42`。
- 原子提交：一组逻辑相关的变更一个提交；避免“混合大提交”。

推荐命令：

```bash
git add -p    # 分块暂存，保证原子提交
git commit -m "feat: xxx"
```

钩子与校验：见 `exercises/06-hooks`，包含 `pre-commit` 和 `commit-msg` 示例，可复制到项目中使用。

#### 2) 分支规范（轻量 GitFlow）

- `main`：稳定生产分支，只接受发布版本合入。
- `develop`：日常集成分支。
- `feature/<ticket>-<desc>`：功能分支，源自 `develop`，如 `feature/123-login`。
- `hotfix/<ticket>-<desc>`：生产紧急修复，源自 `main`，修复后回合 `main` 与 `develop`。
- `release/<version>`：发布分支，源自 `develop`，仅做修复与版本号调整。

命名规则：小写、短横线，必要时带任务号/问题号。

#### 3) 合并规范（PR 流程）

- 所有进入 `develop`/`main` 的变更均通过 PR。
- PR 检查项：
  - 通过构建与检查（CI 绿）。
  - 代码自测完成，有必要的单元/集成测试。
  - 文档/变更日志更新（如需要）。
- 合并方式：
  - 功能分支到 `develop`：默认使用 Squash Merge，保持主线整洁，一次功能一个提交。
  - `release/*` 到 `main`：使用 Merge Commit（保留合并信息）。
  - `hotfix/*`：优先直接合入 `main`，并同步回 `develop`（可 `git cherry-pick`）。
- 更新主线：在 PR 合并前，用 `git fetch` 后优先 `git rebase origin/develop`（或 `origin/main`），避免无意义的合并提交。

常用命令：

```bash
git switch -c feature/123-login develop
# 开发...
git fetch origin
git rebase origin/develop
# 解决冲突 -> push
git push -u origin feature/123-login
```

#### 4) 冲突解决方法（Cookbook）

通用流程（以 rebase 为例，merge 类似）：

1. `git fetch` 同步远端；`git rebase origin/develop`。
2. `git status` 查看冲突文件；逐个打开含冲突标记 `<<<<<<< HEAD`、`=======`、`>>>>>>>` 的文件。
3. 保留正确内容并删除标记；保存后 `git add <file>`。
4. `git rebase --continue`（或合并时 `git merge --continue`）。
5. 如处理错误：`git rebase --abort`/`git merge --abort` 回到操作前。

技巧：

- 选择一侧改动：`git checkout --ours <path>` / `git checkout --theirs <path>`（新 Git 可用 `git restore --source=HEAD <path>` 等）。
- 二进制或大文件：与作者沟通选择权威版本，直接 ours/theirs；必要时在 `.gitattributes` 指定合并策略。
- 重命名冲突：优先 `git mv` 保持历史；必要时分两步提交（先改名，再修改）。
- 频繁重复冲突：启用 `git config rerere.enabled true` 让 Git 记住解决方式。

#### 5) Tag 与版本

- 语义化版本：`MAJOR.MINOR.PATCH`；发布前在 `release/*` 分支打标。
- 命令：`git tag -a v1.2.3 -m "release v1.2.3" && git push origin --tags`。

#### 6) 禁忌与注意

- 不向共享分支强制推送：避免 `git push --force`（必要时用 `--force-with-lease` 并沟通）。
- 不提交机密、生成物与大文件；完善 `.gitignore`。
- 单次 PR 关注单一主题，避免“捆绑式合并”。


