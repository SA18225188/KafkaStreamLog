package com.example.messagingstompwebsocket;



import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

/**
 * WebSocketController
 */
@RestController
public class WebSocketController {

    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("demo/push/{toUserId}")
    public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {


        StreamsBuilder builder = KafkaUtil.config();
        builder.<String, String>stream("test")   //filter 过滤 保留包含message的结果
                .filter((k, v) -> v.contains(message)).foreach((k, v) -> {
            try {
                WebSocketServer.sendInfo(v,toUserId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //.filter(log -> {  }).map (log -> ws.send(log));
        //.flatMapValues(value -> value))

        KafkaUtil.start(builder);
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}


