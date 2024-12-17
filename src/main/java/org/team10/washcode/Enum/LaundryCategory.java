package org.team10.washcode.Enum;

import lombok.Getter;

@Getter
public enum LaundryCategory {
    SHOES("신발"),                    // 신발
    PADDING("패딩"),                  // 패딩
    PREMIUM_FABRIC("프리미엄 패브릭"),   // 프리미엄 패브릭
    CARRIER_SANITATION("캐리어 소독"),  // 캐리어 소독
    COTTON_LAUNDRY("면 세탁물"),        // 면 세탁물
    STORAGE_SERVICE("보관서비스"),       // 보관 서비스
    BEDDING("침구");                   // 침구

    private final String description;

    LaundryCategory(String description) {
        this.description = description;
    }
}
