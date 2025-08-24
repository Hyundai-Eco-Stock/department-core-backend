package org.phoenix.planet.departmentcorebackend.dto.offline.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record OfflinePayload(
    @NotNull @Positive Integer posId,
    @NotNull @Positive Long dailySeq,
    @NotNull @Positive Long shopId,
    @NotNull @Positive Long cardCompanyId,
    @NotBlank String cardNumber,
    @NotNull @Positive Integer last4,
    @NotEmpty List<Item> items,
    @NotNull Summary summary
) {

    public record Item(
        @NotNull @Positive Long productId,
        @NotNull @Positive Integer amount
    ) {

    }

    public record Summary(
        @NotNull @Positive Integer subtotal,
        @NotNull Integer discounts,
        @NotNull @Positive Integer total
    ) {

    }
}