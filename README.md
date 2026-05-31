# onet-proto

onet-backend 서비스들이 공유하는 gRPC proto 정의 및 생성 코드 배포 저장소.

## 패키지 구조

```
proto/
  onet/
    common/v1/
      error_code.proto  # ErrorCode enum
      error.proto       # ErrorResponse message
      match_mode.proto  # MatchMode enum
      common.proto      # 기타 공통 메시지
    matchmaker/v1/
      matchmaker.proto  # MatchMakerService
    # (예정) battle/v1/
    # (예정) lobby/v1/
```

## 패키지 네이밍

- proto package: `onet.{service}.v1`
- java_package: `com.onet.{service}.v1`
- csharp_namespace: `Onet.{Service}.V1`

## ErrorCode 범위

| 범위 | 서비스 |
|------|--------|
| 1~99 | 공통 |
| 10000~10999 | Match Maker |
| 20000~20999 | Battle |
| 30000~30999 | Lobby |

## MatchMode

```protobuf
MATCH_MODE_UNSPECIFIED = 0;
MATCH_MODE_FRIENDLY = 1;   // 친선전 (초대/수락)
MATCH_MODE_BOT = 10;
MATCH_MODE_SOLO = 20;
MATCH_MODE_TEAM_2V2 = 30;
MATCH_MODE_TEAM_3V3 = 40;
```

상수값 간격(10단위)을 두어 중간 삽입 가능하도록 설계.

## buf CLI

```bash
brew install bufbuild/buf/buf

buf lint
buf build
buf breaking --against '.git#branch=main'
```

## 배포

tag push (`v*`) 시 GitHub Actions이 Maven Central에 자동 배포.

```bash
git tag v0.1.x
git push origin v0.1.x
```

빌드 실패한 태그는 삭제하지 말고 버전 번호 올려서 재배포.
