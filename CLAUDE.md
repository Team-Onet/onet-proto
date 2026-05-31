# CLAUDE.md

## 빌드 및 배포
- `buf build` 로컬 검증 후 tag push
- tag push (`v*`) 시 GitHub Actions 자동 배포 (Maven Central)
- 빌드 실패한 태그는 삭제 말고 버전 번호 올려서 재배포

## 패키지 네이밍
- proto package: `onet.{service}.v1`
- java_package: `com.onet.{service}.v1`
- csharp_namespace: `Onet.{Service}.V1`

## Cross-package 참조
다른 패키지 타입은 반드시 fully qualified name 사용:
```protobuf
onet.common.v1.MatchMode mode = 2;  // 올바름
MatchMode mode = 2;                 // 컴파일 에러
```

## ErrorCode 범위
| 범위 | 서비스 |
|------|--------|
| 1~99 | 공통 |
| 10000~10999 | Match Maker |
| 20000~20999 | Battle |
| 30000~30999 | Lobby |

## MatchMode 상수값
간격(10단위)을 두어 중간 삽입 가능하도록 설계. FRIENDLY=1 예외.
