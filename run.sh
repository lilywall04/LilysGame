#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

cd "$ROOT_DIR"

mkdir -p bin

javac -d bin $(find src -name "*.java")
java -cp bin main.Main
