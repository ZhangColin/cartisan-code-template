package ${package}.${module};

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
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

import static com.cartisan.response.ResponseUtil.success;

@Api(tags = "${serviceName}：${moduleName}")
@RestController
@RequestMapping("/${modules}")
@Validated
@Slf4j
public class ${Module}Controller {
    private final ${Module}AppService service;

    public ${Module}Controller(${Module}AppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索${moduleName}")
    @GetMapping("/search")
    public ResponseEntity<PageResult<${Module}Dto>> search${Modules}(
            @ApiParam(value = "查询参数") ${Module}Query ${module}Query,
            @PageableDefault Pageable pageable) {
        return success(service.search${Modules}(${module}Query, pageable));
    }

    @ApiOperation(value = "获取${moduleName}")
    @GetMapping("/{id}")
    public ResponseEntity<${Module}Dto> get${Module}(@ApiParam(value = "${moduleName}Id", required = true) @PathVariable Long id){
        return success(service.get${Module}(id));
    }

    @ApiOperation(value = "添加${moduleName}")
    @PostMapping
    public ResponseEntity<${Module}Dto> add${Module}(
            @ApiParam(value = "${moduleName}信息", required = true) @Validated @RequestBody ${Module}Param ${module}Param) {
        return success(service.add${Module}(${module}Param));
    }

    @ApiOperation(value = "编辑${moduleName}")
    @PutMapping("/{id}")
    public ResponseEntity<${Module}Dto> edit${Module}(
            @ApiParam(value = "${moduleName}Id", required = true) @PathVariable Long id,
            @ApiParam(value = "${moduleName}信息", required = true) @Validated @RequestBody ${Module}Param ${module}Param) {
        return success(service.edit${Module}(id, ${module}Param));
    }

    @ApiOperation(value = "删除${moduleName}")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove${Module}(
            @ApiParam(value = "${moduleName}Id", required = true) @PathVariable Long id) {
        service.remove${Module}(id);
        return success();
    }
}
