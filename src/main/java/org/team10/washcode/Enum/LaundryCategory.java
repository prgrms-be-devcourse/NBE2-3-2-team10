package org.team10.washcode.Enum;

import lombok.Getter;

@Getter
public enum LaundryCategory {
    SHOES("신발"),                    // 신발 1
    PADDING("패딩"),                  // 패딩 2
    PREMIUM_FABRIC("프리미엄 패브릭"),   // 프리미엄 패브릭 3
    CARRIER_SANITATION("캐리어 소독"),  // 캐리어 소독 4
    COTTON_LAUNDRY("면 세탁물"),        // 면 세탁물 5
    STORAGE_SERVICE("보관서비스"),       // 보관 서비스
    BEDDING("침구");                   // 침구 6

    private final String description;

    LaundryCategory(String description) {
        this.description = description;
    }
}
