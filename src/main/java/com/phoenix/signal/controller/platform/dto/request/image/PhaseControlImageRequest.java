package com.phoenix.signal.controller.platform.dto.request.image;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhaseControlImageRequest {
    @NotNull
    @Schema(description = "设备ID")
    Long deviceId;

    @NotNull
    @Schema(description = "方案号")
    Integer planNumber;

    @NotNull
    @Schema(description = "阶段号")
    Integer phaseNumber;

    @NotBlank
    @Schema(description = "相位图片文件", type = "MultipartFile", format = "binary")
    MultipartFile image;
}
