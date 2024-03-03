package ru.kolivim.myproject.task.management.system.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.kolivim.myproject.task.management.system.api.dto.base.BaseDto;

@Data
public class AccountDto extends BaseDto {

    @Schema(description = "Сумма на счете")
    private Double balance;

    @Schema(description = "Максимальная сумма, для начисления процентов")
    private Double maxBalance;

}
