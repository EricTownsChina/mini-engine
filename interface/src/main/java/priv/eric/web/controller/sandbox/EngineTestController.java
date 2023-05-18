package priv.eric.web.controller.sandbox;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.eric.web.entity.Resp;

/**
 * Description: todo
 *
 * @author EricTowns
 * @date 2023/5/18 21:21
 */
@RequestMapping("sandbox")
@RestController
public class EngineTestController {

    @GetMapping("run")
    public Resp run() {

        return Resp.success();
    }


}
