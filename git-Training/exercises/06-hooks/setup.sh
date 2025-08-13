#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PLAY="$SCRIPT_DIR/playground"
rm -rf "$PLAY"
mkdir -p "$PLAY/.git/hooks" "$PLAY/src"
cd "$PLAY"
git init -q

# 示例代码
cat > src/bad.js <<'JS'
function sum(a, b){console.log('debug');return a+b}
module.exports = { sum }
JS
git add -A && git commit -q -m "feat: init"

# pre-commit 钩子：禁止 console.log
cat > .git/hooks/pre-commit <<'HOOK'
#!/usr/bin/env bash
set -euo pipefail
if git diff --cached --name-only | grep -E '\.(js|ts)$' >/dev/null; then
  if git diff --cached | grep -n "console\.log" >/dev/null; then
    echo "[pre-commit] Found console.log in staged changes. Please remove it." >&2
    exit 1
  fi
fi
HOOK
chmod +x .git/hooks/pre-commit

# commit-msg 钩子：简单的 Conventional Commits 校验
cat > .git/hooks/commit-msg <<'HOOK'
#!/usr/bin/env bash
set -euo pipefail
MSG_FILE="$1"
MSG=$(head -n1 "$MSG_FILE")
if ! echo "$MSG" | grep -E '^(feat|fix|docs|style|refactor|perf|test|chore)(\(.+\))?: .+' >/dev/null; then
  echo "[commit-msg] Message must follow Conventional Commits, e.g. 'feat: add feature'" >&2
  exit 1
fi
HOOK
chmod +x .git/hooks/commit-msg

echo "钩子已安装于 $PLAY/.git/hooks"


