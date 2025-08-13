#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PLAY="$SCRIPT_DIR/playground"
ORIGIN="$PLAY/origin.git"
WORK="$PLAY/workspace"
rm -rf "$PLAY"
mkdir -p "$PLAY"
git init --bare -q "$ORIGIN"
git clone -q "$ORIGIN" "$WORK"
cd "$WORK"
echo "hello" > readme.txt
git add -A && git commit -q -m "feat: init repo"
git push -q origin HEAD:main
git switch -c main -q
echo "远端练习就绪：工作副本位于 $WORK，远端为 $ORIGIN。"


