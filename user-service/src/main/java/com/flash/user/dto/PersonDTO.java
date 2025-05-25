package com.flash.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "插入neo4j入参")
public class PersonDTO {
    @NotNull
    @Schema(description = "作者1id", example = "A123")
    private String authorId1;
    @NotNull
    @Schema(description = "作者1名字", example = "张三")
    private String name1;

    @NotNull
    @Schema(description = "作者2id", example = "A223")
    private String authorId2;
    @NotNull
    @Schema(description = "作者2名字", example = "张四")
    private String name2;

}
