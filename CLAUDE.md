# CLAUDE.md

## 빌드 및 배포
- `buf build` 로컬 검증 후 tag push
- tag push (`v*`) 시 GitHub Actions 자동 배포 (Maven Central)
- 빌드 실패한 태그는 삭제 말고 버전 번호 올려서 재배포
- 현재 최신 버전: `0.1.27`

## 패키지 네이밍
- proto package: `onet.{service}.v1`
- java_package: `com.onet.{service}.v1`
- csharp_namespace: `Onet.{Service}.V1`

## Proto 파일 구조
```
proto/onet/
  common/v1/
    common_types.proto   — MatchMode, ItemType 등 공통 enum/타입
    error.proto          — ErrorDetail 메시지
    error_code.proto     — ErrorCode enum
  matchmaker/v1/
    matchmaker.proto     — MatchMakerService, MatchRequest/MatchFound
  battle/v1/
    battle.proto         — BattleService, 전체 메시지 정의
    battle_types.proto   — GameEndReason, PlayerLeftReason 등 enum
```

## Cross-package 참조
다른 패키지 타입은 반드시 fully qualified name 사용:
```protobuf
onet.common.v1.MatchMode mode = 2;  // 올바름
MatchMode mode = 2;                 // 컴파일 에러
```

## 필드 번호 규칙
- 한번 배포된 필드 번호는 절대 재사용 금지
- 필드 삭제 시 `reserved` 처리하거나 버전 올려서 재배포
- 필드 추가는 항상 마지막 번호 다음에 append

## ErrorCode 범위
| 범위 | 서비스 |
|------|--------|
| 1~99 | 공통 |
| 10000~10999 | Match Maker |
| 20000~20999 | Battle |
| 30000~30999 | Lobby |

## MatchMode 상수값
간격(10단위)을 두어 중간 삽입 가능하도록 설계. FRIENDLY=1 예외.

## mapId 규칙
- `MatchRequest.map_id`: `0` = 랜덤, `1006~1012` = 지정 맵 (6×6~12×12)
- 맵 ID는 1000번대, 크기별로 `1000 + 크기` 패턴

## 주요 메시지 요약
**MatchRequest** (matchmaker)
- `map_id = 3`: 0=랜덤, 1006~1012=지정

**MatchFound** (matchmaker)
- `battle_server_url = 1`: Unity 등 외부 클라이언트용 (public IP)
- `battle_server_internal_url = 4`: web-client 프록시용 (Docker 내부 DNS)

**BoardState** (battle)
- `width/height/seed` (fields 5~7): RoomJoined 에서만 의미있음, BoardSyncResponse에서는 무시
