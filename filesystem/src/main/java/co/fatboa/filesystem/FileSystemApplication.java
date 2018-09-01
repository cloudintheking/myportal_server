package co.fatboa.filesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: hl
 * @Description: TODO
 * @Date: 16:16 2018/8/22
 * @Modified By:
 * @Version 1.0
 */
@SpringBootApplication
@EnableAsync
public class FileSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class, args);
    }
}
