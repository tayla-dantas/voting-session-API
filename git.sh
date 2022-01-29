#!/bin/zsh
echo "# voting-session-API" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:tayla-tineu/voting-session-API.git
git push -u origin main
