package org.phoenix.planet.departmentcorebackend.service.car;

import org.phoenix.planet.departmentcorebackend.constant.CarEcoType;
import org.springframework.stereotype.Service;

@Service
public class CarTypeSearchService {

    /**
     * 차의 친환경 정보를 가져오는 메서드 (현재는 임의로 결정, 항상 같은 결과)
     *
     * @param carNumber 차량 번호
     * @return CarEcoType 친환경 종류
     */
    public CarEcoType getTypeByCarNumber(String carNumber) {

        if (carNumber == null || carNumber.isEmpty()) {
            return null;
        }
        if ("157더6629".equals(carNumber)) {
            return CarEcoType.ELECTRONIC;
        }

        CarEcoType[] types = CarEcoType.values();
        int index = (carNumber.hashCode() & Integer.MAX_VALUE) % types.length;
        return types[index];
    }

}
