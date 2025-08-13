#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR=$(cd "$(dirname "$0")" && pwd)
PLAY="$SCRIPT_DIR/playground"
rm -rf "$PLAY"
mkdir -p "$PLAY"
cd "$PLAY"
git init -q
cat > index.html <<'HTML'
<html>
  <body>
    <header>Welcome</header>
    <main>
      <h1>Landing</h1>
    </main>
    <footer>2024</footer>
  </body>
</html>
HTML
git add -A && git commit -q -m "feat: init landing page"
git switch -c develop -q
git switch -c feature/header -q
sed -i '' 's/Welcome/Welcome - Site A/' index.html || true
git commit -am "feat(header): update header title" -q
git switch develop -q
git switch -c feature/footer -q
sed -i '' 's/2024/2025/' index.html || true
git commit -am "feat(footer): update year" -q
git switch develop -q
echo "场景已就绪：在 feature/header 与 feature/footer 上继续编辑并合并至 develop。"


