#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PLAY="$SCRIPT_DIR/playground"
rm -rf "$PLAY"
mkdir -p "$PLAY/src" "$PLAY/docs"
cat > "$PLAY/src/app.js" <<'JS'
export function greet(name = 'Git') {
  return `Hello, ${name}!`;
}

console.log(greet());
JS
cat > "$PLAY/README.md" <<'MD'
# Demo Project

用于 Git 基础练习。
MD
cd "$PLAY"
git init -q
git add -A
git commit -q -m "feat: bootstrap demo project"
echo "// TODO: change greeting" >> src/app.js
echo "Run exercises as described in instructions.md" > TODO.txt
echo "已创建练习场景：$PLAY"


