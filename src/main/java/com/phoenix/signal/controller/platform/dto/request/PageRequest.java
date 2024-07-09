package com.phoenix.signal.controller.platform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PageRequest {

    @NotNull
    @Schema(description = "页码")
    Integer index;

    @NotNull
    @Schema(description = "每页数量")
    Integer size;
}
