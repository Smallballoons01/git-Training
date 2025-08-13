#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PLAY="$SCRIPT_DIR/playground"
rm -rf "$PLAY"
mkdir -p "$PLAY"
cd "$PLAY"
git init -q
echo "alpha" > file.txt
git add file.txt && git commit -q -m "feat: add file"
git switch -c feature/refactor -q
echo "beta" >> file.txt && git commit -am "style: add spacing" -q
echo "gamma" >> file.txt && git commit -am "refactor: split function" -q
echo "delta" >> file.txt && git commit -am "fix: adjust logic" -q
git switch -c bugfix/typo -q
echo "typo" >> file.txt && git commit -am "fix: correct typo" -q
git switch -c main -q
echo "main-only" >> file.txt && git commit -am "chore: main change" -q
echo "创建三条分支：feature/refactor、bugfix/typo、main。按说明 rebase 与 cherry-pick。"


