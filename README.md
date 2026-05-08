# onet-proto

onet 서비스의 중앙 protobuf 스키마 저장소.

## 구조

```
proto/
  common/     - 공통 메시지 (Ping/Pong 등)
  user/       - 유저 도메인 (예정)
  match/      - 매칭 도메인 (예정)
buf.yaml      - buf lint / breaking change 설정
```

## 패키지 네이밍 규칙

```
onet.<도메인>.v1
```

- C#: `Onet.<Domain>.V1`
- Java: `com.onet.<domain>.v1`

## buf CLI 사용

```bash
# 설치
brew install bufbuild/buf/buf

# lint
buf lint

# breaking change 체크 (main 브랜치 기준)
buf breaking --against '.git#branch=main'
```

## Workflow

1. PR 기반으로 proto 변경
2. CI: lint + breaking change 검사
3. merge 후: codegen (C# / Java) → 패키지 배포 (예정)
