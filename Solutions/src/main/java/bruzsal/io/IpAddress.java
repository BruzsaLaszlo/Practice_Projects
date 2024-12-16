package bruzsal.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IpAddress {

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, NoSuchFieldException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getLocalHost().getHostName());

        try (var client = HttpClient.newHttpClient()) {
            String ipv4 = client.send(
                    HttpRequest.newBuilder(URI.create("https://api.ipify.org")).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
            System.out.println(ipv4);
            String ipv6 = client.send(
                    HttpRequest.newBuilder(URI.create("https://api6.ipify.org")).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
            System.out.println(ipv6);
        }
    }
}
