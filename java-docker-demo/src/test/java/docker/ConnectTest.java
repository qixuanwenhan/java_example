package docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import demo.DockerClientHelper;
import java.util.List;
import org.junit.Test;

/**
 * http://www.baeldung.com/docker-java-api
 */
public class ConnectTest {

    @Test
    public void connect() throws Exception {
        DockerClient dockerClient = DockerClientHelper.INSTANCE.getDockerClient();
        List<Container> containers = dockerClient.listContainersCmd().withShowSize(true).withShowAll(true)
            .withStatusFilter("exited").exec();

        for (Container container : containers) {
            System.out.println(container);
            System.out.println(container.getNames());
        }

        dockerClient.close();
    }
}