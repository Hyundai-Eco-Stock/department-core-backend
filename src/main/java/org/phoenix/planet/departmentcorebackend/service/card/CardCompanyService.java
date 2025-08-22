package org.phoenix.planet.departmentcorebackend.service.card;

import java.util.List;
import org.phoenix.planet.departmentcorebackend.dto.card.response.CardCompanyListResponse;

public interface CardCompanyService {

    List<CardCompanyListResponse> searchAll();
}
