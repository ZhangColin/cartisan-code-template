package ${package}.${module};

import com.cartisan.constants.CodeMessage;
import com.cartisan.dtos.PageResult;
import com.cartisan.exceptions.CartisanException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.responses.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "${modelName}：${tableRemarks}")
@RestController
@RequestMapping("/${modules}")
@Validated
@Slf4j
public class ${Module}Controller {
    private final ${Module}AppService service;

    public ${Module}Controller(${Module}AppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索${tableRemarks}")
    @GetMapping("/search")
    public ResponseEntity<PageResult<${Module}Dto>> search${Modules}(
            @ApiParam(value = "查询参数") ${Module}Query ${module}Query,
            @PageableDefault Pageable pageable) {
        return success(service.search${Modules}(${module}Query, pageable));
    }

    @ApiOperation(value = "获取${tableRemarks}")
    @GetMapping("/{id}")
    public ResponseEntity<${Module}Dto> get${Module}(@ApiParam(value = "${tableRemarks}Id", required = true) @PathVariable Long id){
        return success(service.get${Module}(id));
    }

    @ApiOperation(value = "添加${tableRemarks}")
    @PostMapping
    public ResponseEntity<${Module}Dto> add${Module}(
            @ApiParam(value = "${tableRemarks}信息", required = true) @Validated @RequestBody ${Module}Param ${module}Param) {
        return success(service.add${Module}(${module}Param));
    }

    @ApiOperation(value = "编辑${tableRemarks}")
    @PutMapping("/{id}")
    public ResponseEntity<${Module}Dto> edit${Module}(
            @ApiParam(value = "${tableRemarks}Id", required = true) @PathVariable Long id,
            @ApiParam(value = "${tableRemarks}信息", required = true) @Validated @RequestBody ${Module}Param ${module}Param) {
        return success(service.edit${Module}(id, ${module}Param));
    }

    @ApiOperation(value = "删除${tableRemarks}")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove${Module}(
            @ApiParam(value = "${tableRemarks}Id", required = true) @PathVariable Long id) {
        service.remove${Module}(id);
        return success();
    }
}
