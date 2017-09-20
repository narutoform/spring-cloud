package cn.chinotan;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class User_manager_elasticsearch_transportClient {

    public static void main(String[] args) {
        SpringApplication.run(User_manager_elasticsearch_transportClient.class, args);
    }
    
    @Bean
    public TransportClient transportClient(){

        InetSocketTransportAddress address = null;
        try {
            address = new InetSocketTransportAddress(InetAddress.getByName("192.168.217.135"), 9300);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Settings settings = Settings.builder().put("cluster.name", "chinotan").build();

        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY);

        transportClient.addTransportAddress(address);
        
        return transportClient;
    }
}
