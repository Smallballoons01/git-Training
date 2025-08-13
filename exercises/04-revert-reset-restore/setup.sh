#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PLAY="$SCRIPT_DIR/playground"
rm -rf "$PLAY"
mkdir -p "$PLAY"
cd "$PLAY"
git init -q
echo one > data.txt
git add -A && git commit -q -m "feat: add data v1"
echo two >> data.txt
git commit -am "feat: add data v2" -q
echo three >> data.txt
git commit -am "feat: add data v3" -q
echo "回滚/复原练习就绪。使用 revert/reset/restore 完成任务。"


