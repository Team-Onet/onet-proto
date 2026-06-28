#!/bin/bash
set -e

cd "$(dirname "$0")/.."

echo "Cleaning previous generated sources..."
rm -rf java/src/main/java java/src/main/kotlin csharp/Generated

echo "Generating protobuf sources..."
buf generate
echo "Done. onet-backend will pick up changes via includeBuild."
