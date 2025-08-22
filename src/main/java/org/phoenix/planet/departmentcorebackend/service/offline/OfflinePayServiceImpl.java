package org.phoenix.planet.departmentcorebackend.service.offline;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.KafkaOfflinePayInfo;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePayProductSaveRequest;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflinePaySaveRequest;
import org.phoenix.planet.departmentcorebackend.dto.offline.raw.OfflineProduct;
import org.phoenix.planet.departmentcorebackend.dto.offline.request.OfflinePayload;
import org.phoenix.planet.departmentcorebackend.dto.offline.request.OfflinePayload.Item;
import org.phoenix.planet.departmentcorebackend.producer.OfflineEventProducer;
import org.phoenix.planet.departmentcorebackend.util.receipt.ReceiptNoGeneratorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfflinePayServiceImpl implements OfflinePayService {

    // 영수증 번호 생성 유틸
    private final ReceiptNoGeneratorUtil receiptNoGeneratorUtil;
    // kafka producer
    private final OfflineEventProducer offlineEventProducer;
    // 오프라인 결제, 상품 관련
    private final OfflinePayHistoryService offlinePayHistoryService;
    private final OfflinePayProductService offlinePayProductService;
    private final OfflineProductService offlineProductService;
    private final OfflineShopService offlineShopService;
    // 에코 스톡

    @Override
    @Transactional
    public void save(OfflinePayload payload) {
        // 오프라인 결제 정보 저장
        OfflinePaySaveRequest offlinePaySaveRequest = OfflinePaySaveRequest.builder()
            .shopId(payload.shopId())
            .cardCompanyId(payload.cardCompanyId())
            .cardNumberLast4(payload.last4())
            .totalPrice(offlineProductService.getTotalPriceByIds(
                payload.items().stream()
                    .map(Item::productId)
                    .toList()))
            .barcode(receiptNoGeneratorUtil.generate(
                payload.shopId(),
                payload.posId(),
                payload.dailySeq(),
                LocalDateTime.now()))
            .build();
        long offlinePayHistoryId = offlinePayHistoryService.save(offlinePaySaveRequest);

        // 결제 상품 정보들 저장
        List<Long> productIds = payload.items().stream()
            .map(OfflinePayload.Item::productId)
            .toList();

        Map<Long, OfflineProduct> productMap = offlineProductService.searchByIds(productIds)
            .stream()
            .collect(Collectors.toMap(OfflineProduct::offlineProductId, p -> p));

        payload.items()
            .forEach(item -> {
                OfflineProduct offlineProduct = productMap.get(item.productId());

                offlinePayProductService.save(
                    OfflinePayProductSaveRequest.builder()
                        .offlinePayHistoryId(offlinePayHistoryId)
                        .productId(item.productId())
                        .name(offlineProduct.name())
                        .price(offlineProduct.price())
                        .amount(item.amount())
                        .build());
            });

        offlineEventProducer.publishOfflinePayEvent(
            KafkaOfflinePayInfo.builder()
                .offlinePayHistoryId(offlinePayHistoryId)
                .posId(payload.posId())
                .dailySeq(payload.dailySeq())
                .shopId(payload.shopId())
                .cardCompanyId(payload.cardCompanyId())
                .cardNumber(payload.cardNumber())
                .last4(payload.last4())
                .items(payload.items())
                .summary(payload.summary())
                .build());
    }

}
