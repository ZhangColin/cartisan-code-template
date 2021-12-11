package ${packageName}.${camelModule};

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

/**
* @author ${author}
*/
@Api(tags = "${serviceName}：${moduleName}")
@RestController
@RequestMapping("/${camelModules}")
@Validated
@Slf4j
public class ${pascalModule}Controller {
    private final ${pascalModule}AppService service;

    public ${pascalModule}Controller(${pascalModule}AppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索${moduleName}")
    @GetMapping("/search")
    public ResponseEntity<PageResult<${pascalModule}Dto>> search${pascalModules}(
            @ApiParam(value = "查询参数") ${pascalModule}Query ${camelModule}Query,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return success(service.search${pascalModules}(${camelModule}Query, pageable));
    }

    @ApiOperation(value = "获取${moduleName}")
    @GetMapping("/{id}")
    public ResponseEntity<${pascalModule}Dto> get${pascalModule}(@ApiParam(value = "${moduleName}Id", required = true) @PathVariable Long id){
        return success(service.get${pascalModule}(id));
    }

    @ApiOperation(value = "添加${moduleName}")
    @PostMapping
    public ResponseEntity<${pascalModule}Dto> add${pascalModule}(
            @ApiParam(value = "${moduleName}信息", required = true) @Validated @RequestBody ${pascalModule}Param ${camelModule}Param) {
        return success(service.add${pascalModule}(${camelModule}Param));
    }

    @ApiOperation(value = "编辑${moduleName}")
    @PutMapping("/{id}")
    public ResponseEntity<${pascalModule}Dto> edit${pascalModule}(
            @ApiParam(value = "${moduleName}Id", required = true) @PathVariable Long id,
            @ApiParam(value = "${moduleName}信息", required = true) @Validated @RequestBody ${pascalModule}Param ${camelModule}Param) {
        return success(service.edit${pascalModule}(id, ${camelModule}Param));
    }

    @ApiOperation(value = "删除${moduleName}")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove${pascalModule}(
            @ApiParam(value = "${moduleName}Id", required = true) @PathVariable Long id) {
        service.remove${pascalModule}(id);
        return success();
    }
}
