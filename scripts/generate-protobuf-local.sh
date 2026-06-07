#!/bin/bash
set -e

cd "$(dirname "$0")/.."

echo "Generating protobuf sources..."
buf generate
echo "Done. onet-backend will pick up changes via includeBuild."
